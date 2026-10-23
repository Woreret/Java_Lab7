import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    record Product(int id, String name) {
    }

    public static void main(String[] args) throws IOException {
        // повне сховище
        InMemoryDataRepository<Product> store = new InMemoryDataRepository<>(Product::id);
        importAll(store, List.of(new Product(1, "Ноутбук"), new Product(2, "Мишка")));
        check(store.getAll().size() == 2, "після імпорту 2 записи");

        // аналітика: тільки читання і експорт
        ReadOnlyAnalyticsRepository<Product> analytics = new ReadOnlyAnalyticsRepository<>(store.getAll(), Product::id);
        Path csv = Files.createTempFile("analytics", ".csv");
        export(analytics, csv);
        check(Files.readAllLines(csv).size() == 2, "у CSV 2 рядки");

        // кеш: читання, запис, очищення
        CacheRepository<Product> cache = new CacheRepository<>(Product::id);
        cache.add(new Product(10, "Клавіатура"));
        cache.update(new Product(10, "Клавіатура механічна"));
        check(cache.getById(10).orElseThrow().name().equals("Клавіатура механічна"), "update у кеші");
        purge(cache);
        check(cache.getAll().isEmpty(), "кеш порожній після purge");

        // не скомпілюється (і це добре):
        // importAll(cache, ...);   кеш не вміє масовий імпорт
        // purge(analytics);        аналітика не вміє очищатись
        // analytics.add(...);      методу add немає

        System.out.println("Task 4 (ISP): OK");
    }

    // кожен метод просить лише ту можливість що йому треба
    static <T> void importAll(BulkImporter<T> target, List<T> items) {
        target.bulkImport(items);
    }

    static void export(CsvExporter target, Path path) {
        target.exportToCsv(path);
    }

    static void purge(Purgeable target) {
        target.purgeAll();
    }

    private static void check(boolean ok, String msg) {
        if (!ok) throw new IllegalStateException("Перевірка не пройдена: " + msg);
    }
}

// показуємо що кожен метод бере тільки потрібні можливості
// закоментовані рядки навмисно не компілюються

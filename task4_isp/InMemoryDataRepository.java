import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class InMemoryDataRepository<T> implements ReadRepository<T>, WriteRepository<T>,
        BulkImporter<T>, CsvExporter, Purgeable {

    private final Map<Integer, T> store = new LinkedHashMap<>();
    private final ToIntFunction<T> idOf;

    public InMemoryDataRepository(ToIntFunction<T> idOf) {
        this.idOf = idOf;
    }

    @Override
    public List<T> getAll() {
        return List.copyOf(store.values());
    }

    @Override
    public Optional<T> getById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void add(T entity) {
        if (store.putIfAbsent(idOf.applyAsInt(entity), entity) != null) {
            throw new IllegalStateException("Запис з таким id вже є.");
        }
    }

    @Override
    public void update(T entity) {
        if (store.replace(idOf.applyAsInt(entity), entity) == null) {
            throw new NoSuchElementException("Запису з таким id немає.");
        }
    }

    @Override
    public void delete(int id) {
        if (store.remove(id) == null) {
            throw new NoSuchElementException("Запису з таким id немає.");
        }
    }

    @Override
    public void bulkImport(List<T> entities) {
        entities.forEach(this::add);
    }

    @Override
    public void exportToCsv(Path path) {
        CsvExporter.write(path, store.values());
    }

    @Override
    public void purgeAll() {
        store.clear();
    }
}

// єдиний клас що вміє все: читати, писати, імпорт, експорт, очищення

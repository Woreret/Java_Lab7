import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;

public final class ReadOnlyAnalyticsRepository<T> implements ReadRepository<T>, CsvExporter {

    private final List<T> data;
    private final ToIntFunction<T> idOf;

    public ReadOnlyAnalyticsRepository(List<T> data, ToIntFunction<T> idOf) {
        this.data = List.copyOf(data);
        this.idOf = idOf;
    }

    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public Optional<T> getById(int id) {
        return data.stream().filter(x -> idOf.applyAsInt(x) == id).findFirst();
    }

    @Override
    public void exportToCsv(Path path) {
        CsvExporter.write(path, data);
    }
}

// тільки читання і експорт
// методів змінити нема, тому дані не зламати

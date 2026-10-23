import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public interface CsvExporter {

    void exportToCsv(Path path);

    // єдине місце де пишемо csv щоб не дублювати код
    static void write(Path path, Collection<?> rows) {
        try {
            Files.write(path, rows.stream().map(String::valueOf).toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

// маленький список можливостей: зберегти у csv

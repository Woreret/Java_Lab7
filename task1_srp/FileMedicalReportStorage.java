import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

// причина для зміни: куди зберігаємо
public final class FileMedicalReportStorage {

    private final Path dir;

    public FileMedicalReportStorage(Path dir) {
        this.dir = dir;
    }

    public void save(String patientId, String json) {
        try {
            Files.createDirectories(dir);
            Files.writeString(dir.resolve("medical_report_" + patientId + ".json"), json);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

// тут тільки запис у файл, куди і як зберігати знає лише цей клас

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("reports");
        MedicalReportService service = new MedicalReportService(dir);

        DiagnosticResult high = service.process(new PatientData("P-001", "Іваненко І.І.", 110, 175, 150, 95, "+380501112233"));
        DiagnosticResult low = service.process(new PatientData("P-002", "Петренко П.П.", 70, 180, 118, 76, "+380501112233"));
        check(high.risk() == DiagnosticResult.Risk.HIGH, "P-001 має бути HIGH");
        check(low.risk() == DiagnosticResult.Risk.LOW, "P-002 має бути LOW");
        check(Files.readString(dir.resolve("medical_report_P-001.json")).contains("\"BMI\": 35.92"), "BMI з крапкою");

        try {
            service.process(new PatientData("P-003", "Сидоренко С.С.", 1, 175, 120, 80, "+380501112233"));
            throw new IllegalStateException("Мала бути помилка валідації");
        } catch (IllegalArgumentException expected) {
            // так і має бути
        }
        check(Files.notExists(dir.resolve("medical_report_P-003.json")), "для поганих даних файл не створюється");

        System.out.println("Task 1 (SRP): OK");
    }

    private static void check(boolean ok, String msg) {
        if (!ok) throw new IllegalStateException("Перевірка не пройдена: " + msg);
    }
}

// збираємо все разом і перевіряємо що працює

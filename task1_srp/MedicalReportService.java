import java.nio.file.Path;
import java.time.Instant;

// диригент, тільки порядок кроків
public final class MedicalReportService {

    private final PatientDataValidator validator = new PatientDataValidator();
    private final DiagnosticsCalculator calculator = new DiagnosticsCalculator();
    private final JsonMedicalReportFormatter formatter = new JsonMedicalReportFormatter();
    private final SmsDoctorNotifier notifier = new SmsDoctorNotifier();
    private final FileMedicalReportStorage storage;

    public MedicalReportService(Path reportsDir) {
        this.storage = new FileMedicalReportStorage(reportsDir);
    }

    public DiagnosticResult process(PatientData patient) {
        validator.validate(patient);
        DiagnosticResult result = calculator.evaluate(patient);

        storage.save(patient.patientId(), formatter.format(patient, result, Instant.now()));
        if (result.risk() == DiagnosticResult.Risk.HIGH) {
            notifier.notifyCritical(patient, result);
        }
        return result;
    }
}

// сам нічого не рахує, лише викликає інших по черзі
// перевірка, розрахунок, збереження і якщо ризик високий то sms лікарю

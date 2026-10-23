import java.time.Instant;
import java.util.Locale;

// причина для зміни: вигляд звіту
public final class JsonMedicalReportFormatter {

    public String format(PatientData p, DiagnosticResult r, Instant at) {
        return String.format(Locale.ROOT, """
                {
                  "PatientId": "%s",
                  "Name": "%s",
                  "BMI": %.2f,
                  "BloodPressure": "%d/%d",
                  "Category": "%s",
                  "GeneratedAt": "%s"
                }""",
                p.patientId(), p.fullName().replace("\"", "\\\""), r.bmi(),
                p.systolic(), p.diastolic(), r.risk().label, at);
    }
}

// тут тільки вигляд звіту, Locale.ROOT щоб було 24.69 а не 24,69

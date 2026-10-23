import java.util.Locale;

// причина для зміни: як повідомляємо лікаря
public final class SmsDoctorNotifier {

    public void notifyCritical(PatientData p, DiagnosticResult r) {
        String sms = String.format(Locale.ROOT, "УВАГА: пацієнт %s, ІМТ %.1f, тиск %d/%d",
                p.fullName(), r.bmi(), p.systolic(), p.diastolic());
        System.out.println("SMS -> " + p.doctorPhone() + ": " + sms);
    }
}

// тут тільки повідомлення лікарю, зараз просто друкує
// справжній sms можна підключити тут

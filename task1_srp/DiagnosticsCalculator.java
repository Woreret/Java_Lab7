// причина для зміни: медична логіка
public final class DiagnosticsCalculator {

    public DiagnosticResult evaluate(PatientData p) {
        double m = p.heightCm() / 100.0;
        double bmi = p.weightKg() / (m * m);

        DiagnosticResult.Risk risk = DiagnosticResult.Risk.LOW;
        if (bmi > 30 || p.systolic() >= 140) risk = DiagnosticResult.Risk.HIGH;
        else if (bmi >= 25 || p.systolic() >= 130) risk = DiagnosticResult.Risk.MODERATE;

        return new DiagnosticResult(bmi, risk);
    }
}

// тут тільки математика, рахуємо імт і дивимось який ризик
// якщо треба змінити пороги то міняємо лише цей файл

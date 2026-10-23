// результат розрахунку, імт і ризик
public record DiagnosticResult(double bmi, Risk risk) {

    public enum Risk {
        HIGH("Високий ризик"), MODERATE("Помірний ризик"), LOW("Низький ризик");

        public final String label;

        Risk(String label) {
            this.label = label;
        }
    }
}

// назви ризику в одному місці щоб не писати вручну по різних файлах

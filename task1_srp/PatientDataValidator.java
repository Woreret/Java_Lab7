// причина для зміни: правила перевірки
public final class PatientDataValidator {

    public void validate(PatientData p) {
        if (p == null) throw new IllegalArgumentException("Пацієнт не може бути null.");
        // id йде в назву файлу тому дозволяємо тільки безпечні символи
        if (p.patientId() == null || !p.patientId().matches("[A-Za-z0-9_-]+"))
            throw new IllegalArgumentException("Некоректний ідентифікатор пацієнта.");
        if (p.weightKg() <= 2 || p.weightKg() > 300)
            throw new IllegalArgumentException("Некоректна вага.");
        if (p.heightCm() <= 40 || p.heightCm() > 250)
            throw new IllegalArgumentException("Некоректний зріст.");
        if (p.systolic() < 50 || p.diastolic() < 30)
            throw new IllegalArgumentException("Тиск поза допустимим діапазоном.");
    }
}

// тут тільки перевірка даних, якщо щось не так одразу помилка

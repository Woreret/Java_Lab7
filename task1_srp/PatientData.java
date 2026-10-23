// просто дані пацієнта без логіки
public record PatientData(String patientId, String fullName, double weightKg, double heightCm,
                          int systolic, int diastolic, String doctorPhone) {
}

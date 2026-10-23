import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserProfile profile = new UserProfile("u-42", "olena", "olena@example.com", "+380501234567");

        // справжня збірка
        new UserProfileManager(new RedisUserCache(), new PostgresUserRepository(), new SendGridSecurityNotifier())
                .updateProfile(profile);

        // тестова збірка
        List<String> calls = new ArrayList<>();
        new UserProfileManager(
                id -> calls.add("cache:" + id),
                p -> calls.add("db:" + p.userId()),
                (email, msg) -> calls.add("mail:" + email))
                .updateProfile(profile);

        if (!calls.equals(List.of("db:u-42", "cache:u-42", "mail:olena@example.com"))) {
            throw new IllegalStateException("Неправильні виклики: " + calls);
        }

        try {
            new UserProfileManager(null, p -> { }, (e, m) -> { });
            throw new IllegalStateException("Мала бути помилка");
        } catch (NullPointerException expected) {
            // так і має бути
        }

        System.out.println("Task 5 (DIP): OK");
    }
}

// єдине місце де створюємо справжні класи і підключаємо
// потім та сама логіка з фальшивими залежностями (лямбдами)

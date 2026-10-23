import java.util.Objects;

public final class UserProfileManager {

    private final UserCache cache;
    private final UserRepository repository;
    private final SecurityNotifier notifier;

    public UserProfileManager(UserCache cache, UserRepository repository, SecurityNotifier notifier) {
        this.cache = Objects.requireNonNull(cache, "cache");
        this.repository = Objects.requireNonNull(repository, "repository");
        this.notifier = Objects.requireNonNull(notifier, "notifier");
    }

    public void updateProfile(UserProfile profile) {
        if (profile == null || profile.userId() == null || profile.userId().isEmpty()) {
            throw new IllegalArgumentException("Некоректний профіль користувача.");
        }
        repository.update(profile);
        cache.invalidate(profile.userId());
        notifier.sendSecurityAlert(profile.email(), "Дані вашого профілю оновлено.");
    }
}

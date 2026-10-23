public final class PostgresUserRepository implements UserRepository {

    @Override
    public void update(UserProfile profile) {
        System.out.println("[PostgreSQL] оновлено профіль " + profile.userId());
    }
}

// справжня реалізація бази (поки просто друкує текст)

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;

// кеш бере все з InMemoryDataRepository, а назовні показує тільки свої 3 можливості
public final class CacheRepository<T> implements ReadRepository<T>, WriteRepository<T>, Purgeable {

    private final InMemoryDataRepository<T> inner;

    public CacheRepository(ToIntFunction<T> idOf) {
        this.inner = new InMemoryDataRepository<>(idOf);
    }

    @Override
    public List<T> getAll() {
        return inner.getAll();
    }

    @Override
    public Optional<T> getById(int id) {
        return inner.getById(id);
    }

    @Override
    public void add(T entity) {
        inner.add(entity);
    }

    @Override
    public void update(T entity) {
        inner.update(entity);
    }

    @Override
    public void delete(int id) {
        inner.delete(id);
    }

    @Override
    public void purgeAll() {
        inner.purgeAll();
    }
}

// кеш вміє читати, писати і очищатись
// імпорт та експорт йому не треба

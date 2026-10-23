public interface WriteRepository<T> {

    void add(T entity);

    void update(T entity);

    void delete(int id);
}

// маленький список можливостей: додати, оновити, видалити

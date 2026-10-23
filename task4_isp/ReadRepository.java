import java.util.List;
import java.util.Optional;

public interface ReadRepository<T> {

    List<T> getAll();

    Optional<T> getById(int id);
}

// маленький список можливостей: тільки читання

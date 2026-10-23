import java.util.List;

public interface BulkImporter<T> {

    void bulkImport(List<T> entities);
}

// маленький список можливостей: завантажити багато записів

import java.util.Objects;

public final class ReadOnlyPdfDocument implements Document {

    private final String title;
    private final String content;

    public ReadOnlyPdfDocument(String title, String content) {
        this.title = Objects.requireNonNull(title, "title");
        this.content = Objects.requireNonNull(content, "content");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void print() {
        System.out.println("Друк PDF '" + title + "': " + content);
    }
}

// pdf тільки для читання, методів змінити текст у нього нема
// тому його не можна зламати редагуванням

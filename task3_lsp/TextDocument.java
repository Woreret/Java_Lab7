import java.util.Objects;

public final class TextDocument implements EditableDocument {

    private final String title;
    private final String path;
    private String content;

    public TextDocument(String title, String content, String path) {
        this.title = Objects.requireNonNull(title, "title");
        this.content = Objects.requireNonNull(content, "content");
        this.path = Objects.requireNonNull(path, "path");
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
    public void setContent(String newContent) {
        this.content = Objects.requireNonNull(newContent, "newContent");
    }

    @Override
    public void save() {
        System.out.println("Збережено '" + title + "' у " + path);
    }

    @Override
    public void print() {
        System.out.println("Друк '" + title + "': " + content);
    }
}

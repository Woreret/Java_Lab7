import java.util.List;

public class Main {

    public static void main(String[] args) {
        TextDocument report = new TextDocument("Звіт", "Квартальні результати", "/docs/report.txt");
        ReadOnlyPdfDocument contract = new ReadOnlyPdfDocument("Договір", "Умови договору");

        // друкуємо і текст і pdf однаково
        for (Document d : List.of(report, contract)) {
            d.print();
        }

        // редагувати можна тільки EditableDocument
        new DocumentEditor().appendWatermarkAndSave(report, "CONFIDENTIAL");
        // new DocumentEditor().appendWatermarkAndSave(contract, "X");  // не скомпілюється

        if (!report.getContent().endsWith("[Watermark: CONFIDENTIAL]")) {
            throw new IllegalStateException("Водяний знак не додано");
        }
        try {
            report.setContent(null);
            throw new IllegalStateException("Мала бути помилка");
        } catch (NullPointerException expected) {
            // так і має бути
        }

        System.out.println("Task 3 (LSP): OK");
    }
}

// показуємо що pdf і текст друкуються разом, а редагується лише текст

public class DocumentEditor {

    public void appendWatermarkAndSave(EditableDocument doc, String watermark) {
        if (watermark == null || watermark.isBlank()) {
            throw new IllegalArgumentException("Водяний знак не може бути порожнім.");
        }
        doc.setContent(doc.getContent() + "\n[Watermark: " + watermark + "]");
        doc.save();
    }
}

// редактор приймає тільки ті документи що можна змінювати
// pdf сюди не передати бо не скомпілюється

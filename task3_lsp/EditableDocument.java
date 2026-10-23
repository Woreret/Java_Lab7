public interface EditableDocument extends Document {

    void setContent(String newContent);

    void save();
}

// документ який можна змінювати і зберігати
// контракт: setContent не приймає null і після нього getContent() повертає нове значення
// save завжди зберігає, нащадок не має права відмовлятись

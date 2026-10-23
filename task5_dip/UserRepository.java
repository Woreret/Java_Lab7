public interface UserRepository {

    void update(UserProfile profile);
}

// договір: будь-яка база має вміти оновити профіль
// яка саме база менеджеру байдуже

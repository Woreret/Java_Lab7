import java.math.BigDecimal;

// всі знижки в одному місці
public final class Discounts {

    private Discounts() {
    }

    // сума помножена на ставку
    static BigDecimal percent(Order o, String rate) {
        return o.totalAmount().multiply(new BigDecimal(rate));
    }

    // звичайний: 10% на перше замовлення, інакше 5% від 100$
    public static final DiscountStrategy REGULAR = o -> {
        if (o.firstOrder()) return percent(o, "0.10");
        if (o.totalAmount().compareTo(new BigDecimal("100")) >= 0) return percent(o, "0.05");
        return BigDecimal.ZERO;
    };

    // vip: 15% і ще 5% якщо більше 5 товарів
    public static final DiscountStrategy VIP = o ->
            percent(o, o.itemCount() > 5 ? "0.20" : "0.15");

    // оптовик: 20% від 1000$, інакше 10%
    public static final DiscountStrategy WHOLESALE = o ->
            percent(o, o.totalAmount().compareTo(new BigDecimal("1000")) >= 0 ? "0.20" : "0.10");

    // співробітник: завжди 30%
    public static final DiscountStrategy EMPLOYEE = o -> percent(o, "0.30");
}

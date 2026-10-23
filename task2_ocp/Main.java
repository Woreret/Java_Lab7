import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<CustomerCategory, DiscountStrategy> all = new EnumMap<>(CustomerCategory.class);
        all.put(CustomerCategory.REGULAR, Discounts.REGULAR);
        all.put(CustomerCategory.VIP, Discounts.VIP);
        all.put(CustomerCategory.WHOLESALE, Discounts.WHOLESALE);
        all.put(CustomerCategory.EMPLOYEE, Discounts.EMPLOYEE);

        DiscountCalculator calc = new DiscountCalculator(all);

        // ті самі числа що давав старий if-else
        expect(calc, "200", 2, true, CustomerCategory.REGULAR, "20.00");
        expect(calc, "150", 2, false, CustomerCategory.REGULAR, "7.50");
        expect(calc, "50", 2, false, CustomerCategory.REGULAR, "0.00");
        expect(calc, "100", 6, false, CustomerCategory.VIP, "20.00");
        expect(calc, "100", 5, false, CustomerCategory.VIP, "15.00");
        expect(calc, "1000", 1, false, CustomerCategory.WHOLESALE, "200.00");
        expect(calc, "500", 1, false, CustomerCategory.WHOLESALE, "50.00");
        expect(calc, "80", 1, false, CustomerCategory.EMPLOYEE, "24.00");

        // partner ще не підключений
        try {
            calc.calculateDiscount(order("200", 1, false), CustomerCategory.PARTNER);
            throw new IllegalStateException("Мала бути помилка");
        } catch (IllegalArgumentException expected) {
            // так і має бути
        }

        // розширення: нова знижка без змін у калькуляторі
        all.put(CustomerCategory.PARTNER, o -> Discounts.percent(o, "0.25"));
        expect(new DiscountCalculator(all), "200", 1, false, CustomerCategory.PARTNER, "50.00");

        System.out.println("Task 2 (OCP): OK");
    }

    private static Order order(String amount, int items, boolean first) {
        return new Order("test", new BigDecimal(amount), items, first);
    }

    private static void expect(DiscountCalculator calc, String amount, int items, boolean first,
                               CustomerCategory category, String expected) {
        BigDecimal actual = calc.calculateDiscount(order(amount, items, first), category);
        if (actual.compareTo(new BigDecimal(expected)) != 0) {
            throw new IllegalStateException("Очікувалось " + expected + ", отримано " + actual);
        }
    }
}

// збираємо знижки і перевіряємо що цифри як у старому коді
// partner додаємо одним рядком, калькулятор не чіпаємо

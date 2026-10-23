import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

// не знає які бувають клієнти, бере стратегію з мапи
public final class DiscountCalculator {

    private final Map<CustomerCategory, DiscountStrategy> strategies;

    public DiscountCalculator(Map<CustomerCategory, DiscountStrategy> strategies) {
        this.strategies = new EnumMap<>(strategies);
    }

    public BigDecimal calculateDiscount(Order order, CustomerCategory category) {
        DiscountStrategy strategy = strategies.get(category);
        if (strategy == null) {
            throw new IllegalArgumentException("Невідома категорія клієнта: " + category);
        }
        return strategy.discountFor(order).setScale(2, RoundingMode.HALF_UP);
    }
}

// клас не знає які бувають клієнти
// щоб додати нового клієнта цей файл міняти не треба

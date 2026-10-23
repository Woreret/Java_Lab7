import java.math.BigDecimal;

// точка розширення, нова знижка це нова реалізація
@FunctionalInterface
public interface DiscountStrategy {

    BigDecimal discountFor(Order order);
}

// правило для всіх знижок, отримали замовлення і повернули суму знижки

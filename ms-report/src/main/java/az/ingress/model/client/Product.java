package az.ingress.model.client;
import java.math.BigDecimal;
public record Product(
        Long id,
        String name,
        Integer stock
) {}
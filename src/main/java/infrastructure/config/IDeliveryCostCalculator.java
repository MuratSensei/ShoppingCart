package infrastructure.config;

import domain.Delivery;
import domain.dto.ProductDto;

import java.util.List;

public interface IDeliveryCostCalculator {

    public double calculateFor(Delivery delivery, List<ProductDto> products);
}

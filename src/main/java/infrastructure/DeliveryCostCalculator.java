package infrastructure;

import domain.Delivery;
import domain.dto.ProductDto;
import infrastructure.config.IDeliveryCostCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeliveryCostCalculator implements IDeliveryCostCalculator {

    public double calculateFor(Delivery delivery, List<ProductDto> products) {

        List<String> categoryList = new ArrayList<>();
        for (ProductDto productDto : products) {
            categoryList.add(productDto.getProduct().getCategory().getTitle());
        }
        List<String> uniqueList = new ArrayList<>(new HashSet<>(categoryList));

        double numberOfDeliveries = uniqueList.size();
        double numberOfProducts = products.size();

        return ((delivery.getCostPerDelivery() * numberOfDeliveries)
                + (delivery.getCostPerProduct() * numberOfProducts)
                + delivery.getFixedCost());
    }
}

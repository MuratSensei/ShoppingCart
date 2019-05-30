package infrastructure;

import domain.Category;
import domain.Delivery;
import domain.Product;
import domain.dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class DeliveryCostCalculatorTests {

    @Test
    public void DeliveryCost_Successful_Calculate_Test() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();

        Delivery delivery = new Delivery(1, 1, 2.99);
        Category food = new Category("food");
        Product product = new Product("Apple", 100.0, food);
        ProductDto productDto  = new ProductDto(product, 3);

        double deliveryCost = deliveryCostCalculator.calculateFor(delivery, Arrays.asList(productDto));

        Assert.assertEquals(deliveryCost, 4.99, 0);
    }
}

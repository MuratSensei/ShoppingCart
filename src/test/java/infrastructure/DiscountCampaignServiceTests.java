package infrastructure;

import domain.Campaign;

import domain.Category;
import domain.Product;
import domain.dto.ProductDto;
import domain.enums.DiscountType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class DiscountCampaignServiceTests {

    @Test
    public void DiscountCampaign_Successful_Calculate_Test() {
        DiscountCampaignService discountCampaignService = new DiscountCampaignService();

        Category food = new Category("food");
        Category fruit = new Category("fruit", food);
        Category clothes = new Category("clothes");
        Category drink = new Category("drink");

        Campaign rateCampaign = new Campaign(food, 20.0, 3, DiscountType.Rate);
        Campaign amountCampaign = new Campaign(clothes, 5.0, 5, DiscountType.Amount);
        Campaign amountCampaign2 = new Campaign(drink, 5.0, 5, DiscountType.Amount);

        Product apple = new Product("Apple", 100.0, fruit);
        Product rice = new Product("Rice", 50.0, food);
        Product hat = new Product("Hat", 150.0, clothes);
        Product shirt = new Product("Shirt", 150.0, clothes);
        Product juice = new Product("Juice", 150.0, drink);

        List<ProductDto> products = new ArrayList<>();

        products.add(new ProductDto(apple, 7));
        products.add(new ProductDto(rice, 7));
        products.add(new ProductDto(hat, 7));
        products.add(new ProductDto(shirt, 7));
        products.add(new ProductDto(juice, 7));

        double discountCampaign = discountCampaignService.netDiscount(products, rateCampaign, amountCampaign, amountCampaign2);

        Assert.assertEquals(discountCampaign, 3350, 0);
    }
}

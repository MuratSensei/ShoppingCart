package domain;

import domain.config.IShoppingCart;
import domain.dto.ProductDto;
import infrastructure.DeliveryCostCalculator;
import infrastructure.DiscountCampaignService;
import infrastructure.DiscountCouponService;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements IShoppingCart {

    private List<ProductDto> products;
    private double netPrice;
    private DiscountCampaignService discountCampaignService;
    private DiscountCouponService discountCouponService;
    private DeliveryCostCalculator deliveryCostCalculator;
    private boolean calculatorStatus;
    private double campaignDiscount;
    private double couponDiscount;
    private double deliveryCost;

    public ShoppingCart() {
        products = new ArrayList<>();
        netPrice = 0;
        discountCampaignService = new DiscountCampaignService();
        discountCouponService = new DiscountCouponService();
        deliveryCostCalculator = new DeliveryCostCalculator();
        calculatorStatus = true;
        campaignDiscount = 0;
        couponDiscount = 0;
        deliveryCost = 0;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void addProduct(Product product, int quantity) {
        this.products.add(new ProductDto(product, quantity));
    }

    public void getCampaignDiscount(Campaign... campaigns) {
        netPriceCalculator();
        campaignDiscount = discountCampaignService.netDiscount(products, campaigns);
    }

    public void getCouponDiscount(Coupon... coupons) {
        netPriceCalculator();
        couponDiscount = discountCouponService.netDiscount(netPrice, coupons);
    }

    public void getDeliveryCost(Delivery delivery) {
        deliveryCost = deliveryCostCalculator.calculateFor(delivery, products);
    }

    public double getTotalAmountAfterDiscounts() {
        return netPrice;
    }

    public void print() {

        for (ProductDto product : products) {
            System.out.println("CategoryName: " + product.getProduct().getCategory().getTitle() + " | " +
                    "ProductName: " + product.getProduct().getTitle() + " | " +
                    "Quantity: " + product.getQuantity());
        }
        System.out.println("UnitPrice: " + netPriceCalculator() + " | " +
                "TotalPrice: " + (netPriceCalculator() - campaignDiscount - couponDiscount) + " | " +
                "TotalDiscount: " + (campaignDiscount + couponDiscount));
        System.out.println("DeliveryCost: " + deliveryCost);
    }

    private double netPriceCalculator() {
        if (calculatorStatus) {
            netPrice = products.stream()
                    .map(p -> p.getProduct().getPrice() * p.getQuantity())
                    .reduce(0.0, (a, b) -> a + b);

            calculatorStatus = false;

            return netPrice;
        }
        return netPrice;
    }
}


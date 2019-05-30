package application;

import domain.*;
import domain.enums.DiscountType;

public class Main {

    public static void main(String[] args) {
        System.out.println("ShoppingCart Started!");

        Category food = new Category("food");
        Category fruit = new Category("fruit", food);
        Category clothes = new Category("clothes");

        Campaign rateCampaign = new Campaign(food, 20.0, 3, DiscountType.Rate);
        Campaign amountCampaign = new Campaign(clothes, 5.0, 5, DiscountType.Amount);

        Coupon couponRate = new Coupon(100, 25, DiscountType.Rate);
        Coupon couponAmount = new Coupon(500, 50, DiscountType.Amount);

        Product apple = new Product("Apple", 100.0, fruit);
        Product rice = new Product("Rice", 50.0, food);
        Product hat = new Product("Hat", 150.0, clothes);

        Delivery delivery = new Delivery(3,1,2.99);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.addProduct(apple,6);
        shoppingCart.addProduct(rice,1);
        shoppingCart.addProduct(hat,4);

        shoppingCart.getCampaignDiscount(rateCampaign, amountCampaign);
        shoppingCart.getCouponDiscount(couponRate, couponAmount);
        shoppingCart.getDeliveryCost(delivery);

        shoppingCart.print();
    }
}

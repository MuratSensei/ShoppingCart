package domain;

import domain.enums.DiscountType;

public class Campaign {

    private Category category;
    private double discount;
    private int quantity;
    private DiscountType discountType;

    public Campaign(Category category, double discount, int quantity, DiscountType discountType) {
        this.category = category;
        this.discount = discount;
        this.quantity = quantity;
        this.discountType = discountType;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}

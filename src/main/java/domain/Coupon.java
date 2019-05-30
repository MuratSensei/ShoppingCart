package domain;

import domain.enums.DiscountType;

public class Coupon {

    private int limit;
    private int discount;
    private DiscountType discountType;

    public Coupon(int limit, int discount, DiscountType discountType) {
        this.limit = limit;
        this.discount = discount;
        this.discountType = discountType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}

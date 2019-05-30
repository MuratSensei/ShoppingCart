package infrastructure.config;

import domain.Coupon;

public interface IDiscountCouponService {

    public double netDiscount(double netPrice, Coupon... coupons);
}

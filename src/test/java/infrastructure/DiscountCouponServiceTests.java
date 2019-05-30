package infrastructure;

import domain.Coupon;
import domain.enums.DiscountType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DiscountCouponServiceTests {

    @Test
    public void DiscountCoupon_Successful_Calculate_Test() {
        DiscountCouponService discountCouponService = new DiscountCouponService();

        Coupon couponRate = new Coupon(100, 25, DiscountType.Rate);
        Coupon couponAmount = new Coupon(500, 50, DiscountType.Amount);

        double discountCoupon = discountCouponService.netDiscount(1500, couponRate, couponAmount);

        Assert.assertEquals(discountCoupon, 50, 0);
    }
}

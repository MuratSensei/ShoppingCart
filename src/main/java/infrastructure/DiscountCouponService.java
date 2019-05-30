package infrastructure;

import domain.Coupon;
import domain.enums.DiscountType;
import infrastructure.config.IDiscountCouponService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscountCouponService implements IDiscountCouponService {

    private List<Double> discounts = new ArrayList<>();

    public double netDiscount(double netPrice, Coupon... coupons) {

        for (Coupon coupon : coupons) {

            if (coupon.getDiscountType().equals(DiscountType.Amount)
                    && (netPrice > coupon.getLimit())) {

                discounts.add((double) coupon.getDiscount());

            } else if (coupon.getDiscountType().equals(DiscountType.Rate)
                    && (netPrice > coupon.getLimit())) {

                discounts.add((double) (coupon.getDiscount() / 100));
            }
        }

        Collections.sort(discounts);
        return discounts.get(discounts.size()-1);
    }
}

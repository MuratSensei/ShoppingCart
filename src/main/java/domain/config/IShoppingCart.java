package domain.config;

import domain.Campaign;
import domain.Coupon;
import domain.Delivery;

public interface IShoppingCart {

    public void getCampaignDiscount(Campaign... campaigns);

    public void getCouponDiscount(Coupon... coupons);

    public void getDeliveryCost(Delivery delivery);

    public double getTotalAmountAfterDiscounts();

    public void print();
}

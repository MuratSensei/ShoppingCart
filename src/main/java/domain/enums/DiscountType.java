package domain.enums;

public enum DiscountType {

    Amount(1),
    Rate(2);

    private int value;

    public int getValue()
    {
        return this.value;
    }

    DiscountType(int value) {
        this.value = value;
    }
}

package infrastructure;

import domain.Campaign;
import domain.Category;
import domain.dto.ProductDto;
import domain.dto.TotalProductsDto;
import domain.enums.DiscountType;
import infrastructure.config.IDiscountCampignService;

import java.util.ArrayList;
import java.util.List;

public class DiscountCampaignService implements IDiscountCampignService {

    private List<Double> discounts = new ArrayList<>();
    private List<TotalProductsDto> amountProducts = new ArrayList<>();
    private List<TotalProductsDto> rateProducts = new ArrayList<>();

    public double netDiscount(List<ProductDto> products, Campaign... campaigns) {

        for (Campaign campaign : campaigns) {

            if (campaign.getDiscountType().equals(DiscountType.Amount)) {

                for (ProductDto productDto : products) {

                    if (campaign.getCategory().equals(productDto.getProduct().getCategory())
                            || campaign.getCategory().equals(productDto.getProduct().getCategory().getParentCategory())) {

                        totalAmountProducts(productDto.getProduct().getPrice(),
                                productDto.getQuantity(),
                                productDto.getProduct().getCategory());
                    }
                }
                discounts.add(amountCalculator(amountProducts, campaign));

            } else if (campaign.getDiscountType().equals(DiscountType.Rate)) {

                for (ProductDto productDto : products) {

                    if (campaign.getCategory().equals(productDto.getProduct().getCategory())
                            || campaign.getCategory().equals(productDto.getProduct().getCategory().getParentCategory())) {

                        totalRateProducts(productDto.getProduct().getPrice(),
                                productDto.getQuantity(),
                                productDto.getProduct().getCategory());
                    }
                }
                discounts.add(rateCalculator(rateProducts, campaign));
            }
        }

        return discounts.stream()
                .reduce(0.0, (a, b) -> a + b);
    }

    private void totalAmountProducts(double price, double quantity, Category category) {

        for (TotalProductsDto t : amountProducts) {
            if (t.getCategory().equals(category)) {

                t.setTotalPrice(price);
                t.setTotalQuantity(quantity);

                break;

            } else {
                TotalProductsDto totalProductsDto = new TotalProductsDto();

                totalProductsDto.setTotalPrice(price);
                totalProductsDto.setTotalQuantity(quantity);
                totalProductsDto.setCategory(category);

                amountProducts.add(totalProductsDto);

                break;
            }
        }

        if (amountProducts.size() == 0) {
            TotalProductsDto totalProductsDto = new TotalProductsDto();

            totalProductsDto.setTotalPrice(price);
            totalProductsDto.setTotalQuantity(quantity);
            totalProductsDto.setCategory(category);

            amountProducts.add(totalProductsDto);
        }
    }

    private void totalRateProducts(double price, double quantity, Category category) {

        for (TotalProductsDto t : rateProducts) {
            if (t.getCategory().equals(category)) {

                t.setTotalPrice(price);
                t.setTotalQuantity(quantity);

                break;

            } else {
                TotalProductsDto totalProductsDto = new TotalProductsDto();

                totalProductsDto.setTotalPrice(price);
                totalProductsDto.setTotalQuantity(quantity);
                totalProductsDto.setCategory(category);

                rateProducts.add(totalProductsDto);

                break;
            }
        }

        if (rateProducts.size() == 0) {
            TotalProductsDto totalProductsDto = new TotalProductsDto();

            totalProductsDto.setTotalPrice(price);
            totalProductsDto.setTotalQuantity(quantity);
            totalProductsDto.setCategory(category);

            rateProducts.add(totalProductsDto);
        }
    }

    private double amountCalculator(List<TotalProductsDto> amountProducts, Campaign campaign) {
        double totalCampaignPrice = amountProducts.stream()
                .map(p -> p.getTotalPrice() * p.getTotalQuantity())
                .reduce(0.0, (a, b) -> a + b);

        double totalProductsQuantity = amountProducts.stream()
                .map(p -> p.getTotalQuantity())
                .reduce(0.0, (a, b) -> a + b);

        if (totalProductsQuantity > campaign.getQuantity()) {
            return totalCampaignPrice - campaign.getDiscount();
        }
        return 0;
    }

    private double rateCalculator(List<TotalProductsDto> rateProducts, Campaign campaign) {
        double totalCampaignPrice = rateProducts.stream()
                .map(p -> p.getTotalPrice() * p.getTotalQuantity())
                .reduce(0.0, (a, b) -> a + b);

        double totalProductsQuantity = rateProducts.stream()
                .map(p -> p.getTotalQuantity())
                .reduce(0.0, (a, b) -> a + b);

        if (totalProductsQuantity > campaign.getQuantity()) {
            return totalCampaignPrice * campaign.getDiscount() / 100;
        }
        return 0;
    }
}

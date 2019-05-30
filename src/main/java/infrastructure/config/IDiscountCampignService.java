package infrastructure.config;

import domain.Campaign;
import domain.Category;
import domain.dto.ProductDto;
import domain.dto.TotalProductsDto;

import java.util.List;

public interface IDiscountCampignService {

    public double netDiscount(List<ProductDto> products, Campaign... campaigns);
}

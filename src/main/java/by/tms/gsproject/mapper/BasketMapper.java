package by.tms.gsproject.mapper;

import by.tms.gsproject.api.basket.BasketResponse;
import by.tms.gsproject.entity.basket.Basket;

public class BasketMapper {
    public BasketResponse toResponse(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setOrderId(basket.getOrderId());
        basketResponse.setProductId(basket.getProductId());
        basketResponse.setCount(basket.getCount());
        return basketResponse;
    }
}
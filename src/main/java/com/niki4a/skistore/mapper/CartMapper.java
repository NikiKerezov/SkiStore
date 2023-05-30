package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface CartMapper {
    CartMapper CART_MAPPER = Mappers.getMapper(CartMapper.class);
    Cart fromCartResource(CartResource cartResource);
    CartResource toCartResource(Cart cart);
    List<Cart> fromCartResourceList(List<CartResource> cartResourceList);
    List<CartResource> toCartResourceList(List<Cart> cartList);
}

package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Cart;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.repository.CartRepository;
import com.niki4a.skistore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.niki4a.skistore.mapper.CartMapper.CART_MAPPER;
import static com.niki4a.skistore.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    @Override
    public List<CartResource> findAll() {
        return CART_MAPPER.toCartResourceList(cartRepository.findAll());
    }

    @Override
    public CartResource findById(Long id) {
        return CART_MAPPER.toCartResource(cartRepository.findByCartId(id));
    }

    @Override
    public CartResource save(CartResource cartResource) {
        Cart cart = CART_MAPPER.fromCartResource(cartResource);
        return CART_MAPPER.toCartResource(cartRepository.save(cart));
    }

    @Override
    public CartResource update(CartResource cartResource, Long id) {
        Cart cart = cartRepository.findByCartId(id);
        Set<Product> productList = new HashSet<>();
        for (ProductResource product : cartResource.getProducts()) {
            productList.add(PRODUCT_MAPPER.fromProductResource(product));
        }

        cart.setProducts(productList);
        cart.setTotalAmount(cartResource.getTotalAmount());
        return CART_MAPPER.toCartResource(cartRepository.save(cart));
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteByCartId(id);
    }
}

package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Cart;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.mapper.CartMapper;
import com.niki4a.skistore.mapper.ProductMapper;
import com.niki4a.skistore.repository.CartRepository;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.service.CartService;
import com.niki4a.skistore.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    private final CartMapper CART_MAPPER;

    @Override
    public List<CartResource> findAll() {
        return CART_MAPPER.toCartResourceList(cartRepository.findAll());
    }

    @Override
    public CartResource findById(Long id) {
        return CART_MAPPER.toCartResource(cartRepository.findByCartId(id).get());
    }

    @Override
    public CartResource save(CartResource cartResource) {
        Cart cart = CART_MAPPER.fromCartResource(cartResource);

        for (ProductResource product : cartResource.getProducts()) {
            productRepository.findByProductName(product.getProductName()).ifPresentOrElse(
                    cart::addProduct,
                    () -> {
                        throw new IllegalArgumentException("Product not found");
                    });
        }

        return CART_MAPPER.toCartResource(cartRepository.save(cart));
    }

    @Override
    public CartResource update(CartResource cartResource, Long id) {
        Cart cart = cartRepository.findByCartId(id).get();
        HashSet<Product> productList = new HashSet<>();
        for (ProductResource product : cartResource.getProducts()) {
            productRepository.findByProductName(product.getProductName()).ifPresentOrElse(
                    cart::addProduct,
                    () -> {
                        throw new IllegalArgumentException("Product not found");
                    });
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

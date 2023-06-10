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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartResource> findAll() {
        return cartMapper.toCartResourceList(cartRepository.findAll());
    }

    @Override
    public CartResource findById(Long id) {
        return cartMapper.toCartResource(cartRepository.findByCartId(id).orElseThrow(() -> new IllegalArgumentException("Cart not found")));
    }

    @Override
    public CartResource save(CartResource cartResource) {
        Cart cart = cartMapper.fromCartResource(cartResource);

        if (cartResource.getProducts() == null) {
            cart.setProducts(new HashSet<>());
        }

        for (ProductResource product : cartResource.getProducts()) {
            Product existingProduct = productRepository.findByProductName(product.getProductName())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            cart.addProduct(existingProduct);
        }

        return cartMapper.toCartResource(cartRepository.save(cart));
    }

    @Override
    public CartResource update(CartResource cartResource, Long id) {
        Cart cart = cartRepository.findByCartId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Set<Product> productList = new HashSet<>();
        for (ProductResource product : cartResource.getProducts()) {
            Product existingProduct = productRepository.findByProductName(product.getProductName())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            productList.add(existingProduct);
        }

        cart.setProducts(productList);
        cart.setTotalAmount(cartResource.getTotalAmount());

        return cartMapper.toCartResource(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartRepository.deleteByCartId(id);
    }
}

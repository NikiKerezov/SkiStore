package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> create(@RequestBody CartResource cartResource) {
        CartResource newCartResource = cartService.save(cartResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/cart/{id}")
                        .buildAndExpand(newCartResource.getCartId())
                        .toUri()
        ).body(newCartResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CartResource cartResource, @PathVariable Long id) {
        return ResponseEntity.ok(cartService.update(cartResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

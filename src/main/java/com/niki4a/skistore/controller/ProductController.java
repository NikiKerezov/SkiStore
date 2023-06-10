package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductResource productResource) {
        ProductResource newProductResource = productService.save(productResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/product/{id}")
                        .buildAndExpand(newProductResource.getProductId())
                        .toUri()
        ).body(newProductResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductResource productResource, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(productResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

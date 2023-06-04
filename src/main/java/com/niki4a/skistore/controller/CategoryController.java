package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryResource categoryResource) {
        CategoryResource newCategoryResource = categoryService.save(categoryResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/category/{id}")
                        .buildAndExpand(newCategoryResource.getCategoryId())
                        .toUri()
        ).body(newCategoryResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryResource categoryResource, @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.update(categoryResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

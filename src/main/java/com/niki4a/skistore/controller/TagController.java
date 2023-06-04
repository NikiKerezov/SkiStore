package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.repository.TagRepository;
import com.niki4a.skistore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TagResource tagResource) {
        TagResource newTagResource = tagService.save(tagResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/tag/{id}")
                        .buildAndExpand(newTagResource.getTagId())
                        .toUri()
        ).body(newTagResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TagResource tagResource, @PathVariable Long id) {
        return ResponseEntity.ok(tagService.update(tagResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

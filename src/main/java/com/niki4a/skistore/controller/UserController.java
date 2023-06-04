package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserResource userResource) {
        UserResource newUserResource = userService.save(userResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/user/{id}")
                        .buildAndExpand(newUserResource.getUserId())
                        .toUri()
        ).body(newUserResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserResource userResource, @PathVariable Long id) {
        return ResponseEntity.ok(userService.update(userResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.niki4a.skistore.controller;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.entity.CustomerOrder;
import com.niki4a.skistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderResource orderResource) {
        OrderResource newCustomerOrder = orderService.save(orderResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/order/{id}")
                        .buildAndExpand(newCustomerOrder.getOrderId())
                        .toUri()
        ).body(newCustomerOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderResource orderResource, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.update(orderResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/audits/{id}")
    public ResponseEntity<?> findAllAudits(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findAllAudits(id));
    }
}

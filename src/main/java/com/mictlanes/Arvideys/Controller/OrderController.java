package com.mictlanes.Arvideys.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mictlanes.Arvideys.Models.Order;
import com.mictlanes.Arvideys.Services.OrderServices;

import java.util.List;

@RestController
@RequestMapping(path="/api/orders")
public class OrderController {

	private final OrderServices orderServices;
	
    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    // GET all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderServices.findAllOrders();
    }

    // GET one order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderServices.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/{id_order}/products/{id_product}")
    public Order addProductToOrder(
    		@PathVariable Long id_order, 
    		@PathVariable Long id_product, 
    		@RequestParam int qty_product, 
    		@RequestParam double total_price
    		) {
        return orderServices.addProductToOrder(id_order, id_product, qty_product, total_price);
    }

    @DeleteMapping("/{id_order}/products/{id_product}")
    public Order removeProductFromOrder(@PathVariable Long id_order, @PathVariable Long id_product) {
        return orderServices.removeProductFromOrder(id_order, id_product);
    }
}
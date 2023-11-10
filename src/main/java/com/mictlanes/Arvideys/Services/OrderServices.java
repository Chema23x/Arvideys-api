package com.mictlanes.Arvideys.Services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mictlanes.Arvideys.Models.Order;
import com.mictlanes.Arvideys.Models.Order_has_Product;
import com.mictlanes.Arvideys.Models.Product;
import com.mictlanes.Arvideys.Repository.OrderRepository;

import com.mictlanes.Arvideys.Repository.ProductRepository;

@Service
public class OrderServices {

	  @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    // Get all orders
	    public List<Order> findAllOrders() {
	        return orderRepository.findAll();
	    }

	    // Get one order by ID
	    public Optional<Order> findOrderById(Long id) {
	        return orderRepository.findById(id);
	    }

    
	    @Transactional
	    public Order addProductToOrder(Long orderId, Long productId, int qty_product, double total_price) {
	        Optional<Order> orderOptional = orderRepository.findById(orderId);
	        Optional<Product> productOptional = productRepository.findById(productId);
	        if (orderOptional.isPresent() && productOptional.isPresent()) {
	        	Order order = orderOptional.get();
	            Product product = productOptional.get();
	            order.addProduct(product, qty_product, total_price);
	            return orderRepository.save(order);
	        } else {
	            throw new EntityNotFoundException("Order or Product not found");
	        }
	    }
	    
	    @Transactional
	    public Order removeProductFromOrder(Long orderId, Long productId) {
	        Optional<Order> orderOptional = orderRepository.findById(orderId);
	        Optional<Product> productOptional = productRepository.findById(productId);
	        if (orderOptional.isPresent() && productOptional.isPresent()) {
	            Order order = orderOptional.get();
	            Product product = productOptional.get();
	            order.removeProduct(product);
	            return orderRepository.save(order);
	        } else {
	            // Handle the case where either the order or the product doesn't exist
	            throw new RuntimeException("Order or Product not found");
	        }
	    }
}
	
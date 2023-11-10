package com.mictlanes.Arvideys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mictlanes.Arvideys.Models.Product;
import com.mictlanes.Arvideys.Services.ProductServices;
import com.mictlanes.Arvideys.exception.ResourceNotFoundException;


@RestController
@RequestMapping(path="/api/products")
public class ProductController {
	
	private final ProductServices productServices;
	
	 @Autowired
	 public ProductController(ProductServices productServices) {
		 this.productServices = productServices;
	 }
	 
	 
	// GET all Products
	    @GetMapping
	    public ResponseEntity<List<Product>> getAllProducts() {
	        List<Product> products = productServices.getAllProducts();
	        return ResponseEntity.ok(products);
	    }

	 // GET a single Product by id
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productServices.getProductById(id);
	        if (product != null) {
	            return ResponseEntity.ok(product);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	 // POST a new Product
	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product, 
	                                           @RequestParam Long categoryId) {
	        try {
	            Product newProduct = productServices.createProduct(product, categoryId);
	            return ResponseEntity.ok(newProduct);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // PUT to update a Product
	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, 
	                                           @RequestBody Product productDetails, 
	                                           @RequestParam Long categoryId){
	        try {
	            Product updatedProduct = productServices.updateProduct(id, productDetails, categoryId);
	            return ResponseEntity.ok(updatedProduct);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	 // DELETE a book
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        try {
	            productServices.deleteProduct(id);
	            return ResponseEntity.ok().build();
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    

}
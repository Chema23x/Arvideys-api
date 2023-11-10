package com.mictlanes.Arvideys.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mictlanes.Arvideys.Models.Category;
import com.mictlanes.Arvideys.Models.Product;
import com.mictlanes.Arvideys.Repository.CategoryRepository;
import com.mictlanes.Arvideys.Repository.ProductRepository;
import com.mictlanes.Arvideys.exception.*;

@Service
public class ProductServices {
	
	private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServices(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
    

    // Método para obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
	// Método para obtener un producto por su ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
    }
    
    
    // Create a new Product
    @Transactional
    public Product createProduct(Product product, Long categoryId) {
    	Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        
        product.setCategory(category);
        return productRepository.save(product);
    }

 // Update a product  
    @Transactional
    public Product updateProduct(Long id, Product productDetails, Long categoryId) {
        Product product = getProductById(id); 

        product.setQuantity(productDetails.getQuantity());
        product.setSize(productDetails.getSize());
        product.setembroidery_type(productDetails.getembroidery_type());
        product.setproduct_color(productDetails.getproduct_color());
        product.setPrice(productDetails.getPrice());
        product.setproduct_image(productDetails.getproduct_image());

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        product.setCategory(category);

        // Guarda los cambios en la base de datos
        product = productRepository.save(product);

        return product;
    }


    // Método para eliminar un producto por su ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

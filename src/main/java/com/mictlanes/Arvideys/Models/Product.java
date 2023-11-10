package com.mictlanes.Arvideys.Models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id_product; // Corregido el nombre de la propiedad

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "size", length = 10, nullable = false)
    private String size;

    @Column(name = "embroidery_type", length = 50, nullable = false)
    private String embroidery_type;

    @Column(name = "product_color", length = 30, nullable = false)
    private String product_color;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "product_image", length = 255, nullable = false)
    private String product_image;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Category_id_category", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Order_has_Product> orderHasProducts = new HashSet<>();

    // Constructor vacío
    public Product() {
    }

    // Constructor con argumentos
    
    public Product(Long id_product, Integer quantity, String size, String embroidery_type, String product_color,
			BigDecimal price, String product_image, Category category) {
		super();
		this.id_product = id_product;
		this.quantity = quantity;
		this.size = size;
		this.embroidery_type = embroidery_type;
		this.product_color = product_color;
		this.price = price;
		this.product_image = product_image;
		this.category = category;
	}

	// Constructor sin ID para generar un nuevo producto
    
    public Product(Integer quantity, String size, String embroidery_type, String product_color,
			BigDecimal price, String product_image, Category category) {
		super();
		this.quantity = quantity;
		this.size = size;
		this.embroidery_type = embroidery_type;
		this.product_color = product_color;
		this.price = price;
		this.product_image = product_image;
		this.category = category;
	}

    // Métodos
    public void addOrder(Order order, int quantity) {
    	Order_has_Product.OrderProductId orderProductId = new Order_has_Product.OrderProductId();
    	Order_has_Product orderHasProduct = new Order_has_Product();
		orderHasProducts.add(orderHasProduct);
		order.getOrderHasProducts().add(orderHasProduct);
    }

    public void removeOrder(Order order) {
    	for (Iterator<Order_has_Product> iterator = orderHasProducts.iterator(); iterator.hasNext();) {
    		Order_has_Product orderHasProduct = iterator.next();

			if (orderHasProduct.getProduct().equals(this) && orderHasProduct.getOrder().equals(order)) {
				iterator.remove();
				orderHasProduct.getOrder().getOrderHasProducts().remove(orderHasProduct);
				orderHasProduct.setOrder(null);
				orderHasProduct.setProduct(null);
			}
		}
    }
    
    // GET SET

	public Long getId_product() {
		return id_product;
	}

	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getembroidery_type() {
		return embroidery_type;
	}

	public void setembroidery_type(String embroidery_type) {
		this.embroidery_type = embroidery_type;
	}

	public String getproduct_color() {
		return product_color;
	}

	public void setproduct_color(String product_color) {
		this.product_color = product_color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getproduct_image() {
		return product_image;
	}

	public void setproduct_image(String product_image) {
		this.product_image = product_image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Order_has_Product> getOrderHasProducts() {
		return orderHasProducts;
	}

	public void setOrderHasProducts(Set<Order_has_Product> orderHasProducts) {
		this.orderHasProducts = orderHasProducts;
	}

	
	@Override
	public String toString() {
		return "Product [id_product=" + id_product + ", quantity=" + quantity + ", size=" + size + ", embroidery_type="
				+ embroidery_type + ", product_color=" + product_color + ", price=" + price + ", product_image="
				+ product_image + ", category=" + category + ", orderHasProducts=" + orderHasProducts + "]";
	}

	public void setProductImage(String getproduct_image) {
		// TODO Auto-generated method stub
		
	}

        
}
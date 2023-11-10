package com.mictlanes.Arvideys.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "`Order`") // Cambiado a minúsculas
public class Order { // Cambiado a singular

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id_order;

    @Column(name = "order_date", nullable = false)
    private String order_date;

    @Column(name = "order_status", nullable = false)
    private String order_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id_user", referencedColumnName = "id_user",nullable = false) // Corregido el nombre de la columna
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order_has_Product> orderHasProducts = new HashSet<>();
    
    //Constructor Parametros order vacio
    public Order() {}
    //Constructor con parametros Order
    public Order(Long id_order, String order_date, String order_status, User user,
			Set<Order_has_Product> orderHasProducts) {
		super();
		this.id_order = id_order;
		this.order_date = order_date;
		this.order_status = order_status;
		this.user = user;
		this.orderHasProducts = orderHasProducts;
	}
    //NECESARIO EXPORTAR PRODUCTOS DESDE CATEGORIA
	// Method to add a book to the order
    
    @Transactional
    public void addProduct(Product product, int qty_product, double total_price) {
    	Order_has_Product.OrderProductId orderProductId = new Order_has_Product.OrderProductId(this.getId_order(), product.getId_product());
    	Order_has_Product orderHasProduct = new Order_has_Product(orderProductId, this, product, qty_product, total_price);
        this.orderHasProducts.add(orderHasProduct);
        product.getOrderHasProducts().add(orderHasProduct);
    }


	public void removeProduct(Product product) {
		Order_has_Product.OrderProductId orderProductId = new Order_has_Product.OrderProductId(this.getId_order(), product.getId_product());
		Order_has_Product orderHasProduct = new Order_has_Product(orderProductId, this, product, 0, 0);
        product.getOrderHasProducts().remove(orderHasProduct);
        this.orderHasProducts.remove(orderHasProduct);
    }
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Order_has_Product> getOrderHasProducts() {
		return orderHasProducts;
	}
	public void setOrderHasProducts(Set<Order_has_Product> orderHasProducts) {
		this.orderHasProducts = orderHasProducts;
	}
    
   

}
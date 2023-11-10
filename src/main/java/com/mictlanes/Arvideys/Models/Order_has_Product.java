package com.mictlanes.Arvideys.Models;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;




@Entity
@Table(name = "Order_has_Product") // Nombre de la tabla corregido
public class Order_has_Product {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "Order_id_order") // Nombre de la columna corregido
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id_product") // Nombre de la columna corregido
    private Product product;

    @Column(name = "qty_product")
    private int qty_product;
    
    @Column(name = "total_price")
    private double total_price;

    //Constructor
    public Order_has_Product(OrderProductId id, Order order, Product product, int qty_product, double total_price) {
    	super();
    	this.id = id;
    	this.order = order;
    	this.product = product;
    	this.qty_product = qty_product;
    	this.total_price = total_price;
    }
    
    public Order_has_Product() {
    	
    }
    
    //METODOS

	// Clase de clave embebida
    @Embeddable
    public static class OrderProductId implements Serializable {

        @Column(name = "Order_id_order") // Nombre de la columna corregido
        private Long orderId;

        @Column(name = "Product_id_product") // Nombre de la columna corregido
        private Long productId;


        public OrderProductId(Long orderId, Long productId) {
            this.orderId = orderId;
            this.productId = productId;
        }
        public OrderProductId() {}
        
     // Override equals and hashCode
     		@Override
     		public boolean equals(Object o) {
     			if (this == o)
     				return true;
     			if (o == null || getClass() != o.getClass())
     				return false;
     			OrderProductId that = (OrderProductId) o;
     			return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
     		}

     		@Override
     		public int hashCode() {
     			return Objects.hash(orderId, productId);
     		}
     	}
        
        
        //Getter and Setters

	public OrderProductId getId() {
		return id;
	}

	public void setId(OrderProductId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty_product() {
		return qty_product;
	}

	public void setQty_product(int qty_product) {
		this.qty_product = qty_product;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "Order_has_Product [id=" + id + ", order=" + order + ", product=" + product + ", qty_product="
				+ qty_product + ", total_price=" + total_price + "]";
	}
    
}
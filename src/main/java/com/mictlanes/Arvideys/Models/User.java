package com.mictlanes.Arvideys.Models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id_user;
	@Column (name = "first_name", nullable=false)
	private String first_name;
	@Column (name = "last_name", nullable=false)
	private String last_name;
	@Column (name = "phone_number", nullable=false)
	private String phone_number;
	@Column (name = "email_address", nullable=false)
	private String email_address;
	@Column (name = "address", nullable=false)
	private String address;
	@Column (name = "password", nullable=false)
	private String password;
	
	// Anotaciones para hacer conexion entre nuestras tablas
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Discount_Codes_id_discount_codes", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DiscountCodes discount_codes;
	
	
	//Constructor Vacio
	public User() {
		
	}
	//SUPER CONSTRUCTOR
	public User(Long id_user, String first_name, String last_name, String phone_number, String email_address,
			String address, String password, DiscountCodes discount_codes) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.email_address = email_address;
		this.address = address;
		this.password = password;
		this.discount_codes = discount_codes;
	}
	//GET SET 
	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DiscountCodes getDiscount_codes() {
		return discount_codes;
	}

	public void setDiscount_codes(DiscountCodes discount_codes) {
		this.discount_codes = discount_codes;
	}
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", phone_number=" + phone_number + ", email_address=" + email_address + ", address=" + address
				+ ", password=" + password + ", discount_codes=" + discount_codes + "]";
	}
	
		
}
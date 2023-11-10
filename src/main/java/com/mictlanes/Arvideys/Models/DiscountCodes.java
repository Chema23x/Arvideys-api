package com.mictlanes.Arvideys.Models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Discount_Codes")
public class DiscountCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_codes")
    private Long id_discount_codes;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "used", nullable = false)
    private boolean used;

    @OneToMany(mappedBy = "discount_codes", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();
    public DiscountCodes() {

    }


    public DiscountCodes(Long id_discount_codes, String code, boolean used) {
        super();
        this.id_discount_codes = id_discount_codes;
        this.code = code;
        this.used = used;
    }

    // Methods to add and remove books if necessary
    public void addUser(User user) {
        users.add(user);
        user.setDiscount_codes(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setDiscount_codes(null);
    }

    public Long getId_discount_codes() {
        return id_discount_codes;
    }


    public void setId_discount_codes(Long id_discount_codes) {
        this.id_discount_codes = id_discount_codes;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public boolean isUsed() {
        return used;
    }


    public void setUsed(boolean used) {
        this.used = used;
    }


    @Override
    public String toString() {
        return "DiscountCodes [id_discount_codes=" + id_discount_codes + ", code=" + code + ", used=" + used + "]";
    }


}
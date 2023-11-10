package com.mictlanes.Arvideys.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mictlanes.Arvideys.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
package com.mictlanes.Arvideys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mictlanes.Arvideys.Models.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
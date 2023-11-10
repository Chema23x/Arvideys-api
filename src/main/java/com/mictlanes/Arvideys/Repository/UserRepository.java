package com.mictlanes.Arvideys.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mictlanes.Arvideys.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
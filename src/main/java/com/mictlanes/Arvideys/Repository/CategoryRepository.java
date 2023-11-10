package com.mictlanes.Arvideys.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mictlanes.Arvideys.Models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
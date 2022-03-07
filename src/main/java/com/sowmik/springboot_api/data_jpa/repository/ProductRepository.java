package com.sowmik.springboot_api.data_jpa.repository;

import com.sowmik.springboot_api.data_jpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

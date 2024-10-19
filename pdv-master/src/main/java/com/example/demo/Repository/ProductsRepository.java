package com.example.demo.Repository;

import com.example.demo.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findByNameContaining(String name);

    Optional<Products> findByCodigodebarras(String codigo);


}

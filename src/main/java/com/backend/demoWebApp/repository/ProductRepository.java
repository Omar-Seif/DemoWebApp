package com.backend.demoWebApp.repository;

import com.backend.demoWebApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // ← Marks this as a Spring Data repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // That's it! Spring Data JPA provides all CRUD operations automatically

    // You can add custom query methods later if needed
    // Example: List<Product> findByName(String name);
}

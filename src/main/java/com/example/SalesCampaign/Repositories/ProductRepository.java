package com.example.SalesCampaign.Repositories;

import com.example.SalesCampaign.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Custom query methods can be added here if needed
}


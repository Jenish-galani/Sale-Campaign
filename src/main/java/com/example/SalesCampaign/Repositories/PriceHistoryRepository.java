package com.example.SalesCampaign.Repositories;

import com.example.SalesCampaign.Models.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {

    // Method to find a PriceHistory by a specific date
    Optional<PriceHistory> findPriceHistoryByLocalDate(LocalDate localDate);

    // Method to find the latest PriceHistory entry for a given product and date
    @Query(value = "SELECT * FROM price_history WHERE product_id = :productId AND date = :date ORDER BY id DESC LIMIT 1", nativeQuery = true)
    PriceHistory findTopByProductIdAndDate(@Param("productId") int productId, @Param("date") LocalDate date);

    // Method to find all PriceHistory entries for a specific product
    @Query(value = "SELECT * FROM price_history WHERE product_id = :productId", nativeQuery = true)
    List<PriceHistory> findByProductId(@Param("productId") int productId);

    // Method to find the latest PriceHistory entry for a specific product, ordered by date
    @Query(value = "SELECT * FROM price_history WHERE product_id = :productId ORDER BY date DESC LIMIT 1", nativeQuery = true)
    PriceHistory findByProductIdOrderByDate(@Param("productId") int productId);

    // Method to find the latest PriceHistory entry for a specific product before a given date
    @Query(value = "SELECT * FROM price_history WHERE product_id = :productId AND date < :date ORDER BY date DESC LIMIT 1", nativeQuery = true)
    PriceHistory findByProductIdAndBeforeDate(@Param("productId") int productId, @Param("date") LocalDate date);
}



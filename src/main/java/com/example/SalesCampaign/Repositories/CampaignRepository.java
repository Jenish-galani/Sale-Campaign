package com.example.SalesCampaign.Repositories;

import com.example.SalesCampaign.Models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    // Method to retrieve all campaigns by their start date
    @Query(value = "SELECT * FROM campaigns WHERE start_date = :startDate", nativeQuery = true)
    List<Campaign> findAllByStartDate(@Param("startDate") LocalDate startDate);

    // Method to retrieve all campaigns by their end date
    @Query(value = "SELECT * FROM campaigns WHERE end_date = :endDate", nativeQuery = true)
    List<Campaign> findAllByEndDate(@Param("endDate") LocalDate endDate);
}

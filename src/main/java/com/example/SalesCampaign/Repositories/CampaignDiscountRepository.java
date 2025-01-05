package com.example.SalesCampaign.Repositories;

import com.example.SalesCampaign.Models.CampaignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignDiscountRepository extends JpaRepository<CampaignDiscount,Integer> {
    // Custom query methods can be added here if needed
}
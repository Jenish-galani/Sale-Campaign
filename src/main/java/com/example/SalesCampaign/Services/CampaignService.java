package com.example.SalesCampaign.Services;

import com.example.SalesCampaign.Models.Campaign;
import com.example.SalesCampaign.Models.CampaignDiscount;
import com.example.SalesCampaign.Models.ResponseDTO;
import com.example.SalesCampaign.Repositories.CampaignDiscountRepository;
import com.example.SalesCampaign.Repositories.CampaignRepository;
import com.example.SalesCampaign.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CampaignDiscountRepository campaignDiscountRepository;

    // Method to save a campaign along with its discounts
    public ResponseDTO<Campaign> saveCampaign(Campaign campaign) {
        try {
            for (CampaignDiscount discount : campaign.getCampaignDiscounts()) {
                discount.setCampaign(campaign);
            }
            return new ResponseDTO<>(campaignRepository.save(campaign), HttpStatus.OK, "Campaign saved successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save campaign: " + e.getMessage());
        }
    }

    // Method to retrieve all campaigns
    public ResponseDTO<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return new ResponseDTO<>(campaigns, HttpStatus.OK, "All campaigns retrieved successfully");
    }

}

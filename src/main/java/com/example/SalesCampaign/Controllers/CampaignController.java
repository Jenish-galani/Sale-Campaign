package com.example.SalesCampaign.Controllers;

import com.example.SalesCampaign.Models.Campaign;
import com.example.SalesCampaign.Models.ResponseDTO;
import com.example.SalesCampaign.Services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    // Endpoint to save a new campaign
    @PostMapping("saveCampaign")
    public ResponseDTO<Campaign> saveCampaign(@RequestBody Campaign campaign) {
        return campaignService.saveCampaign(campaign);
    }

    // Endpoint to retrieve all campaigns
    @GetMapping("getCampaign")
    public ResponseDTO<List<Campaign>> getCampaign() {
        return campaignService.getAllCampaigns();
    }
    
}

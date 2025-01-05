package com.example.SalesCampaign.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Unique identifier for the campaign

    @Column(name = "campaign_name")
    private String campaignName; // Name of the campaign

    @Column(name = "start_date")
    private LocalDate startDate; // Start date of the campaign

    @Column(name = "end_date")
    private LocalDate endDate; // End date of the campaign

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "campaign")
    @JsonManagedReference
    private List<CampaignDiscount> campaignDiscounts; // List of discounts associated with the campaign

    public Campaign() {
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", campaignName='" + campaignName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discounts=" + (campaignDiscounts != null ? campaignDiscounts.size() : 0) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }
}

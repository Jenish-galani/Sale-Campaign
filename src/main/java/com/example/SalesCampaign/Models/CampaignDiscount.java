package com.example.SalesCampaign.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "campaign_discounts")

public class CampaignDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the campaign discount

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonBackReference
    private Campaign campaign; // Associated campaign

    @Column(name = "product_id")
    private int productId; // ID of the product associated with the discount

    @Column(name = "discount")
    private float discount; // Discount value for the product

    public CampaignDiscount() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CampaignDiscount{" +
                "id=" + id +
                ", campaignId=" + (campaign != null ? campaign.getId() : "N/A") +
                ", productId=" + productId +
                ", discount=" + discount +
                '}';
    }
}

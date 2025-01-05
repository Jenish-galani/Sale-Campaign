package com.example.SalesCampaign.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "price_history")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the price history entry

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product; // Reference to the associated product

    @Column(name = "price")
    private double price; // Original price of the product

    @Column(name = "final_price")
    private double finalPrice; // Final price after applying discounts

    @Column(name = "discount_percentage")
    private double discountPercentage; // Percentage of discount applied

    @Column(name = "discount_amount")
    private double discountAmount; // Amount of discount applied

    @Column(name = "date")
    private LocalDate localDate; // Date when the price history entry was recorded

    public PriceHistory() {
    }

    public PriceHistory(Product product, double price, double finalPrice, double discountPercentage, double discountAmount, LocalDate localDate) {
        this.product = product;
        this.price = price;
        this.finalPrice = finalPrice;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.localDate = localDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", product=" + product +
                ", price=" + price +
                ", finalPrice=" + finalPrice +
                ", discountPercentage=" + discountPercentage +
                ", discountAmount=" + discountAmount +
                ", localDate=" + localDate +
                '}';
    }
}


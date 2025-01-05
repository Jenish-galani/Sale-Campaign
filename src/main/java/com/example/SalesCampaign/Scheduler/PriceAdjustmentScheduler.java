package com.example.SalesCampaign.Scheduler;

import com.example.SalesCampaign.Models.Campaign;
import com.example.SalesCampaign.Models.CampaignDiscount;
import com.example.SalesCampaign.Models.PriceHistory;
import com.example.SalesCampaign.Models.Product;
import com.example.SalesCampaign.Repositories.CampaignRepository;
import com.example.SalesCampaign.Repositories.PriceHistoryRepository;
import com.example.SalesCampaign.Repositories.ProductRepository;
import com.example.SalesCampaign.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PriceAdjustmentScheduler {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    // Scheduled task to adjust product prices daily
    @Scheduled(cron = "0 0 0 * * *")
    public void adjustProductPrices() {
        System.out.println("Campaign started: " + LocalDate.now());
        LocalDate today = LocalDate.now();
        List<Campaign> activeSales = campaignRepository.findAllByStartDate(today);

        for (Campaign campaign : activeSales) {
            List<CampaignDiscount> discounts = campaign.getCampaignDiscounts();
            for (CampaignDiscount discount : discounts) {
                Product product = productRepository.findById(discount.getProductId()).orElse(null);

                if (product != null) {
                    double discountAmount = product.getCurrentPrice() * (discount.getDiscount() / 100);
                    double newPrice = product.getCurrentPrice() - discountAmount;

                    if (newPrice >= 0) {
                        product.setCurrentPrice(newPrice);
                        product.setDiscount(product.getDiscount() + discount.getDiscount());
                        productRepository.save(product);
                        priceHistoryRepository.save(new PriceHistory(product, product.getCurrentPrice() + discountAmount, newPrice, discount.getDiscount(), discountAmount, LocalDate.now()));
                    }
                }
            }
        }
    }

    // Scheduled task to revert product prices at the end of the day
    @Scheduled(cron = "0 59 23 * * *")
    public void revertPrice() {
        System.out.println("Campaign ended: " + LocalDate.now());
        LocalDate today = LocalDate.now();
        List<Campaign> endedSales = campaignRepository.findAllByEndDate(today);

        for (Campaign campaign : endedSales) {
            List<CampaignDiscount> discounts = campaign.getCampaignDiscounts();
            for (CampaignDiscount discount : discounts) {
                Product product = productRepository.findById(discount.getProductId()).orElse(null);
                if (product != null) {
                    LocalDate campaignStartDate = campaign.getStartDate();

                    PriceHistory priceHistoryStart = priceHistoryRepository.findTopByProductIdAndDate(product.getId(), campaignStartDate);
                    PriceHistory priceHistoryLatest = priceHistoryRepository.findByProductIdOrderByDate(product.getId());
                    PriceHistory priceHistoryBeforeStart = priceHistoryRepository.findByProductIdAndBeforeDate(product.getId(), campaignStartDate);

                    if (priceHistoryStart != null && priceHistoryLatest != null && priceHistoryBeforeStart != null) {
                        double previousPrice = priceHistoryLatest.getFinalPrice();
                        double currentPrice = previousPrice + priceHistoryStart.getDiscountAmount();

                        product.setCurrentPrice(currentPrice);
                        product.setDiscount((float) (product.getDiscount() - priceHistoryStart.getDiscountPercentage()));
                        productRepository.save(product);

                        double finalPrice = currentPrice;
                        double basePrice = priceHistoryLatest.getPrice() + (priceHistoryBeforeStart.getPrice() - priceHistoryStart.getPrice());
                        double discountAmount = basePrice - finalPrice;
                        double discountPercentage = (discountAmount * 100) / basePrice;

                        priceHistoryRepository.save(new PriceHistory(product, basePrice, finalPrice, discountPercentage, discountAmount, LocalDate.now()));
                    }
                }
            }
        }
    }
}

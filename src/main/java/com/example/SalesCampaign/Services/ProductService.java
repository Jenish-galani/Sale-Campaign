package com.example.SalesCampaign.Services;

import com.example.SalesCampaign.Models.PriceHistory;
import com.example.SalesCampaign.Models.Product;
import com.example.SalesCampaign.Models.ResponseDTO;
import com.example.SalesCampaign.Repositories.CampaignRepository;
import com.example.SalesCampaign.Repositories.PriceHistoryRepository;
import com.example.SalesCampaign.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    // Method to save a single product
    public ResponseDTO<Product> saveProduct(Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            double discountAmount = product.getMrp() * (product.getDiscount() / 100);

            // Save price history
            LocalDate today = LocalDate.now();
            priceHistoryRepository.save(new PriceHistory(product, product.getMrp(), product.getCurrentPrice(), product.getDiscount(), discountAmount, today));

            return new ResponseDTO<>(savedProduct, HttpStatus.OK, "Product saved successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save product: " + e.getMessage());
        }
    }

    // Method to save multiple products
    public ResponseDTO<List<Product>> saveAllProduct(List<Product> products) {
        try {
            List<Product> productList = productRepository.saveAll(products);
            for (Product product : productList) {
                double discountAmount = product.getMrp() * (product.getDiscount() / 100);

                // Save price history
                LocalDate today = LocalDate.now();
                priceHistoryRepository.save(new PriceHistory(product, product.getMrp(), product.getCurrentPrice(), product.getDiscount(), discountAmount, today));
            }
            return new ResponseDTO<>(productList, HttpStatus.OK, "Products saved successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save products: " + e.getMessage());
        }
    }

    // Method to get all products
    public ResponseDTO<List<Product>> getProductList() {
        try {
            return new ResponseDTO<>(productRepository.findAll(), HttpStatus.OK, "Product list retrieved successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve products: " + e.getMessage());
        }
    }

    // Method to get paginated products
    public ResponseDTO<Page<Product>> getAllPaginated(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<Product> productsPage = productRepository.findAll(pageable);
            return new ResponseDTO<>(productsPage, HttpStatus.OK, "Paginated products retrieved successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve paginated products: " + e.getMessage());
        }
    }

    // Method to get a single product by ID
    public Optional<Product> getOne(int pId) {
        return productRepository.findById(pId);
    }

    // Method to update a single product
    public Product updateOne(Product product) {
        return productRepository.save(product);
    }

    // Method to delete a single product
    public void deleteOne(int pId) {
        productRepository.deleteById(pId);
    }

    // Method to update a product's price
    public ResponseDTO<Product> updateProductPrice(int productId, double price) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                return new ResponseDTO<>(null, HttpStatus.NOT_FOUND, "Product not found");
            }
            if (product.getCurrentPrice() != price) {
                product.setCurrentPrice(price);
                productRepository.save(product);

                double discountAmount = product.getCurrentPrice() * (product.getDiscount() / 100);

                // Save price history
                LocalDate today = LocalDate.now();
                priceHistoryRepository.save(new PriceHistory(product, product.getMrp(), price, product.getDiscount(), discountAmount, today));
            }
            return new ResponseDTO<>(product, HttpStatus.OK, "Product price updated successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update product price: " + e.getMessage());
        }
    }
}

package com.example.SalesCampaign.Controllers;

import com.example.SalesCampaign.Models.Product;
import com.example.SalesCampaign.Models.ResponseDTO;
import com.example.SalesCampaign.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to save a single product
    @PostMapping("saveProduct")
    public ResponseDTO<Product> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Endpoint to get all products
    @GetMapping("getAllProducts")
    public ResponseDTO<List<Product>> getAllProducts() {
        return productService.getProductList();
    }

    // Endpoint to get a single product by ID
    @GetMapping("id/{pId}")
    public Optional<Product> getOne(@PathVariable int pId) {
        return productService.getOne(pId);
    }

    // Endpoint to update a single product
    @PutMapping("id/{pId}")
    public Product updateOne(@RequestBody Product product) {
        return productService.updateOne(product);
    }

    // Endpoint to delete an one product
    @DeleteMapping("id/{pId}")
    public String deleteOne(@PathVariable int pId) {
        productService.deleteOne(pId);
        return "Record deleted successfully";
    }

    // Endpoint to get all products with pagination
    @GetMapping("getAllPaginated")
    public ResponseDTO<Page<Product>> getAllProductsPaginated(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return productService.getAllPaginated(page, size);
    }

    // Endpoint to save multiple products
    @PostMapping("saveAll")
    public ResponseDTO<List<Product>> saveAll(@RequestBody List<Product> products) {
        return productService.saveAllProduct(products);
    }

    // Endpoint to update the price of a product
    @PutMapping("updatePrice")
    public ResponseDTO<Product> updateProductPrice(@RequestHeader("productId") int productId, @RequestHeader("price") double price) {
        return productService.updateProductPrice(productId, price);
    }
}

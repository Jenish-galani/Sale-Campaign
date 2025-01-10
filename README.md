# 🛍️ Sale-Campaign

# First, create a database named 'SalesCampaign' and then run it.

# 📖 Overview
The Sale Campaign Management System is a Java Spring Boot application designed to efficiently manage sales campaigns and a large product catalog of approximately 100,000 products. This system dynamically adjusts product prices during sales campaigns and provides APIs for querying product information, price history, and campaign details.

# 🏗️ Project Structure

- ├── src
- │   ├── main
- │   │   ├── java
- │   │   │   └── com
- │   │   │       └── example
- │   │   │           └── sale
- │   │   │               ├── controller
- │   │   │               ├── model
- │   │   │               ├── repository
- │   │   │               └── service
- │   │   └── resources
- │   │       └── application.properties
- │   └── test
- └── pom.xml

# ✨ Features
- ✅ Paginated Product Listing API: Fetch product details with pagination and real-time price adjustments during sales.
- ✅ Campaign Management API: Create and manage sales campaigns, including defining product-specific discounts.
- 🔄 Dynamic Price Adjustment: Temporarily adjust product prices during active sales campaigns.
- 🔍 Query Features:
- Retrieve all products with dynamically adjusted prices.
- Access the complete pricing history of each product.
- Retrieve all campaigns (past, current, and upcoming).

# 💻 Technologies Used
- Language & Framework: Java with Spring Boot
- Database: MySQL

#  ⚙️ Installation and Usage
- Java 17+
- Maven
- MySQL Database

# 🚧 Future Enhancements
- 🔎 Implement a search feature for products.
- 🔐 Add user authentication for managing campaigns.
- 🖥️ Develop frontend integration for campaign and product management.

#📜 License
- This project is licensed under the MIT License.

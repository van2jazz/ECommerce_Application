# ECommerce_Application
+ This E_Commerce Appication is developed in Java and the Spring Boot framework. It employs Spring Data JPA for seamless interaction with PostgreSQL database, managing entities such as categories, products, and orders. Robust authentication is ensured through Auth0 for secure REST API access.

+ The APIs are well-documented and easily accessible through Swagger UI, making it simple for developers to test and understand the various endpoints. Overall, this project provides secure Rest APIs to create a scalable platform for businesses to sell their products to customers.


# Features
## Admin
+ Loggin
+ Users
+ Categories
+ Product
+ Price and Discount
+ Others

## User
+ Registration & Login
+ Fetch categories and products based on category
+ Adding & deleting products to cart
+ Managing address and products quantity
+ Ordering products and fetching order status

# Security
+ The API is secured using JSON Web Tokens (JWT) handled by Auth0. To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorize option available in the Swagger-ui.

# Technologies:

+  Java 17 or above
+  Spring Boot 3.0
+  Maven
+  MySQL
+  Spring Data JPA
+  Spring Security
+  JSON Web Tokens (JWT)
+  Auth0
+  Swagger UI

#  Running the app

1. Clone the repository: git clone https://github.com/van2jazz/ECommerce_Application 
2. Import the project into STS:

+ Click File > Import...
+ Select Maven > Existing Maven Projects and click Next
+ Browse to the project directory and click Finish

3. Update the values in application.properties with your database connection details.
4. Run the app: Right-click the project in the Package Explorer and click Run As > Spring Boot App.

# API documentation
 + API documentation is available via Swagger UI at http://localhost:8080/swagger-ui/index.html (note: app must be running)

# ER-DIAGRAM
<img width="605" alt="Ecommerce_db_image" src="https://github.com/van2jazz/ECommerce_Application/assets/53022905/aba78264-970f-40f1-9bef-a801f15f3b34">

# Swagger-UI
![Screenshot 2023-11-25 at 22-04-16 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/00a525d4-fc45-4c9d-976b-0aecfd7fc8b0)

# API Controller
![Screenshot 2023-11-26 at 04-38-04 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/86c47e3c-4288-4ed0-a650-51f9ae174735)
![Screenshot 2023-11-26 at 04-38-49 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/732366ea-696b-4cb0-9191-0a2b02242b86)
![Screenshot 2023-11-26 at 04-39-28 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/e70e7bf2-6edb-42c8-bfe5-ead45c8f00ca)
![Screenshot 2023-11-26 at 04-43-47 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/4c76e6cb-c824-4e20-b5c7-9e0b3ab4d511)
![Screenshot 2023-11-26 at 04-44-01 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/49965a78-a2c0-4afd-8438-dc856dc11d47)
![Screenshot 2023-11-26 at 04-44-31 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/ca843892-db12-4d9b-ba75-d7a3796c9e16)
![Screenshot 2023-11-26 at 04-43-26 Swagger UI](https://github.com/van2jazz/ECommerce_Application/assets/53022905/50463ea2-f2d9-48d8-b910-6ae8ef146175)





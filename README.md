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

1 Clone the repository: git clone https://github.com/van2jazz/ECommerce_Application
2 Import the project into STS:

+ Click File > Import...
+ Select Maven > Existing Maven Projects and click Next
+ Browse to the project directory and click Finish

3 Update the values in application.properties with your database connection details.
4 Run the app: Right-click the project in the Package Explorer and click Run As > Spring Boot App.

# API documentation
 + API documentation is available via Swagger UI at http://localhost:8080/swagger-ui/index.html (note: app must be running)



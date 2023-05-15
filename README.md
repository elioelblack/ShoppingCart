# ShoppingCart

## Instructions:
- Create a database with the shopping-cart.sql file included in the root of the project
- configure your datasource application.properties 
   ```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/shopping-cart?serverTimezone=UTC
   spring.datasource.username=eliezer
   spring.datasource.password=123
- User and password of the user on database: admin | admin
- Security through bearer token
- security endpoint: /authenticate, body: { "username":"admin", "password":"admin"}
- Run the project

# Supermarket Management System

## Overview

### About the project

Final, practical project concluding the QA Academy Software Development Bootcamp.

The project consolidates the following concepts from the core training modules:
* Agile & Project Management (Git, Jira)
* Databases & Cloud Fundamentals (MySQL)
* Programming Fundamentals (Java)
* API Development (Spring Boot)
* Automated Testing (JUnit, Mockito)

The project consists of a Spring Boot API, produced with full CRUD (Create, Read, Updated and Delete) functionallity. The API is fully capable of handling HTTP requests and commuincates with a local database (MySQL).

### About the API

The API consists of a *Supermarket Management System*.

There are four 'model' classes, or 'entities':
1. User
2. Category
3. Product
4. Order

Shoppers ('User') can choose a variety of products ('Product') across multiple product categories ('Category') in order to generate an order ('Order') that is stored within the database.

### Tech used

* Version Control System: Git
* Source Code Management: GitHub
* Kanban Board: Jira (Scrum Board)
* Database Management System: MySQL (local)
* Back-End Programming Language: Java
* API Development Platform: Spring
* Build Tool: Maven
* Unit & Integration testing: JUnit
</details>

## MVP (Minimum Viable Product) Checklist

### Codebase:

* Spring Boot API with full CRUD functionality.
* Sensible package structure (back-end).
* Adherence to best practices.

### Testing:

* Unit and integration testing for the project back-end.
* Reasonable test coverage of the src/main/java folder

### Continuous Integration (CI):

* GitHub repository utilising the **feature-branch** model
* The main branch must compile
* A build of the application is present in the root folder of your git repo
    * A **.jar** which can be deployed from the command-line (java -jar <filename.jar>)

### README.md requirements

## Installation

### Running the application

```
git clone https://github.com/M-AliFida/QA-practical-spring-project
```
### Executing the application

```
cd QA-practical-spring-project
java -jar SuperMarketSystem-0.0.1-SNAPSHOT.jar
```

### Creating a database inside MySQL

```
CREATE DATABASE `super_market`
```

### MySQL database properties

```
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.url=jdbc:mysql://localhost:3306/super_market?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
logging.level.org.hibernate.SQL=DEBUG
spring.jpa.show-sql=true
```
Pay particular attention to `username` and `password`.

## Usage

### API access and requests

The API can be accessed using Swagger (`http://localhost:8080/swagger-ui.html`) or Postman. 

I will demonstrate API requests using Postman below.

### API functionality (CRUD)

The following CRUD functionality is specified for each class

#### User
![]()
#### Category
![]()
#### Product
![]()
#### Order
![]()

For a comprehensive understanding of the API including field constraints, parameters and response messages, see here: []()

### Demonstration of API using Postman

This demonstration aims to show how a typical user could use the Supermarket Management System.

<hr>

#### Creating and reading user data

We can register the shopper ('User') details on the system.

![]()

We can then read the data, either using the user ID (which, in this case is `1`)...
or by displaying all users. You can create multiple users within the system.

You will notice above that the shopper data generated above has an empty list of orders (`"orders: []`). This is becase the shopper has not made an order yet. The system *auto-generates* orders: when a shopper purchases products, an order becomes generated.

In order to purchase products, we first need to add them. We can generate products both within categories, or on their own.

#### Creating categories and products together

First, let's verify that we have no categories within our database. 

We created a category called `NFTs`, which have three products inside (`Robotos`, `HBots`, `Bored Ape Yacht Club`) that have priced different (`100`, `1000` and `10000` respectively)

We can read our category data, again, by using with the category ID...
or listing all categories. You can create multiple categories with multiple products. 

As our products were automatically created alongside our category, we can now manipulate this data seperately. This is a good example of how JPA works.  

To read products, we can use either the product ID...
or list all products. 

#### Creating categories and products seperately

We can also create a category without any products, after which we can add products to the category.

First we create an empty category

We can see that the products list is empty (`"products": []`).

We can then add any number our products to the `groceries` category by using the categoryID.

#### Purchasing products and creating an order

As we now have a user and categories of products, we can make a purchase. 

This is done through the productID and userID. We can state the quantites of the products being purchased.

We have generate a new order with it's own order number (`orderNumber`-- this is also the orderID), total amount of order (`totalPrice`), date and time of order (`orderTime`) as well as the status of delivery (`deliveryStatus`). Naturally, the order also shows what products were placed in the order.

Orders are assigned to the user that ordered them.

#### Searching, updating and deleting orders

We can use our retrieve an order by using the orderNumber...
or by listing all orders in the System in one place.

We can also update our orders. 

As you can see, the status of delivery (`deliveryStatus`) has been updated from 'Pending' to 'Delivered'

Finally, we can delete the order using the orderNumber.

We can verify the order has been deleted be listing all of our orders.

## Data Persistence

## Testing

## README.Me requirements
















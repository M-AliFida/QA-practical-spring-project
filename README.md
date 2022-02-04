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

The API consists of a **Supermarket Management System**.

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

### [README.md requirements](#final-thoughts)

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

The API can be accessed using Swagger (`http://localhost:8080/swagger-ui.html`) or Postman. <br>

I will demonstrate API requests using Postman below.

### API functionality (CRUD)

The following CRUD functionality is specified for each class

> User

<a href="User CRUD Functionality"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/CRUD%20Functionality/User%20CRUD.png" width="500" ></a>

> Category

<a href="Category CRUD Functionality"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/CRUD%20Functionality/Category%20CRUD.png" width="500" ></a>

> Product
> 
<a href="Product CRUD Functionality"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/CRUD%20Functionality/Product%20CRUD.png" width="500" ></a>

> Order
> 
<a href="Order CRUD Functionality"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/CRUD%20Functionality/Order%20CRUD.png" width="500" ></a>


For a comprehensive understanding of the API including field constraints, parameters and response messages, see [API Documentation](https://github.com/M-AliFida/QA-practical-spring-project/blob/develop/Documentation/API%20Documentation.pdf)

### Demonstration of API using Postman

This demonstration aims to show how a typical user use case of the Supermarket Management System. It is recommended you view the following screenshots with their [Corresponding JSON files](https://github.com/M-AliFida/QA-practical-spring-project/tree/develop/Screenshots/API%20Requests/JSON%20Files%20(Results%20Output%20for%20API%20Calls)).

<hr>

#### Creating and reading user data

The first thing we may want to do is to register a shopper ('User'), in order to add their details on the system.

<a href="user-create"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/user-create.png" width="1000" ></a>

We can then read the user data, either using the user `id` (which, in this case is `1`) ...

<a href="user-read-data-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/user-read-data-ID.png" width="1000" ></a>

... or by displaying all users

<a href="user-read-data-all"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/user-read-data-all.png" width="1000" ></a>

Note. *You can create multiple users within the system.*

You may notice above that the shopper data generated above has an empty list of orders ("orders: []"). This is becase the shopper has not made an order yet. The system *auto-generates* orders: when a shopper purchases products, an order becomes generated.

In order to purchase products, we first need to add them. We can generate products both within categories, or on their own.

<hr>

#### Creating categories and products together

First, let's verify that we have no categories within our database

<a href="category-read-all-no-found"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/category-read-all-no-found.png" width="1000" ></a>

We then create a new category called `NFTs`, which have three products inside (`Robotos`, `HBots`, `Bored Ape Yacht Club`) that are priced different (`100`, `1000` and `10000` respectively).

We can then read our category data, again, by either using the category `id` (`c69bcb3a-7a98-46f0-a2b8-4c5d8a6aeeee`)...

<a href="category-read-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/category-read-ID.png" width="1000" ></a>

... or listing all categories. 

<a href="category-read-all"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/category-read-all.png" width="1000" ></a>

Note. *You can create multiple categories with multiple products.*

Our products are automatically created along with our category. This allows us to manipulate this data seperately. **This is a good example of how JPA works.**

In order to read products, we can use either the product `id` (`ba81cd8e-81ab-4149-a8c4-6be6df971d28`) ...

<a href="product-read-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/product-read-ID.png" width="1000" ></a>

... or list all products. 

<a href="product-read-all"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/product-read-all.png" width="1000" ></a>

<hr>

#### Creating categories and products seperately

We can create a product on its own, with a respective price

<a href="product-create"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/product-create.png" width="1000" ></a>

We can also empty category, after which we can add products to that category

<a href="category-create-no-products"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/category-create-no-products.png" width="1000" ></a>

We can see that the products list is empty ("products: []").

We can then add any number our products (`Eggs` and `Milk`) to the `groceries` category by using the `categoryID` (`7ad2f647-22b4-47af-828a-1f5286753bef`).

<a href="category-create-products-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/category-create-products-ID.png" width="1000" ></a>

<hr>

#### Purchasing products and creating an order

As we now have a user and categories of products, we can now make a purchase. 

This is done through the use of the user `id` (`1`) and `productID` (`9c62bf4c-30be-4101-8305-3786f42758d0`). We can state the quantites of the products being purchased

<a href="product-create-purchase-order"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/product-create-purchase-order.png" width="1000" ></a>

We have now generated a new order with its own order number ("orderNumber"-- this is also the order `id`), total amount of order ("totalPrice"), date and time of order ("orderTime") as well as the status of delivery ("deliveryStatus"). Naturally, the order also shows the products placed within the order.

Orders are assigned to the user that ordered them

<a href="user-read-order-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/user-read-order-ID.png" width="1000" ></a>

<hr>

#### Searching, updating and deleting orders

We can retrieve an order by using the order `id' (`9c62bf4c-30be-4101-8305-3786f42758d0`) ...

<a href="order-read-order-ID"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/order-read-order-ID.png" width="1000" ></a>

... or by listing all the orders within the System

<a href="order-read-all"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/order-read-all.png" width="1000" ></a>

We can also update our orders

<a href="order-update"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/order-update.png" width="1000" ></a>

As you can see, the status of delivery ("deliveryStatus") has been updated from 'Pending' to 'Delivered'

Finally, we can delete the order using the order `id` (`9c62bf4c-30be-4101-8305-3786f42758d0`)

<a href="order-delete"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/order-delete.png" width="1000" ></a>

We can verify the order has been deleted by listing all of our orders

<a href="order-read-none"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/API%20Requests/order-read-none.png" width="1000" ></a>

## Data Persistence

Sample data retrieved from the 'products' table, showing persistence of data from the above API demonstration (more specifically, from the API calls in ['Creating categories and products together'](#creating-categories-and-products-together))

<a href="Data Persistence"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/Data%20Persistence/Data%20Persistence.png" width="1000" ></a>

## Testing Coverage

Both unit and integration tests were carried out. <br>

The overall test coverage (including Class %, Method % and Line %) of all the test files is

<a href="Test Coverage"><img src="https://raw.githubusercontent.com/M-AliFida/QA-practical-spring-project/develop/Screenshots/Test%20Coverage/Test%20Coverage.png" width="1000" ></a>

## Final Thoughts

> Why are we doing this?

The project helps to consolidate the learning material taught during the QA Academy Software Development Bootcamp through practical application. There is tremendous value in learning how to implement a REST API, as it an helps me to upskill the basic principles, fundamentals, syntax and applications of Java that I have acquired throughout the Bootcamp.

> How I expected the challenge to go

> What went well? / What didn't go as planned?

> Possible improvements for future revisions of the project

## Screenshots + Jira

* [Screenshots showing your postman requests and the output from the API](#demonstration-of-api-using-postman)
* [Screenshots of your database to prove that data is being persisted](#data-persistence)
* [Screenshot of your test results, including coverage report](#testing-coverage)
* [Jira Board](https://m-a-f.atlassian.net/jira/software/projects/DFE/boards/1)

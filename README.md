# Supermarket Management System

## Overview

### About the project

A Final, practical project concluding the QA Academy Software Development Bootcamp.

The project consolidates the following concepts from the core training modules:
* Agile & Project Management (Git, Jira)
* Databases & Cloud Fundamentals (MySQL)
* Programming Fundamentals (Java)
* API Development (Spring Boot)
* Automated Testing (JUnit)

The project consists of a Spring Boot API, produced with full CRUD (Create, Read, Updated and Delete) functionallity. The API is fully capable of handling HTTP requests and commuincates with a local database (MySQL).

### About the API

The API consists of a *supermarket management system*.

There are four 'model' classes, or 'entities':
1. User
2. Category
3. Product
4. Order

Shoppers ('User') can choose a variety of products ('Product') across multiple product categories ('Category') in order to generate an order ('Order') which will be stored on the database. 

### Tech used:

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

### README.md requirements (TO FOLLOW):

README.md should contain the following headers:
* Why are we doing this?
* How I expected the challenge to go.
* What went well? / What didn't go as planned?
* Possible improvements for future revisions of the project.
* Screenshots showing your postman requests and the output from the API.
* Screenshots of your database to prove that data is being persisted.
* Screenshot of your test results, including coverage report.
* [Link](https://m-a-f.atlassian.net/jira/software/projects/DFE/boards/1) to Jira Board.
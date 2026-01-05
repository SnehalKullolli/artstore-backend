# ArtStore Backend API

## Overview
ArtStore Backend is a RESTful web application built using Spring Boot that provides APIs for product management, cart handling, and order processing. It serves as the backend system for the ArtStore frontend and ensures secure, reliable, and consistent data operations.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- RESTful APIs
- Maven

## Core Features
- Product, cart, and order management using REST APIs
- CRUD operations with transactional consistency
- Layered architecture (Controller, Service, Repository)
- Role-based access control for secured endpoints
- Data validation and integrity enforcement

## Application Architecture
- Controller Layer – Handles HTTP requests and responses
- Service Layer – Implements business logic and transactions
- Repository Layer – Manages database operations using JPA
- Entity Layer – Represents database tables and relationships

## Database Design
- Designed relational schema with 6 entities
- Implemented entity relationships using JPA annotations
- Ensured cascade operations and referential integrity
- Used MySQL for persistent data storage

## API Overview
- Exposes 15+ RESTful endpoints
- Supports product listing, cart operations, and order workflows
- Handles request validation and consistent response handling

## Security & Authentication
- Implemented role-based access control
- Secured sensitive APIs based on user roles
- Authentication and authorization handled at API level

## Testing
- Tested 15+ APIs using Postman
- Validated backend data using SQL queries
- Verified transactional workflows and data consistency

## How to Run Locally (Eclipse)
1. Clone the repository
2. Open Eclipse IDE
3. Import the project as:
   File → Import → Existing Maven Project
4. Update database credentials in `application.properties`
5. Right-click on the main Spring Boot application class
6. Select Run As → Spring Boot App
7. Application will start on `http://localhost:8080`

## Future Enhancements
- Improved exception handling
- API documentation using Swagger

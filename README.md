# Order Booking & Portfolio API

## Tech Stack
- Java 17
- Spring Boot 3
- MySQL
- Spring Data JPA
- Maven

## Features
- Place Order
- Fill Order
- Cancel Order
- Portfolio Management
- Sector Overlap Calculation

## Business Rules
- Max 3 pending orders per trader
- SELL rejected if holdings insufficient
- Only PENDING orders can be filled/cancelled

## How to Run

### 1. Create Database
CREATE DATABASE trading_db;

### 2. Configure application.properties

### 3. Run Application
mvn spring-boot:run

## API Endpoints

POST /api/orders

POST /api/orders/{id}/fill

POST /api/orders/{id}/cancel

GET /api/portfolio/{traderId}

POST /api/portfolio

## Design Decisions
- Layered Architecture
- Transaction Management
- Pure Java overlap logic
- Global Exception Handling

## Assumptions
- Authentication not implemented
- Stocks assumed valid

## Future Improvements
- Kafka Integration
- Redis Caching
- Docker Support
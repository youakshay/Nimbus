# Nimbus - Ride Pooling Platform
## Features
- **User Authentication and Authorization**
   - JWt-based authentication and Redis-backed token blacklist for secure logout.
- **Ride Pooling Logic**
  - Dynamic user-to-pool matching based on route overlap and seat availability.
  - Membership validation ensuring users can only view pools they are part of.
- **RESTful API Design**
  - DTO-based request/response models with input validation
  - Modular architecture(controllers, Services, Repositories) for clean code separation.
  
## Tech Stack
- **Backend:** Java 17, Spring Boot(Web, Security, Data JPA)
- **Database:** PostgreSQL
- **Cache/Blacklist:** Redis
- **DevOps:** Docker, Docker compose
- **Build Tool:** Maven

## Getting Started

### Prerequisites
- Java 17+
- Docker installed

### Steps

1. Clone the repository:
   ```
   git clone https://github.com/youakshay/nimbus.git
   cd nimbus
   ```
2. Build the application:
   ```
   maven clean install
   ```
3. Start services with Docker Compose:
   ```
   docker-compose up
   ```

## API Endpoints
- POST /api/v1/auth/register -> Register new user
- POST /api/v1/auth/login -> Login and get JWT token
- POST /api/v1/auth/logout -> Logout and blacklist JWt token
- POST /api/v1/business/trip -> create a trip request
- GET /api/v1/business/pools/{poolID} -> Fetch pool details(Only if member of that pool)

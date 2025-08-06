# Nimbus - Ride Pooling Platform
## Tech Stack
- **Backend:** Java 17, Spring Boot(Web, Security, Data JPA)
- **Database:** PostgreSQL
- **Cache/Blacklist:** Redis
- **DevOps:** Docker, Docker compose
- **Build Tool:** Maven

## Getting Started
- Java 17+
- Docker installed

## Steps

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

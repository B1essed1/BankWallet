# BankWallet

A Spring Boot application for managing bank wallet operations.

## Prerequisites

- Java 21
- Docker (for running PostgreSQL)
- Maven

## Database Setup

### Run PostgreSQL with Docker

Click the command below or copy and run it in your terminal:

```bash
docker run --name bankwallet-postgres -e POSTGRES_DB=bankwallet_db -e POSTGRES_USER=bankwallet_user -e POSTGRES_PASSWORD=bankwallet_pass -p 5432:5432 -d postgres:15
```

This command will:
- Create a PostgreSQL container named `bankwallet-postgres`
- Create a database named `bankwallet_db`
- Create a user `bankwallet_user` with password `bankwallet_pass`
- Expose PostgreSQL on port `5432`
- Run in detached mode

### Database Configuration

The application is configured to connect to PostgreSQL with the following settings (in `application.yml`):

- **Database Name**: `bankwallet_db`
- **Username**: `bankwallet_user`
- **Password**: `bankwallet_pass`
- **Port**: `5432`
- **Host**: `localhost`

### Useful Docker Commands

**Stop the database:**
```bash
docker stop bankwallet-postgres
```

**Start the database:**
```bash
docker start bankwallet-postgres
```

**Remove the database container:**
```bash
docker rm -f bankwallet-postgres
```

**View database logs:**
```bash
docker logs bankwallet-postgres
```

**Access PostgreSQL CLI:**
```bash
docker exec -it bankwallet-postgres psql -U bankwallet_user -d bankwallet_db
```

## Running the Application

1. Start the PostgreSQL database using the Docker command above
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Build

```bash
mvn clean package
```

## Technologies Used

- Spring Boot 4.0.0
- Java 21
- PostgreSQL
- Spring Data JPA
- Spring Security
- JWT Authentication
- Lombok
- SpringDoc OpenAPI
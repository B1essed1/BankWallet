# BankWallet

# How to use

- first need to create user from RegisterUserRequest
  which does have request example in REQUESTS.MD
- then from that you can use everything with that user



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


### Database Configuration

The application is configured to connect to PostgreSQL with the following settings (in `application.yml`):

- **Database Name**: `bankwallet_db`
- **Username**: `bankwallet_user`
- **Password**: `bankwallet_pass`
- **Port**: `5432`
- **Host**: `localhost`

## API Documentation

Once the application is running, you can access the Swagger UI at:
## WSDL
```
http://localhost:8080/ws/bankwallet.wsdl

```

## Build
First need to compile project for generating xsd classes

```bash
mvn clean compile
```

## Technologies Used

- Spring Boot 4.0.0
- Java 21
- PostgreSQL
- Spring Data JPA
- Lombok



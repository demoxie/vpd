# Microservice Setup

## Description
This microservice architecture consists of 8 services as follows:

1. **Eureka Server**:
    - Service registry server where all services register their presence.
    - Runs on port `8761`.
    - Accessible at [http://localhost:8761/eureka](http://localhost:8761/eureka).

2. **Config Server**:
    - Holds configurations for each service, from which they pull their configurations.
    - Runs on port `8888`.
    - Accessible at [http://localhost:8888](http://localhost:8888).

3. **Gateway Service**:
    - Acts as the entry point for the entire system, except for the external-transaction service.
    - Runs on port `4040`.
    - Accessible at [http://localhost:4040/api/v1](http://localhost:4040/api/v1).

4. **Authorization Server**:
    - Responsible for authenticating and authorizing users. The gateway service calls this server before routing requests to the intended destination service.
    - Runs on port `7070`.
    - Accessible at [http://localhost:7070/auth](http://localhost:7070/auth).

5. **Account Service**:
    - Manages and processes user financial accounts.
    - Runs on port `2020`.
    - Accessible at [http://localhost:2020/accounts](http://localhost:2020/accounts).

6. **Transaction Service**:
    - Handles transaction processing.
    - Runs on port `3030`.
    - Accessible at [http://localhost:3030/transaction-services](http://localhost:3030/transaction-services).

7. **External Service**:
    - Designed for external clients and protected with a rate limiter.
    - Runs on port `4000`.
    - Accessible at [http://localhost:4000/api/v1](http://localhost:4000/api/v1).

8. **External Transaction**:
    - A client service through which external clients can access the microservice.
    - Runs on port `4000`.
    - Accessible at [http://localhost:4000/api/v1](http://localhost:4000/api/v1).

## Starting the Services
To start the services, navigate to the root directory of each service and execute the following commands:

```bash
mvn clean install
mvn spring-boot:run
````


Ensure that the necessary databases are created for each service to connect to.

To start the external client, navigate to its root directory and run:

```bash
npm install
npm start
```


The easiest way to start all services simultaneously is to use Docker Compose. Run the following command:

```bash
docker-compose up
```


This will bring up the entire microservice architecture.

Postman Collection
A Postman collection is available in the root directory of this project to facilitate testing.

Contact
If you have any questions, please reach out to me at:
```bash
Email: shadrachadamuul@gmail.com
Phone: +2347067659632
```
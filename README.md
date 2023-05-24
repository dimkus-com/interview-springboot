# The Spring-Boot Interview Project

## Task

Implement:
- Spring Boot micro-service written in Java
- Build with Maven
- Data will be stored in embeded H2 database
- micro-service will be able to run locally without any other external DB (etc.) dependencies
- provide source code to us preferably with link publicly available on the internet or via ZIP file attached to the email


#### Create endpoint for creation of Quotation.

Quotation has business attributes:
- beginingOfInsurance
- insuredAmount
- dateOfSigningMortgage
- Customer


#### Create endpoint for creation of Subscription.

#### Create endpoint for retrieving single Subscription object.


Subscription has business attributes:
- Quotation
- startDate
- validUntil


#### Create endpoint for updating Customer attributes.

#### Endpoint for updating Customer should be able to update and/or remove existing values of any/all attributes.

Customer has business attributes:

- firstName
- lastName
- middleName
- email
- phoneNumber
- birthDate

## Solution - check list
1) In the task java version not defined. I used latest LTS version of Java 17 & latest version of Spring-Boot 3.1.0. For using on Java 11 LTS or less the code will be a little different. I used there Records DTOs as Immutable objects for serialization/deserialization Rest API requests and separate Rest & Database layers. And for Java 11 LTS or less Spring-Boot will be 2.x.
2) In the task no detailed information about Customer object updating. I created as defined endpoint with "update and/or remove existing values of any/all attributes" - customer object will be updated as you will send the data. If you send value that attribute will be updated, if you unset attribute or send null, that attribute will be removed. But for production solution that is anti-pattern. Better way to have endpoint with atomic attribute updates and separate endpoint for removing.
3) In the task no information about security. Possible solution Spring Security with OAuth2/JWT support.
4) H2 database with auto-update DDL Hibernate, as defined in the task, but "real" projects use other databases. Liquibase/Flyway is used as the schema keeper. Hibernate DDL autoupdate is disabled, this is an anti-pattern. Testcontainers for testing purposes is the best solution.
5) For Rest API possible improvement to use Swagger/OpenAPI (API first model), but not defined in the task.
6) For less & better readable code in the project I used Lombok
7) For bi-direction transformations from DTOs to DB Entities classes I used MapStruct
8) For beginingOfInsurance, dateOfSigningMortgage, startDate, validUntil I used ZoneDateTime(ISO 8601) datatype for correction understand time point in the different timezones.
9) Test are on all Mappers & for Dates Inputs & Outputs formats 

### Endpoints:
- http://localhost:8080/api/customer/create
- http://localhost:8080/api/customer/update/{id}
- http://localhost:8080/api/customer/get/{id} - not in the task, created only for tests
- http://localhost:8080/api/quotation/create
- http://localhost:8080/api/subscription/create
- http://localhost:8080/api/subscription/get/{id}

#### ZonedDateTime input format
- ZonedDateTime ISO-8601

#### ZonedDateTime output format (response always in UTC)
- 2023-05-24T15:55:44Z

#### birthDate is ISO LocalDate format for input & output
- 2023-05-24
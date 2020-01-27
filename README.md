# Ischia: A Kotlin Spring Boot Starter

Justification: Kotlin and Spring Boot are a productive combo, but it takes significant setup to get past all of the configuration.

Goal: an example project that can be forked to save significant time in creating a headless Kotlin + Spring Boot ReST API.

Current version: `v0.2.0` 

Why "Ischia?" It's an island that I like.

## Features

 - Modular Gradle build that separates domain classes, business logic, and web concerns
 - < 10 second restart time after initial build, even for large/complex projects
 - Incremental Kotlin compilation
 - Lightning-fast test iteration cycles inside modules
 - Embedded servlet container and runnable jar
 - Type-safe config values
 - Only the topmost web layer depends on Spring Boot
 
## Components

 - Kotlin 1.3
 - Spring Boot 2.2
 - Gradle 6.1
 - Spring Security 5.1
 - Google OAuth2
 - JPA and Hibernate
 - Mariadb JDBC connector
 - Spring Docs
 - Embedded Jetty servlet container
 - SLF4J with Jetty Util Logging
 - JUnit + Mockito

# Building and Running Ischia

Requirements: 
 - Java JDK 1.8
 - MariaDB (10.4.11 recommended) or MySQL (requires additional configuration)

## Running the project for the first time

It's a good idea to build and run the existing project before modifying.

Copy `application-dev.properties.sample` and `application.properties.sample` and ensure the following properties are set correctly:

The following JDBC connection params should match your local Mariadb database:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ischia?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=<YOUR MARIADB USER>
spring.datasource.password=<YOUR MARIADB PASSWORD>
```

## Running the project

With the development profile (overrides settings using `application-dev.properties`):
```
./gradlew build assemble && java -jar -Dspring.profiles.active=dev build/libs/ischia-0.2.0.jar
``` 

In debug mode (using IntelliJ defaults):

```
./gradlew build assemble && java -jar -Dspring.profiles.active=dev -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 build/libs/ischia-0.2.0.jar 
```

# Modifying the project

Ischia is an example project that is meant to be forked directly and transformed into your application.

## Adding a domain class and data access object

1. Create the domain class in the module `1.domain`
2. Add an accompanying DAO interface in the module `1.domain` and any access methods
3. Add a repository in `src/main/kotlin/io/ischia/repository/Repositories.kt` with the following features:
  - It should extend `CrudRepository<T, ID>`, where `T` is the domain class and `ID` is the class of the class's `@Id`-annotated field
  - It should override any methods that Hibernate cannot create automatically using `@Query` and `override fun <function name>()`

## Adding a module

Ischia's module setup uses naming system that ensures the dependency graph remains a DAG. Recommendations for adding new modules:
 
 - Begin the name of the module with a number that is one greater its highest-numbered dependency
 - Favor depending on api/interface modules over implementation modules (e.g. depend on `2.api` rather than `3.service`) 

# Changelog

#### `v0.2.0` 2019/1/25
 - Added TypedConfig and TypedSpringConfig implementation
 - Added Github oauth, switched to Spring Security to eliminate deprecated Spring Oauth
 
#### `v0.1.0` 2019/1/24
 - Initial commit, spring boot + spring security + google oauth + JPA/Hibernate
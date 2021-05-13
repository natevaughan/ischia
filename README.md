# Ischia: A Kotlin Spring Boot Starter Template

Justification: Kotlin and Spring Boot are a productive combo, but it takes significant setup to get past all of the configuration.

Goal: an example project that can be forked to save significant time in creating a headless Kotlin + Spring Boot API.

Current version: `v0.2.1` 

Why "Ischia?" It's a cool island.

## Features

 - Modular Gradle build that separates domain classes, business logic, and web concerns
 - fast restart time after initial build, even for large/complex projects
 - Incremental Kotlin compilation
 - Lightning-fast test iteration cycles inside modules
 - Embedded servlet container and runnable jar
 - Type-safe config values
 - Only the topmost web layer depends on Spring Boot
 
## Components

 - Kotlin 1.5.0
 - Spring Boot 2.2
 - Gradle 6.1
 - Spring Security 5.2
 - Github OAuth2
 - JPA and Hibernate
 - Mariadb JDBC connector
 - Spring Docs
 - Embedded Jetty servlet container
 - SLF4J with Jetty Util Logging
 - JUnit + Mockito
 - Liquibase for database migrations

# Building and Running Ischia

Requirements: 
 - Java JDK 1.8
 - MariaDB (10.4.11 recommended) or MySQL (requires additional configuration)

# Project setup

The project is missing several configuration values which you must set up before it will run for the first time.

### Copy application properties files

From the project root, run the following commands to copy the template `application-dev.properties.sample` and `application.properties.sample` to ones that can be used by Spring by removing the `.sample` suffix:

```
cp src/main/resources/application-dev.properties.sample src/main/resources/application-dev.properties
cp src/main/resources/application.properties.sample src/main/resources/application.properties
```

### Add a database user and password that matches your local database

You should have a MariaDB database with a user and password that you can log into MariaDB. (You can check that your user and password work by running `mysql -u<YOUR USER> -p` and typing your password.)

Then, add the user and password to `src/main/resources/application-dev.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/ischia?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=<YOUR MARIADB USER>
spring.datasource.password=<YOUR MARIADB PASSWORD>
```

### Create a Github Application

Log into Github to create a Github application. Click your user icon (top right) and select "Settings." On the far left (bottom), select "Developer Settings." On the left, select "Oauth Apps." Click "Create new Oauth App."

Add Application Name, Url, and Description to any values you desire.

Set "Application Callback Url" to `http://localhost:8080/login/oauth2/code/github` (IMPORTANT).

Save your new oauth app.

Now copy the Client ID and Client Secret fields from the Github UI to your `application.properties`:

```
# OAuth2 configuration
ischia.oauth2.github.client-id=<GITHUB OAUTH2 CLIENT ID>
ischia.oauth2.github.client-secret=<GITHUB OAUTH2 CLIENT SECRET>
```

The application is now ready to run!

# Running the project

For convenience, a shell script has been included to simplify startup. From the project root, first make the script executable:
```
chmod +x ischia
```
Then start the app using `./ischia 1`

This set's Springs active profile to `dev`, and tells Spring to read first any settings in `application.properties`, and to overwrite them with any values contained in `application-dev.properties`.

To debug the application, set up a remote debug run profile in IntelliJ with the default settings. The application does not suspend, so you may connect and disconnect your debugger at any time.

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


## Packaging for deployment

To package for deployment, first build a runnable jar:
```
./ischia 3
```

Deploy to your favorite Java 8 runtime environment!

# Changelog

#### `v0.2.1` 2021-05-13
 - Upgrade to Spring Security 5.4.6
 - Added 1.util module for utility methods and interfaces
 - Added ./ischia shell script for startup
 - Added liquibase for DB migrations

#### `v0.2.0` 2019-01-26
 - Upgrade to Spring Security 5.2 and eliminate the deprecated `@EnableOauth2Sso`
 - Added TypedConfig and TypedSpringConfig implementation
 - Switched to Github oauth

#### `v0.1.1` 2019-01-25
 - Fix failing test in UserServiceSpec
 
#### `v0.1.0` 2019-01-24
 - Initial commit, spring boot + spring security + google oauth + JPA/Hibernate

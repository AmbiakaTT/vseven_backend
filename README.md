# VinUni Launchpad Project

## Environment Setup
Required dependencies:
```txt
Spring Boot 3+
Maven
JDK 17
```
### Step 0
```bash
git clone https://github.com/AmbiakaTT/vseven_backend
cd vseven_backend
```

### Step 1: Install JDK17
Download and install JDK 17 from [Oracle's website](https://www.oracle.com/java/technologies/downloads/).

### Step 2: Download Apache Maven
Download Apache Maven from [here](https://maven.apache.org/download.cgi) and follow the installation instructions.

Note: You can use IntelliJ to deal with the dependencies for you

### Step 3: Load Required Dependencies in pom.xml
Ensure all required dependencies are properly listed in the `pom.xml` file of your project.

### Step 3: Create MySQL database and populate data
```sql
CREATE DATABASE VSEVEN_INTEGRATE
USE VSEVEN_INTEGRATE
```

### Populate data
Copy code from main/src/main/resources/static/sqlscripts/setup.sql and paste it into MySQL workbench

### Password
Change local MySQL workbench password from main/src/main/resources/application.properties to your password

### Step 4: Maven Build
To build your project using Maven, execute the following command:
```bash
mvn clean install
```
This command will download dependencies, compile the code, and package your application.

*Note: Alternatively, you can use Jetbrains IntelliJ which can automatically load Maven dependencies for you.*

### Step 5: Run application
Run LaunchpadApplication
## Developer Workflow

1. Read user story
2. Discuss details with project manager
3. Implementation
4. Testing
5. Fix any bugs

## Structure Overview

The codebase is organized into several key packages:

- `src/main/java/com/vseven/launchpad/controller`: Contains classes responsible for handling incoming HTTP requests, processing input data, and producing HTTP responses.
- `src/main/java/com/vseven/launchpad/repository`: Houses classes responsible for data access, typically through database operations or external API calls.
- `src/main/java/com/vseven/launchpad/entity`: Contains entity classes representing data models used within the application. These classes often map to database tables.
- `src/main/java/com/vseven/launchpad/dto`: Contains Data Transfer Object (DTO) classes, which are used for transferring data between layers of the application or between the application and external systems.
- `src/main/java/com/vseven/launchpad/model`: Contains additional model classes that represent data structures used within the application but are not directly mapped to the database.
- `src/main/java/com/vseven/launchpad/security`: Holds classes responsible for implementing security measures such as authentication, authorization, and access control.
- `src/main/java/com/vseven/launchpad/exception`: Contains classes defining custom exceptions used within the application.
- `test/unit/com`: Unit testing with mockito framework 

- Depreciated:
-`src/main/java/com/vseven/launchpad/service`: No longer in use




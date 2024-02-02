# VinUni Launchpad Project

## Environment Setup

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
Change password from main/src/main/resources/application.properties to your password

### Step 4: Maven Build
To build your project using Maven, execute the following command:
```bash
mvn clean install
```
This command will download dependencies, compile the code, and package your application.

*Note: Alternatively, you can use Jetbrains IntelliJ which can automatically load Maven dependencies for you.*

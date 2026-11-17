# Medical Store Management System

A web-based application for managing the day-to-day activities of a medical store. The system helps users manage medicine information and monitor stock through a centralized interface.

## Features

- User login and logout
- Dashboard for viewing store information
- Add and manage medicine records
- View medicine stock and identify low-stock items
- Manage customer information
- MySQL database connectivity
- Password hashing using BCrypt

> Features listed here reflect the project context. Update this section if your current code includes additional modules or if any feature is not implemented.

## Technology Stack

- **Backend:** Java
- **Web technologies:** Jakarta Servlets and JSP
- **Database:** MySQL
- **Build tool:** Apache Maven
- **Server:** Apache Tomcat 10.1 or another Jakarta Servlet 6.0-compatible server
- **Java version:** 21
- **Libraries:** MySQL Connector/J, BCrypt, Jakarta JSTL

## Project Structure

```text
medical-store-management-system/
├── pom.xml
├── src/
│   └── main/
│       ├── java/       # Java servlet and application classes
│       ├── resources/  # Application resources (if used)
│       └── webapp/     # JSP pages and web resources
├── java/
│   └── medicalstore/  # Additional project source folder, if used
└── README.md
```

The exact folders may vary as the project is developed. The Maven configuration builds a WAR file named `MedicalStoreManagement.war`.

## Prerequisites

Install the following before running the application:

1. JDK 21
2. Apache Maven
3. MySQL Server
4. Apache Tomcat 10.1 (Jakarta Servlet 6.0 support)
5. An IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code

## Database Setup

1. Start the MySQL server.
2. Create a database for the application (use the database name configured in your Java database connection class).
3. Import or execute the SQL schema/scripts supplied with the project, if available.
4. Open the database connection class and update the JDBC URL, database username, and password for your local MySQL installation.
5. Ensure the MySQL database name and table names match the SQL queries used by the application.

Example JDBC URL:

```text
jdbc:mysql://localhost:3306/your_database_name
```

Do not commit real database passwords or other secrets to GitHub.

## Build the Project

Open a terminal in the folder containing `pom.xml` and run:

```bash
mvn clean package
```

If the build succeeds, Maven creates:

```text
target/MedicalStoreManagement.war
```

## Run the Application

1. Start MySQL.
2. Build the project using the command above.
3. Copy `target/MedicalStoreManagement.war` into the Tomcat `webapps` folder, or deploy it through your IDE/Tomcat manager.
4. Start the Tomcat server.
5. Open the application in your browser:

```text
http://localhost:8080/MedicalStoreManagement/
```

The exact landing page or URL may differ depending on the welcome page and Tomcat context configuration.

## Troubleshooting

- **Database connection error:** Check that MySQL is running and the JDBC URL, username, password, and database name are correct.
- **HTTP 404:** Confirm that the WAR deployed successfully and use the correct Tomcat context path.
- **HTTP 500:** Check the Tomcat console/logs for the underlying Java exception.
- **Servlet class loading error:** Confirm that the project uses a Jakarta-compatible Tomcat version and that servlet imports use `jakarta.servlet`.
- **Build failure:** Verify that JDK 21 and Maven are installed and that Maven is using the expected JDK.

## Security Notes

- Use BCrypt for password hashing; never store plain-text passwords.
- Keep database credentials outside source control.
- Validate user input on the server side.
- Use role-based access checks for administrative pages.

## Author

**Nisarga963**

## Repository

[Medical Store Management System on GitHub](https://github.com/Nisarga963/medical-store-management-system)

## License

No license has been specified for this repository. All rights are reserved by the repository owner unless a license is added.

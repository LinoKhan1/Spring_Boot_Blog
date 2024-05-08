# Spring Boot Blog Application

## Description

The Spring Boot Blog Application is a web-based platform designed for managing and publishing blog posts. It provides features for creating, editing, and deleting blog posts, as well as user authentication and authorization functionalities.

## Key Features
1. User Registration and Login
2. Blog Post Creation and Editing
3. Blog Post Management (Update and Delete)
4. User Authentication and Authorization
5. User-friendly Interface

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Screenshots or Demos](#screenshots-or-demos)
- [Contributing](#contributing)
- [License](#license)
- [Contact Information](#contact-information)

## Installation
Instructions on how to set up and run the application locally.
To run the Spring Boot Blog Application locally on your machine, follow these steps:
1. Clone the Repository
   ```
   git clone <repository-url>
   ```
2. Navigate to the Project Directory
   ```
   cd spring-boot-blog
   ```
3. Set Up Database
   - Create a MySQL database named `spring_boot_blog`.
   - Configure database connection details in `application.properties`.
4. Run the Application
   ```
   mvn spring-boot:run
   ```
5. Access the Application
   Open your web browser and navigate to `http://localhost:8080` to access the Spring Boot Blog Application.

## Usage
Instructions on how to use the Spring Boot Blog Application.
The Spring Boot Blog Application provides a user-friendly interface for managing blog posts. Here's how to use the application:

1. Sign Up/Login:
   - Create a new account or log in using existing credentials.

2. Create Blog Posts:
   - Click on the "Create Post" button to create a new blog post.
   - Enter the title, content, and other details for the blog post.

3. Manage Blog Posts:
   - Edit or delete existing blog posts as needed.

## Technologies Used
List of technologies, frameworks, and libraries used.
- Java 21
- Spring Boot
- Thymeleaf
- MySQL
- Maven
- Spring Security
- Lombok
- Spring Data JPA
- Spring Web

## Project Structure
The Spring Boot Blog Application is structured as follows:
```
. spring-boot-blog/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   └── com/
  │   │   │       └── linokhan/
  │   │   │           └── springbootblog/
  │   │   │               ├── controller/
  │   │   │               ├── model/
  │   │   │               ├── repository/
  │   │   │               └── service/
  │   │   └── resources/
  │   │       ├── static/
  │   │       └── templates/
  │   └── test/
  └── pom.xml
```

## Contributing
Guidelines for contributing to the project.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact Information
For questions or support, please contact Lino Khan at linokhan1@gmail.com.

# NU Student Clubs Management System - Backend

Backend implementation for the Nile University Clubs Hub

## 📋 Overview

This repository contains the backend implementation for the **Nile University Clubs Hub**, a centralized platform designed to bring all NU clubs together in one place. The project is built using **Spring Boot** and **Java**. 

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 4.0.0**
- **Spring Web MVC** - Building RESTful APIs
- **Spring Data JPA** - Database interaction
- **MySQL** - Primary database
- **Maven** - Project management and dependencies
- **Spring Boot DevTools** - Development productivity

## 📁 Project Structure

```
src/main/java/com/nu/clubs/clubs_bakend/
├── controller/          # REST Controllers
│   └── ClubController.java
├── service/            # Business Logic Layer
│   ├── EventService.java
│   ├── MembershipService.java
│   └── BoardMemberService.java
├── repository/         # Data Access Layer
│   ├── AdminRepository.java
│   └── CommitteeRepository. java
├── model/              # Entity Classes
│   └── Role.java
├── dto/                # Data Transfer Objects
│   ├── EventRequest.java
│   └── GalleryRequest. java
└── exception/          # Exception Handling
    └── GlobalExceptionHandler.java
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven Wrapper)
- MySQL Server
- Git

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/NU-Student-Clubs/NU-Student-Clubs-Management-System-Backend.git
cd NU-Student-Clubs-Management-System-Backend
```

2. **Set up the database**
   
   Create a MySQL database:
```sql
CREATE DATABASE nu_clubs_db;
```

3. **Configure database connection**
   
   Create `src/main/resources/application. properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nu_clubs_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. **Build the project**
```bash
./mvnw clean install
```

5. **Run the application**
```bash
./mvnw spring-boot:run
```

Or on Windows:
```cmd
mvnw.cmd spring-boot:run
```

The application will be running at:  `http://localhost:8080`

## 🔌 API Endpoints

### Clubs
- `GET /clubs` - Get all clubs
- `GET /clubs/{id}` - Get a specific club
- `POST /clubs` - Create a new club
- `PUT /clubs/{id}` - Update club details
- `DELETE /clubs/{id}` - Delete a club

*(More endpoints will be added as the project evolves)*

## 🏗️ Features

- **Club Management** - Create, read, update, and delete clubs
- **Event Management** - Handle club events and activities
- **Membership System** - Manage club memberships
- **Board Members** - Track club leadership and board members
- **Gallery Management** - Store and manage club photos and media
- **Role-Based Access** - Different permissions for admins, board members, and members

## 📊 Database Schema

The system uses MySQL with JPA/Hibernate for object-relational mapping. Key entities include: 
- **Club** - Club information and details
- **Event** - Club events and activities
- **Member** - Student membership information
- **BoardMember** - Club leadership
- **Committee** - Club committees
- **Admin** - System administrators
- **Role** - User roles and permissions

## 🧪 Testing

Run tests using: 
```bash
./mvnw test
```

## 🤝 Contributing

We welcome contributions! To contribute:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards
- Follow Java naming conventions
- Write meaningful commit messages
- Add comments for complex logic
- Update documentation for new features

## 📝 Project Status

The project is under active development. Current progress: 
- ✅ Spring Boot setup
- ✅ Database integration
- ✅ Basic controllers structure
- ✅ Services and repositories scaffolding
- 🚧 Business logic implementation (in progress)
- 🚧 Entity models (in progress)
- 🚧 API documentation (in progress)
- 📋 Authentication & Authorization (planned)
- 📋 Frontend integration (planned)

## 🐛 Known Issues

Check the [Issues](https://github.com/NU-Student-Clubs/NU-Student-Clubs-Management-System-Backend/issues) page for current bugs and feature requests.

## 📫 Contact

For questions and inquiries, please open an [Issue](https://github.com/NU-Student-Clubs/NU-Student-Clubs-Management-System-Backend/issues) on this repository.

## 👥 Team

This project is developed and maintained by the NU Student Clubs development team. 

## 📄 License

This project is open source and available for educational purposes.

## 🙏 Acknowledgments

- Nile University for supporting student initiatives
- All contributors and developers
- The Spring Boot community

---

**Made with ❤️ at Nile University** 🎓

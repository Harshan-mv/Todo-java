# Todo App - Spring Boot Application

A simple and elegant Todo application built with Spring Boot, featuring a clean web interface for managing daily tasks.

## 🚀 Live Demo

**[View Live Application](https://todo-java-13wl.onrender.com)**

## 📋 Features

- ✅ Add new todos
- ✅ Mark todos as complete/incomplete
- ✅ Edit existing todos
- ✅ Delete todos
- ✅ Responsive design
- ✅ Real-time updates
- ✅ Clean and intuitive UI

## 🛠️ Technologies Used

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5.3** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Web MVC** - Web framework
- **H2 Database** - In-memory database
- **Maven** - Build tool and dependency management

### Frontend
- **Thymeleaf** - Server-side templating engine
- **HTML5** - Markup language
- **CSS3** - Styling with modern design
- **JavaScript** - Interactive functionality
- **Bootstrap** - Responsive design framework

### DevOps & Deployment
- **Docker** - Containerization
- **Render** - Cloud deployment platform
- **Git** - Version control
- **GitHub** - Code repository

## 🏗️ Project Structure

```
todo-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/todoapp/
│   │   │   ├── TodoAppApplication.java
│   │   │   ├── controller/
│   │   │   │   └── TodoController.java
│   │   │   ├── model/
│   │   │   │   └── Todo.java
│   │   │   └── repository/
│   │   │       └── TodoRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-prod.properties
│   │       ├── static/
│   │       │   ├── css/style.css
│   │       │   └── js/script.js
│   │       └── templates/
│   │           └── index.html
│   └── test/
├── Dockerfile
├── .dockerignore
├── .gitignore
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (or use included Maven wrapper)
- Git

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/Harshan-mv/Todo-java.git
   cd Todo-java
   ```

2. **Run the application**
   ```bash
   # Using Maven wrapper (recommended)
   ./mvnw spring-boot:run
   
   # Or using Maven
   mvn spring-boot:run
   ```

3. **Access the application**
   - Open your browser and go to: `http://localhost:8080`

### Building for Production

```bash
# Build JAR file
./mvnw clean package

# Run the JAR
java -jar target/todo-app-0.0.1-SNAPSHOT.jar
```

## 🐳 Docker Deployment

### Build and Run with Docker

```bash
# Build Docker image
docker build -t todo-app .

# Run container
docker run -p 8080:8080 todo-app
```

### Docker Hub
The application is containerized and ready for deployment on any Docker-compatible platform.

## 🌐 Deployment

The application is deployed on **Render** using Docker containers:

- **Platform**: Render.com
- **Environment**: Production
- **Database**: H2 (in-memory)
- **Runtime**: Java 17
- **Container**: Docker

### Deployment Features
- ✅ Automatic deployments from GitHub
- ✅ HTTPS enabled
- ✅ Environment-specific configurations
- ✅ Health monitoring
- ✅ Auto-scaling capabilities


## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is open source and is available 

## 👨‍💻 Author

**Harshan MV**

## 🔮 Future Enhancements

- [ ] User authentication and authorization
- [ ] PostgreSQL database integration
- [ ] RESTful API endpoints
- [ ] Task categories and tags
- [ ] Due dates and reminders
- [ ] Search and filter functionality
- [ ] Dark mode theme
- [ ] Mobile app version
      
 Check the website https://todo-java-13wl.onrender.com
---

⭐ **Star this repository if you found it helpful!** ⭐

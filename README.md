# 📣 Echo Room

**Echo Room** is a web application inspired by WhatsApp and similar real-time messaging platforms. It aims to deliver a responsive, modern chat experience supported by a scalable microservices architecture.

---

## 🧩 Architecture

The application consists of multiple distributed services communicating using different protocols tailored to specific needs:

- **HTTP/REST**: Used for serving HTML pages and handling public APIs (e.g., user authentication, group creation).
- **WebSockets**: Enables real-time bidirectional communication between client and server for the chat functionality.
- **gRPC**: Provides efficient, strongly-typed communication between internal microservices.
- **RabbitMQ**: Acts as a message broker for asynchronous, event-driven communication.
- **Spring Cloud Eureka (planned)**: Service discovery to improve scalability and resilience in the future.

> ⚠️ The architecture is evolving and may change as the project grows.

---

## 📷 Architecture Diagram

![architecture](https://github.com/user-attachments/assets/3fc017e7-0a7d-40df-b04e-0b8b26460287)

---

## 🚀 Tech Stack

- **Frontend**: React
- **Backend**: Spring Boot (Java)
- **gRPC & Protocol Buffers**: Internal microservice communication
- **WebSockets**: Real-time messaging
- **RabbitMQ**: Asynchronous message handling
- **Spring Cloud (future)**: Eureka
- **Docker**: Application containerization
- **PostgreSQL**: Data persistence

---

## 📌 Project Goals

- Build a functional, scalable chat system
- Apply solid microservices architecture principles
- Integrate multiple communication strategies (REST, gRPC, WebSockets, async messaging)
- Demonstrate practical skills in modern technologies

---

## 🛠️ Work in Progress

- [x] Initial service structure
- [x] Build the Authorization Server
- [ ] gRPC integration between the services
- [ ] WebSocket-based real-time chat module
- [ ] Docker Compose setup for multi-service deployment
- [ ] Service discovery with Eureka

---

## 🔐 Authorization Server

The Authorization Server handles user authentication and OAuth2 client management within the system. It provides pages for user registration and login, facilitating secure access to the application.

- It communicates with the Users Service via gRPC to manage user data — it does not store any user information itself.
- All user details (creation, retrieval, updates) are handled exclusively by the Users Service.
- Additionally, the Authorization Server includes a page for creating OAuth clients, which will be extended with more features in the future.

## 💬 Conversation Service

This service is responsible for managing all conversations within the application, including both private one-on-one chats and group conversations. It provides essential operations to create, update, and delete conversations, manage participants, and configure permissions and related metadata.

Currently, there are two entities representing the types of conversations in the system: **PRIVATE_CHAT**, which involves exactly two participants, and **GROUP_CHAT**, which includes two or more participants and supports additional features such as changing the group’s name, description, image, and more.

![conversation-service](https://github.com/user-attachments/assets/04359bc6-61a5-48e4-84fb-4f68216edf57)

---

## 🧠 Contribution & Feedback

This project is part of my personal portfolio. If you have suggestions, ideas, or want to collaborate, feel free to open an issue or reach out!

---

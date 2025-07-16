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

![architecture](https://github.com/user-attachments/assets/26fa364c-ce27-474b-a0e2-f46cd2d47f27)

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
- Demonstrate practical skills in modern technologies and DevOps practices

---

## 🛠️ Work in Progress

- [x] Initial service structure
- [ ] gRPC integration between the services
- [ ] WebSocket-based real-time chat module
- [ ] Docker Compose setup for multi-service deployment
- [ ] Service discovery with Eureka

---

## 🧠 Contribution & Feedback

This project is part of my personal portfolio. If you have suggestions, ideas, or want to collaborate, feel free to open an issue or reach out!

---

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
- Demonstrate practical skills in modern technologies and DevOps practices

---

## 🛠️ Work in Progress

- [x] Initial service structure
- [x] Build the Authorization Server
- [ ] gRPC integration between the services
- [ ] WebSocket-based real-time chat module
- [ ] Docker Compose setup for multi-service deployment
- [ ] Service discovery with Eureka

---

## 💬 Conversation Service

This service is responsible for managing all conversations within the application, including both private one-on-one chats and group conversations. It provides essential operations to create, update, and delete conversations, manage participants, and configure permissions and related metadata.

Currently, there are two entities representing the types of conversations in the system: **PRIVATE_CHAT**, which involves exactly two participants, and **GROUP_CHAT**, which includes two or more participants and supports additional features such as changing the group’s name, description, image, and more.

![conversation-service](https://github.com/user-attachments/assets/7cfc9a09-d833-46bc-a246-9de8a1fd4713)

---

## 🧠 Contribution & Feedback

This project is part of my personal portfolio. If you have suggestions, ideas, or want to collaborate, feel free to open an issue or reach out!

---

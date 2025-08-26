# BookHub API

**BookHub** is a RESTful API platform designed to manage books, users, and reviews. It supports secure user registration and authentication using JWT, and provides endpoints to manage books, categories, and providers.

---

## Features

- **User Authentication**
  - JWT-based registration and login.
- **Book Management**
  - Create, retrieve, update, and delete books.
  - Retrieve books by category.
- **Category Management**
  - Create, retrieve, and delete book categories.
- **Provider Management**
  - Create, retrieve, and delete book providers.

---

## Technology Stack

- **Language:** Java 17+
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Tokens)
- **Build Tool:** Maven

---

## API Endpoints

### Authentication

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| POST   | `/bookhub/auth/register`  | Register a new user              |
| POST   | `/bookhub/auth/login`     | Authenticate and get JWT token   |

---

### Book

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| GET    | `/bookhub/books`          | Retrieve all books               |
| GET    | `/bookhub/books/{id}`     | Retrieve book by ID              |
| POST   | `/bookhub/books`          | Create a new book                |
| PUT    | `/bookhub/books/{id}`     | Update a book by ID              |
| DELETE | `/bookhub/books/{id}`     | Delete a book by ID              |
| GET    | `/bookhub/books/source`   | Retrieve books by category       |

---

### Category

| Method | Endpoint                      | Description                      |
|--------|-------------------------------|----------------------------------|
| GET    | `/bookhub/category`           | Retrieve all categories          |
| GET    | `/bookhub/category/{id}`      | Retrieve category by ID          |
| POST   | `/bookhub/category`           | Create a new category            |
| DELETE | `/bookhub/category/{id}`      | Delete category by ID            |

---

### Provider

| Method | Endpoint                      | Description                      |
|--------|-------------------------------|----------------------------------|
| GET    | `/bookhub/provider`           | Retrieve all providers           |
| GET    | `/bookhub/provider/{id}`      | Retrieve provider by ID          |
| POST   | `/bookhub/provider`           | Create a new provider            |
| DELETE | `/bookhub/provider/{id}`      | Delete provider by ID            |

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL (configured with appropriate credentials)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/bookhub.git
   cd bookhub

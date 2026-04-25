# Job Search API with Advanced Filtering

A robust, high-performance RESTful API built with **Spring Boot 4.0.5** for searching and managing job listings. This project focuses on efficient data retrieval, multi-criteria filtering, and secure public-facing endpoints.

## 🎯 Project Overview
This API allows users to search through large datasets of job listings using advanced filters such as keywords, location, and job types. It is designed with a focus on **Query Optimization**, **Full-Text Search**, and **Security**.

## ✨ Key Features
- **Advanced Multi-Criteria Search:** Combine multiple filters (location, type, keyword) in a single request.
- **Full-Text Indexing:** Optimized search across job titles and descriptions using database-level full-text indexing.
- **Pagination & Sorting:** Efficient cursor-based or limit/offset pagination to handle large datasets seamlessly.
- **Suggested Jobs:** A dedicated endpoint that provides job suggestions based on partial keyword matches.
- **Security-First:** Built-in protection against SQL Injection and excessive query abuse via strict input validation.

## ⚙️ Tech Stack
- **Framework:** Spring Boot 4.0.5
- **Language:** Java 21+
- **Database:** PostgreSQL (utilizing GIN/Full-Text indexes)
- **Containerization:** Docker & Docker Compose
- **Build Tool:** Maven
- **Communication:** REST API

## 🚀 Getting Started

### Prerequisites
- JDK 21 or higher
- Docker & Docker Compose

### Installation
Clone the repository:
   ```bash
   git clone [https://github.com/ramil-astanli/job-search-api.git](https://github.com/ramil-astanli/job-search-api.git)

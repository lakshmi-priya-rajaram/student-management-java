# Student-Management-Java

Simple Java console CRUD app using MySQL (JDBC).

## Files
- StudentManagement.java
- schema.sql
- README.md

## Setup

### 1) Install prerequisites
- Java JDK (11+ recommended)
- MySQL server
- MySQL Connector/J (JDBC driver) — [mysql-connector-java-x.x.x.jar]

### 2) Create DB
Run the SQL in `schema.sql` (via MySQL Workbench / CLI):
```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

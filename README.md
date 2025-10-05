# Expense_Tracker

A simple and clean web application to manage daily expenses using **Spring Boot, Thymeleaf, and SQL Server**.

---

## 🚀 Features
- ➕ Add, ✏️ Edit, ❌ Delete, and 👀 View expenses
- 🗂️ Filter by category and date range
- 💾 SQL Server database integration
- 🎨 Responsive UI (Bootstrap + Thymeleaf)
- 🧠 Spring Data JPA with repository pattern

---

## 🧠 Tech Stack
| Layer | Technology |
|-------|-------------|
| Backend | Spring Boot |
| Frontend | Thymeleaf + Bootstrap |
| Database | SQL Server |
| ORM | Spring Data JPA |
| Build Tool | Maven |
| Language | Java 21 |

---

## ⚙️ Setup and Run Instructions

2️⃣ Import Database

download "expense_db.bak" inside db folder 

Open SQL Server Management Studio

Right-click Databases → Restore Database

Choose Device → Browse → Add → expense_db.bak

Click OK to restore.

3️⃣ open the Application

open in IntelliJ/Eclipse IDE
open expense_tracker folder inside IDE


4️⃣ Configure Database Connection

Open src/main/resources/application.properties and update your username and password also verify database:

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=expense_db;encrypt=false

spring.datasource.username=your_username

spring.datasource.password=your_password

spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

run ExpenseTrackerApplication.java.


5️⃣ Run and access the Application

run ExpenseTrackerApplication.java inside "src/main/java/com.assignment.expense_tracker"

 Access the App

👉 http://localhost:8080

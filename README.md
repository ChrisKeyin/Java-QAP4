# Java File & Database Persistence Project

## Project Description

This is a simple Java console application that shows two persistence techniques.

1. Writing Java objects to a text file using File I/O
2. Writing Java objects to a PostgreSQL database using JDBC

The app shows two entities:

- `Drug` (stored in a text file)
- `Patient` (stored in a PostgreSQL database)

A menu-based interface (using `Scanner`) allows the user to:

1. Save `Drug` data to a text file
2. Read and display all `Drug` data from the text file
3. Save `Patient` data to a PostgreSQL database
4. Read and display all `Patient` data from the database

---

 ## Technologies Used

- Java (console application)
- Maven (dependency management)
- PostgreSQL
- JDBC (PostgreSQL driver)
- File I/O (`BufferedWriter`, `BufferedReader`)
- IntelliJ IDEA

---

## How to Run the Project

### 1. Clone the Repository

```bash
git clone <your-github-repo-url>
cd <your-project-folder>
```

---

### 2. Database Setup (PostgreSQL)

1. Open pgAdmin and connect to your PostgreSQL server.
2. Create a database, `pharmacydb`.
3. Run the SQL script from `patient_table.sql`:

```sql
CREATE TABLE IF NOT EXISTS patient (
    patient_id      INT PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    date_of_birth   DATE NOT NULL
);
```

4. Update the database connection details in:

`src/main/java/com/chrisking/pharmacy/PatientDatabaseManager.java`

Set:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/pharmacydb";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password_here";
```

---

### 3. Build & Run

You can run the app directly from IntelliJ or with Maven.

From IntelliJ:

- Open the project
- Run the `App` class (`com.chrisking.pharmacy.App`)

---

## Menu Options

When you run the application, you will see:

```
===== Pharmacy Menu =====
1. Save Drug to file
2. Read all Drugs from file
3. Save Patient to database
4. Read all Patients from database
0. Exit
```

---

## Example Usage

### Save a Drug to File

- Choose option 1
- Enter:
    - drug ID
    - drug name
    - drug cost
    - dosage

The drug is appended to `drugs.txt`.

---

### Read All Drugs from File

- Choose option 2
- All saved drugs are printed to the console.

---

### Save a Patient to Database

- Choose option 3
- Enter:
    - patient ID
    - first name
    - last name
    - date of birth (`YYYY-MM-DD`)

The patient is inserted into the `patient` table.

---

### Read All Patients from Database

- Choose option 4
- All rows in the `patient` table are printed to the console.

---

## Reflection

This project helped me practice working with both file-based and database-backed persistence.  
I implemented File I/O for the `Drug` entity and JDBC with PostgreSQL for the `Patient` entity.  
Configuring the database and making sure the SQL table matched the Java code was the main challenge, but once connected, the persistence operations and menu system worked smoothly.

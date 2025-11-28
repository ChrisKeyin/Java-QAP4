CREATE TABLE IF NOT EXISTS patient (
    patient_id      INT PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    date_of_birth   DATE NOT NULL
);


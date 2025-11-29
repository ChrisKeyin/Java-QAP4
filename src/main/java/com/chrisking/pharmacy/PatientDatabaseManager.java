package com.chrisking.pharmacy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseManager {

    // JDBC connection URL for the PostgreSQL database
    private static final String URL = "jdbc:postgresql://localhost:1508/pharmacydb";

    // Database user (adjust as needed)
    private static final String USER = "postgres";

    // Database password (stored here for the simplicity of the project, should be hashed)
    private static final String PASSWORD = "Vader10156$";

    /**
     * Constructor verifies the database connection on startup.
     * Prints a message indicating whether the connection succeeded.
     */
    public PatientDatabaseManager() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Successfully connected to PostgreSQL.");
        } catch (SQLException e) {
            System.out.println("Could not connect to PostgreSQL: " + e.getMessage());
        }
    }

    /**
     * Insert a patient record into the database.
     * Expects Patient.patientDOB to be in ISO format "YYYY-MM-DD" which is parsed to java.sql.Date.
     *
     * @param patient Patient object to insert
     */
    public void insertPatient(Patient patient) {
        String sql = "INSERT INTO patient (patient_id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters to the prepared statement
            stmt.setInt(1, patient.getPatientId());
            stmt.setString(2, patient.getPatientFirstName());
            stmt.setString(3, patient.getPatientLastName());
            stmt.setDate(4, Date.valueOf(patient.getPatientDOB()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient saved to database successfully.");
            } else {
                System.out.println("No rows inserted (something went wrong).");
            }

        } catch (IllegalArgumentException e) {
            // Thrown by Date.valueOf when the date string is invalid
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (SQLException e) {
            // SQL errors such as constraint violations, connectivity issues, etc.
            System.out.println("Error inserting patient into database: " + e.getMessage());
        }
    }

    /**
     * Retrieve all patients from the database ordered by patient_id.
     *
     * @return list of Patient objects (empty list if none or on error)
     */
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT patient_id, first_name, last_name, date_of_birth FROM patient ORDER BY patient_id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate over result set and build Patient objects
            while (rs.next()) {
                int id = rs.getInt("patient_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dob = rs.getDate("date_of_birth");

                // Convert SQL Date to String for the Patient POJO
                String dobString = dob.toString();

                Patient patient = new Patient(id, firstName, lastName, dobString);
                patients.add(patient);
            }

        } catch (SQLException e) {
            // Log and return whatever has been collected so far (or empty list on failure)
            System.out.println("Error reading patients from database: " + e.getMessage());
        }

        return patients;
    }
}

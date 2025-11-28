package com.chrisking.pharmacy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:1508/pharmacydb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Vader10156$";

    public PatientDatabaseManager() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Successfully connected to PostgreSQL.");
        } catch (SQLException e) {
            System.out.println("Could not connect to PostgreSQL: " + e.getMessage());
        }
    }

    public void insertPatient(Patient patient) {
        String sql = "INSERT INTO patient (patient_id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (SQLException e) {
            System.out.println("Error inserting patient into database: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT patient_id, first_name, last_name, date_of_birth FROM patient ORDER BY patient_id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("patient_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dob = rs.getDate("date_of_birth");

                String dobString = dob.toString();

                Patient patient = new Patient(id, firstName, lastName, dobString);
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.out.println("Error reading patients from database: " + e.getMessage());
        }

        return patients;
    }
}

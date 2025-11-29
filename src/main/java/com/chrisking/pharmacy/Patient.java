package com.chrisking.pharmacy;

public class Patient {

    // Unique patient identifier
    private int patientId;

    // Patient first name
    private String patientFirstName;

    // Patient last name
    private String patientLastName;

    // Patient date of birth
    private String patientDOB;

    /**
     * Create a Patient instance.
     *
     * @param patientId        unique id
     * @param patientFirstName first name
     * @param patientLastName  last name
     * @param patientDOB       date of birth string
     */
    public Patient(int patientId, String patientFirstName, String patientLastName, String patientDOB) {
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientDOB = patientDOB;
    }

    // Getter for patient id
    public int getPatientId() {
        return patientId;
    }

    // Getter for first name
    public String getPatientFirstName() {
        return patientFirstName;
    }

    // Getter for last name
    public String getPatientLastName() {
        return patientLastName;
    }

    // Getter for date of birth
    public String getPatientDOB() {
        return patientDOB;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + patientFirstName + '\'' +
                ", lastName='" + patientLastName + '\'' +
                ", dob='" + patientDOB + '\'' +
                '}';
    }
}

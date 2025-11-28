package com.chrisking.pharmacy;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DrugFileManager drugFileManager = new DrugFileManager();
    private static final PatientDatabaseManager patientDatabaseManager = new PatientDatabaseManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> handleSaveDrugToFile();
                case 2 -> handleReadDrugsFromFile();
                case 3 -> handleSavePatientToDatabase();
                case 4 -> handleReadPatientsFromDatabase();
                case 0 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== Pharmacy Menu =====");
        System.out.println("1. Save Drug to file");
        System.out.println("2. Read all Drugs from file");
        System.out.println("3. Save Patient to database");
        System.out.println("4. Read all Patients from database");
        System.out.println("0. Exit");
    }

    private static void handleSaveDrugToFile() {
        System.out.println("--- Save Drug to File ---");

        int id = readInt("Enter drug ID (integer): ");
        String name = readString("Enter drug name: ");
        double cost = readDouble("Enter drug cost: ");
        String dosage = readString("Enter dosage (e.g. '10mg', '1 tablet'): ");

        Drug drug = new Drug(id, name, cost, dosage);
        drugFileManager.saveDrugToFile(drug);
    }

    private static void handleReadDrugsFromFile() {
        System.out.println("--- Read Drugs from File ---");
        List<Drug> drugs = drugFileManager.readAllDrugsFromFile();

        if (drugs.isEmpty()) {
            System.out.println("No drugs found in file.");
        } else {
            System.out.println("Drugs in file:");
            for (Drug drug : drugs) {
                System.out.println(drug);
            }
        }
    }

    private static void handleSavePatientToDatabase() {
        System.out.println("--- Save Patient to Database ---");

        int id = readInt("Enter patient ID (integer): ");
        String firstName = readString("Enter patient first name: ");
        String lastName = readString("Enter patient last name: ");
        String dob = readString("Enter patient date of birth (YYYY-MM-DD): ");

        Patient patient = new Patient(id, firstName, lastName, dob);
        patientDatabaseManager.insertPatient(patient);
    }

    private static void handleReadPatientsFromDatabase() {
        System.out.println("--- Read Patients from Database ---");
        List<Patient> patients = patientDatabaseManager.getAllPatients();

        if (patients.isEmpty()) {
            System.out.println("No patients found in database.");
        } else {
            System.out.println("Patients in database:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number (e.g. 12.99).");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

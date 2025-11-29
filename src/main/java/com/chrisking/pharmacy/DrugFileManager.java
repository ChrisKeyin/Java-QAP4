package com.chrisking.pharmacy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DrugFileManager {

    // File used to store drug records (one record per line)
    private static final String FILE_NAME = "drugs.txt";

    /**
     * Append a Drug record to the file.
     * Writes a single line as produced by Drug.toFileLine().
     *
     * @param drug the Drug to save
     */
    public void saveDrugToFile(Drug drug) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(drug.toFileLine());
            writer.newLine();
            System.out.println("Drug saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving drug to file: " + e.getMessage());
        }
    }

    /**
     * Read all Drug records from the file.
     * Skips invalid lines and returns an empty list if the file does not exist.
     *
     * @return list of parsed Drug objects
     */
    public List<Drug> readAllDrugsFromFile() {
        List<Drug> drugs = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No drug file found yet. Nothing to read.");
            return drugs;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Drug drug = Drug.fromFileLine(line);
                    drugs.add(drug);
                } catch (IllegalArgumentException ex) {
                    // Invalid line format: skip and continue reading remaining lines
                    System.out.println("Skipping invalid line in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading drugs from file: " + e.getMessage());
        }

        return drugs;
    }
}

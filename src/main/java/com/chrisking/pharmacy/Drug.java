package com.chrisking.pharmacy;

public class Drug {

    // Unique identifier for the drug
    private int drugId;

    // Human readable name of the drug
    private String drugName;

    // Cost of the drug
    private double drugCost;

    // Dosage information
    private String dosage;

    /**
     * Create a Drug instance.
     *
     * @param drugId   unique id
     * @param drugName name of the drug
     * @param drugCost cost as a decimal value
     * @param dosage   dosage description
     */
    public Drug(int drugId, String drugName, double drugCost, String dosage) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCost = drugCost;
        this.dosage = dosage;
    }

    // Getter for id
    public int getDrugId() {
        return drugId;
    }

    // Getter for name
    public String getDrugName() {
        return drugName;
    }

    // Getter for cost
    public double getDrugCost() {
        return drugCost;
    }

    // Getter for dosage
    public String getDosage() {
        return dosage;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + drugId +
                ", drugName='" + drugName + '\'' +
                ", drugCost=" + drugCost +
                ", dosage='" + dosage + '\'' +
                '}';
    }

    /**
     * Convert this Drug to a single line suitable for file storage.
     * Uses '|' as the field separator.
     *
     * @return file line representation
     */
    public String toFileLine() {
        return drugId + "|" + drugName + "|" + drugCost + "|" + dosage;
    }

    /**
     * Parse a Drug from a file line produced by toFileLine().
     *
     * @param line the line read from file
     * @return parsed Drug instance
     * @throws IllegalArgumentException if the line format is invalid
     */
    public static Drug fromFileLine(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid line for Drug: " + line);
        }
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double cost = Double.parseDouble(parts[2]);
        String dosage = parts[3];
        return new Drug(id, name, cost, dosage);
    }
}

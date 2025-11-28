package com.chrisking.pharmacy;

public class Drug {

    private int drugId;
    private String drugName;
    private double drugCost;
    private String dosage;

    public Drug(int drugId, String drugName, double drugCost, String dosage) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCost = drugCost;
        this.dosage = dosage;
    }

    public int getDrugId() {
        return drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public double getDrugCost() {
        return drugCost;
    }

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

    public String toFileLine() {
        return drugId + "|" + drugName + "|" + drugCost + "|" + dosage;
    }

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

package model;

import java.util.Date;

public class Patient implements Comparable<Patient> {
    private String name;
    private String condition;
    private int severityScore;
    private Date admissionDate;

    // Constructor
    public Patient(String name, String condition, int severityScore) {
        this.name = name;
        this.condition = condition;
        this.severityScore = severityScore;
        this.admissionDate = new Date();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public int getSeverityScore() {
        return severityScore;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    // Comparable implementation for sorting
    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.severityScore, other.severityScore);
    }

    @Override
    public String toString() {
        return "Patient: " +
                "name='" + name + '\'' +
                ", condition='" + condition + '\'' +
                ", severityScore=" + severityScore +
                ", admissionDate=" + admissionDate ;
    }
}
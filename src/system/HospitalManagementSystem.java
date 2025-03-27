package system;

import model.Patient;
import datastructures.PatientLinkedList;
import datastructures.EmergencyQueue;
import datastructures.DischargeStack;

public class HospitalManagementSystem {
    private PatientLinkedList patientList;
    private EmergencyQueue emergencyQueue;
    private DischargeStack dischargeStack;

    public HospitalManagementSystem() {
        patientList = new PatientLinkedList();
        emergencyQueue = new EmergencyQueue();
        dischargeStack = new DischargeStack();
    }

    // Add a new patient
    public void admitPatient(String name, String condition, int severityScore) {
        Patient newPatient = new Patient(name, condition, severityScore);
        patientList.addPatient(newPatient);

        // Automatically add to emergency queue if severity is high
        if (severityScore > 7) {
            emergencyQueue.addEmergencyPatient(newPatient);
        }
    }

    // Find a patient by name
    public Patient findPatient(String name) {
        return patientList.searchPatient(name);
    }

    // Process next emergency patient
    public Patient processNextEmergency() {
        return emergencyQueue.treatNextPatient();
    }

    // Discharge a patient
    public void dischargePatient(Patient patient) {
        dischargeStack.recordDischarge(patient);
    }

    // Undo last discharge
    public Patient undoLastDischarge() {
        return dischargeStack.undoLastDischarge();
    }

    // Count patients above a certain severity
    public int countCriticalPatients(int severityThreshold) {
        return patientList.countPatientsAboveSeverity(severityThreshold);
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        HospitalManagementSystem hospital = new HospitalManagementSystem();

        // Admit patients
        hospital.admitPatient("John Doe", "Heart Attack", 9);
        hospital.admitPatient("Jane Smith", "Broken Leg", 4);
        hospital.admitPatient("Mike Johnson", "Stroke", 8);

        // Find a patient
        Patient foundPatient = hospital.findPatient("John Doe");
        System.out.println("Found Patient: " + foundPatient);

        // Process emergencies
        while (hospital.emergencyQueue.hasEmergencyPatients()) {
            Patient emergency = hospital.processNextEmergency();
            System.out.println("Treating Emergency: " + emergency);
        }

        // Count critical patients
        int criticalPatients = hospital.countCriticalPatients(7);
        System.out.println("Number of Critical Patients: " + criticalPatients);

        // Discharge a patient
        hospital.dischargePatient(foundPatient);

        // Undo last discharge
        Patient undischarged = hospital.undoLastDischarge();
        System.out.println("Undid Discharge: " + undischarged);
    }
}
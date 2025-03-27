package datastructures;

import java.util.PriorityQueue;
import model.Patient;

public class EmergencyQueue {
    private PriorityQueue<Patient> emergencyPatients;

    public EmergencyQueue() {
        // Use reverse order to have highest severity first
        emergencyPatients = new PriorityQueue<>((p1, p2) ->
                Integer.compare(p2.getSeverityScore(), p1.getSeverityScore()));
    }

    public void addEmergencyPatient(Patient patient) {
        emergencyPatients.offer(patient);
    }

    public Patient treatNextPatient() {
        return emergencyPatients.poll();
    }

    public boolean hasEmergencyPatients() {
        return !emergencyPatients.isEmpty();
    }
}
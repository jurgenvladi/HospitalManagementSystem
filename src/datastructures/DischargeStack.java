package datastructures;

import java.util.Stack;
import model.Patient;

public class DischargeStack {
    private Stack<Patient> dischargedPatients;

    public DischargeStack() {
        dischargedPatients = new Stack<>();
    }

    public void recordDischarge(Patient patient) {
        dischargedPatients.push(patient);
    }

    public Patient undoLastDischarge() {
        if (!dischargedPatients.isEmpty()) {
            return dischargedPatients.pop();
        }
        return null;
    }
}
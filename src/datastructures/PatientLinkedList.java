package datastructures;

import model.Patient;

public class PatientLinkedList {
    private class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public PatientLinkedList() {
        head = null;
        size = 0;
    }

    // Add patient to the linked list
    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Binary search to find patient by name
    public Patient searchPatient(String name) {
        return binarySearchPatient(name);
    }

    private Patient binarySearchPatient(String name) {
        // Convert linked list to sorted array for binary search
        Patient[] patients = toSortedArray();

        int left = 0;
        int right = patients.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = name.compareTo(patients[mid].getName());

            if (comparison == 0) {
                return patients[mid];
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }

    // Convert linked list to sorted array
    private Patient[] toSortedArray() {
        Patient[] patients = new Patient[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            patients[i] = current.patient;
            current = current.next;
        }

        // Selection Sort
        for (int i = 0; i < patients.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < patients.length; j++) {
                if (patients[j].getName().compareTo(patients[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            // Swap
            Patient temp = patients[minIndex];
            patients[minIndex] = patients[i];
            patients[i] = temp;
        }

        return patients;
    }

    // Recursive method to count patients above severity level
    public int countPatientsAboveSeverity(int severityThreshold) {
        return recursiveCountSeverity(head, severityThreshold);
    }

    private int recursiveCountSeverity(Node node, int severityThreshold) {
        // Base case
        if (node == null) {
            return 0;
        }

        // Count current node if above threshold
        int count = (node.patient.getSeverityScore() > severityThreshold) ? 1 : 0;

        // Recursively count for next nodes
        return count + recursiveCountSeverity(node.next, severityThreshold);
    }
}
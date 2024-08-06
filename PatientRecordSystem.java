import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientRecordSystem {
    // List to store patients and observation types
    private List<Patient> patients;
    private List<ObservationType> observationTypes;

    // Constructor to initialize lists
    public PatientRecordSystem() {
        this.patients = new ArrayList<>();
        this.observationTypes = new ArrayList<>();
    }

    // Method to add a measurement observation type
    public void addMeasurementObservationType(String code, String name, String unit) {
        // Check if the observation type already exists
        for (ObservationType observationType : observationTypes) {
            if (observationType.getCode().equals(code)) {
                System.out.println("Error: Observation type with code '" + code + "' already exists.");
                return;
            }
        }
        // If not, add the observation type
        observationTypes.add(new MeasurementObservationType(code, name, unit));
    }

    // Method to add a category observation type
    public void addCategoryObservationType(String code, String name, String[] categories) {
        // Check if the observation type already exists
        for (ObservationType observationType : observationTypes) {
            if (observationType.getCode().equals(code)) {
                System.out.println("Error: Observation type with code '" + code + "' already exists.");
                return;
            }
        }
        // If not, add the observation type
        observationTypes.add(new CategoryObservationType(code, name, categories));
    }

    // Method to add a patient
    public void addPatient(String id, String name) {
        // Check if the patient already exists
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                System.out.println("Error: Patient with ID '" + id + "' already exists.");
                return;
            }
        }
        // If not, add the patient
        patients.add(new Patient(id, name));
    }

    // Method to add a measurement observation for a patient
    public void addMeasurementObservation(String patientId, String observationTypeCode, double value) {
        // Find the patient by ID
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Error: Patient with ID '" + patientId + "' not found.");
            return;
        }
        // Find the measurement observation type by code
        MeasurementObservationType observationType = findMeasurementObservationTypeByCode(observationTypeCode);
        if (observationType == null) {
            System.out.println("Error: Measurement observation type with code '" + observationTypeCode + "' not found.");
            return;
        }
        // Add the measurement observation to the patient
        patient.addObservation(new MeasurementObservation(observationType, value));
    }

    // Method to add a category observation for a patient
    public void addCategoryObservation(String patientId, String observationTypeCode, String category) {
        // Find the patient by ID
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Error: Patient with ID '" + patientId + "' not found.");
            return;
        }
        // Find the category observation type by code
        CategoryObservationType observationType = findCategoryObservationTypeByCode(observationTypeCode);
        if (observationType == null) {
            System.out.println("Error: Category observation type with code '" + observationTypeCode + "' not found.");
            return;
        }
        // Add the category observation to the patient
        patient.addObservation(new CategoryObservation(observationType, 0, category));
    }

    // Method to find a patient by ID
    private Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    // Method to find a measurement observation type by code
    private MeasurementObservationType findMeasurementObservationTypeByCode(String code) {
        for (ObservationType observationType : observationTypes) {
            if (observationType instanceof MeasurementObservationType && observationType.getCode().equals(code)) {
                return (MeasurementObservationType) observationType;
            }
        }
        return null;
    }

    // Method to find a category observation type by code
    private CategoryObservationType findCategoryObservationTypeByCode(String code) {
        for (ObservationType observationType : observationTypes) {
            if (observationType instanceof CategoryObservationType && observationType.getCode().equals(code)) {
                return (CategoryObservationType) observationType;
            }
        }
        return null;
    }

    // Method to load data from files
    public void loadData() throws IOException {
        loadMeasurementObservationTypes("PRS-MeasurementObservationTypes.txt");
        loadCategoryObservationTypes("PRS-CategoryObservationTypes.txt");
        loadPatients("PRS-Patients.txt");
        loadMeasurementObservations("PRS-MeasurementObservations.txt");
        loadCategoryObservations("PRS-CategoryObservations.txt");
    }

    // Method to save data to files
    public void saveData() throws IOException {
        createFileIfNotExists("PRS-MeasurementObservationTypes.txt");
        createFileIfNotExists("PRS-CategoryObservationTypes.txt");
        createFileIfNotExists("PRS-Patients.txt");
        createFileIfNotExists("PRS-MeasurementObservations.txt");
        createFileIfNotExists("PRS-CategoryObservations.txt");

        saveMeasurementObservationTypes("PRS-MeasurementObservationTypes.txt");
        saveCategoryObservationTypes("PRS-CategoryObservationTypes.txt");
        savePatients("PRS-Patients.txt");
        saveMeasurementObservations("PRS-MeasurementObservations.txt");
        saveCategoryObservations("PRS-CategoryObservations.txt");
    }

    // Method to create file if it doesn't exist
    private void createFileIfNotExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    // Method to save measurement observation types to file
    private void saveMeasurementObservationTypes(String fileName) throws IOException {
        List<String> existingLines = Files.readAllLines(Paths.get(fileName));
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (ObservationType observationType : observationTypes) {
                if (observationType instanceof MeasurementObservationType) {
                    String line = observationType.getCode() + ";" + observationType.getName() + ";" +
                            ((MeasurementObservationType) observationType).getUnit();
                    if (!existingLines.contains(line)) {
                        writer.println(line);
                    }
                }
            }
        }
    }

    // Method to save category observation types to file
    private void saveCategoryObservationTypes(String fileName) throws IOException {
        List<String> existingLines = Files.readAllLines(Paths.get(fileName));
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (ObservationType observationType : observationTypes) {
                if (observationType instanceof CategoryObservationType) {
                    String line = observationType.getCode() + ";" + observationType.getName() + ";" +
                            String.join(",", ((CategoryObservationType) observationType).getCategories());
                    if (!existingLines.contains(line)) {
                        writer.println(line);
                    }
                }
            }
        }
    }

    // Method to save patients to file
    private void savePatients(String fileName) throws IOException {
        List<String> existingLines = Files.readAllLines(Paths.get(fileName));
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (Patient patient : patients) {
                String line = patient.getId() + ";" + patient.getName();
                if (!existingLines.contains(line)) {
                    writer.println(line);
                }
            }
        }
    }

    // Method to save measurement observations to file
    private void saveMeasurementObservations(String fileName) throws IOException {
        List<String> existingLines = Files.readAllLines(Paths.get(fileName));
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (Patient patient : patients) {
                for (Observation observation : patient.getObservations()) {
                    if (observation instanceof MeasurementObservation) {
                        MeasurementObservation measurementObservation = (MeasurementObservation) observation;
                        String line = patient.getId() + ";" + observation.getObservationType().getCode() + ";" +
                                measurementObservation.getValue();
                        if (!existingLines.contains(line)) {
                            writer.println(line);
                        }
                    }
                }
            }
        }
    }

    // Method to save category observations to file
    private void saveCategoryObservations(String fileName) throws IOException {
        List<String> existingLines = Files.readAllLines(Paths.get(fileName));
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (Patient patient : patients) {
                for (Observation observation : patient.getObservations()) {
                    if (observation instanceof CategoryObservation) {
                        CategoryObservation categoryObservation = (CategoryObservation) observation;
                        String line = patient.getId() + ";" + observation.getObservationType().getCode() + ";" +
                                categoryObservation.getCategory();
                        if (!existingLines.contains(line)) {
                            writer.println(line);
                        }
                    }
                }
            }
        }
    }

    // Method to load measurement observation types from file
    private void loadMeasurementObservationTypes(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].startsWith("T")) {
                    addMeasurementObservationType(parts[0], parts[1], parts[2]);
                }
            }
        }
    }

    // Method to load category observation types from file
    private void loadCategoryObservationTypes(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 3 && parts[0].startsWith("T")) {
                    List<String> categories = new ArrayList<>(Arrays.asList(parts).subList(2, parts.length));
                    addCategoryObservationType(parts[0], parts[1], categories.toArray(new String[0]));
                }
            }
        }
    }

    // Method to load patients from file
    private void loadPatients(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2 && parts[0].startsWith("P")) {
                    addPatient(parts[0], parts[1]);
                }
            }
        }
    }

    // Method to load measurement observations from file
    private void loadMeasurementObservations(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String patientId = parts[0];
                    String observationTypeCode = parts[1];
                    String value = parts[2];
                    addMeasurementObservation(patientId, observationTypeCode, Double.parseDouble(value));
                }
            }
        }
    }

    // Method to load category observations from file
    private void loadCategoryObservations(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String patientId = parts[0];
                    String observationTypeCode = parts[1];
                    String category = parts[2];
                    addCategoryObservation(patientId, observationTypeCode, category);
                }
            }
        }
    }

    // Method to get details of an observation type
    public String getObservationTypeDetails(String code) {
        for (ObservationType observationType : observationTypes) {
            if (observationType.getCode().equals(code) && observationType instanceof MeasurementObservationType) {
                return observationType.toString();
            }
        }

        for (ObservationType observationType : observationTypes) {
            if (observationType.getCode().equals(code) && observationType instanceof CategoryObservationType) {
                return observationType.toString();
            }
        }

        return "Observation type not found.";
    }

    // Method to get the record of a patient
    public String getPatientRecord(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient.toString();
            }
        }
        return "Patient not found.";
    }

    // Override toString method to provide a summary of the patient record system
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient Record System:\n");

        stringBuilder.append("Measurement Observation Types:\n");
        for (ObservationType observationType : observationTypes) {
            if (observationType instanceof MeasurementObservationType) {
                stringBuilder.append(observationType.toString()).append("\n");
            }
        }
        stringBuilder.append("Category Observation Types:\n");
        for (ObservationType observationType : observationTypes) {
            if (observationType instanceof CategoryObservationType) {
                stringBuilder.append(observationType.toString()).append("\n");
            }
        }

        // Append patients
        stringBuilder.append("Patients:\n");
        for (Patient patient : patients) {
            stringBuilder.append(patient.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}

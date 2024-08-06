import java.io.IOException;
import java.util.Scanner;

public class PatientRecordSystemMenu {
    private static PatientRecordSystem patientRecordSystem = new PatientRecordSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        while (true) {
            System.out.println("===================== Patient Record System =====================");
            System.out.println("1. Add a measurement observation type");
            System.out.println("2. Add a category observation type");
            System.out.println("3. Add a patient");
            System.out.println("4. Add a measurement observation");
            System.out.println("5. Add a category observation");
            System.out.println("6. Display details of an observation type");
            System.out.println("7. Display a patient record by the patient id");
            System.out.println("8. Save data");
            System.out.println("9. Load data");
            System.out.println("D. Display all data for inspection");
            System.out.println("X. Exit");
            System.out.print("Please enter an option (1-9 or D or X): ");

            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "1":
                    addMeasurementObservationType();
                    break;
                case "2":
                    addCategoryObservationType();
                    break;
                case "3":
                    addPatient();
                    break;
                case "4":
                    addMeasurementObservation();
                    break;
                case "5":
                    addCategoryObservation();
                    break;
                case "6":
                    displayObservationTypeDetails();
                    break;
                case "7":
                    displayPatientRecord();
                    break;
                case "8":
                    saveData();
                    break;
                case "9":
                    loadData();
                    break;
                case "D":
                    displayAllData();
                    break;
                case "X":
                    System.out.println("Exiting... bye! :)");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void displayAllData() {
        System.out.println(patientRecordSystem.toString());
    }

    public static void addMeasurementObservationType() {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation type name: ");
        String name = scanner.nextLine();
        System.out.print("Enter observation type unit: ");
        String unit = scanner.nextLine();
        patientRecordSystem.addMeasurementObservationType(code, name, unit);
        System.out.println("Measurement observation type added successfully.");
    }

    public static void addCategoryObservationType() {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation type name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the number of categories: ");
        int numCategories = Integer.parseInt(scanner.nextLine());
        String[] categories = new String[numCategories];
        for (int i = 0; i < numCategories; i++) {
            System.out.print("Enter category " + (i + 1) + ": ");
            categories[i] = scanner.nextLine();
        }
        patientRecordSystem.addCategoryObservationType(code, name, categories);
        System.out.println("Category observation type added successfully.");
    }

    private static void addPatient() {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine().toUpperCase();
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        patientRecordSystem.addPatient(id, name);
        System.out.println("Patient added successfully.");
    }

    private static void addMeasurementObservation() {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation type code: ");
        String observationTypeCode = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation value: ");
        String value = scanner.nextLine();
        int intValue = Integer.parseInt(value);
        patientRecordSystem.addMeasurementObservation(patientId, observationTypeCode, intValue);
        System.out.println("Measurement observation added successfully.");
    }

    private static void addCategoryObservation() {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation type code: ");
        String observationTypeCode = scanner.nextLine().toUpperCase();
        System.out.print("Enter observation category: ");
        String category = scanner.nextLine();
        patientRecordSystem.addCategoryObservation(patientId, observationTypeCode, category);
        System.out.println("Category observation added successfully.");
    }

    private static void displayObservationTypeDetails() {
        System.out.print("Enter observation type code: ");
        String code = scanner.nextLine().toUpperCase();
        System.out.println(patientRecordSystem.getObservationTypeDetails(code));
    }

    private static void displayPatientRecord() {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine().toUpperCase();
        System.out.println(patientRecordSystem.getPatientRecord(id));
    }

    private static void saveData() {
        try {
            patientRecordSystem.saveData();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        try {
            patientRecordSystem.loadData();
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
    }
}

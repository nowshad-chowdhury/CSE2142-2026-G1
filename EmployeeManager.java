import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Validate that exactly one command-line argument is provided.
        if (args.length != 1) {
            System.out.println(Constants.INVALID_NUMBER_MESSAGE);
            return;
        }

        String command = args[0];

        // Display all employees stored in the employee file.
        if (command.equals(Constants.LIST_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName : readEmployeeFile().split(",")) {
                    System.out.println(employeeName.trim());
                }

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Select and display a random employee.
        else if (command.equals(Constants.RANDOM_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames = readEmployeeFile().split(",");

                System.out.println(
                        employeeNames[new Random().nextInt(employeeNames.length)]
                                .trim());

            } catch (IOException exception) {
                System.out.println("Error selecting random employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Add a new employee to the employee file.
        else if (command.startsWith(Constants.ADD_COMMAND)) {

            if (command.substring(1).trim().isEmpty()) {
                System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                return;
            }

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                writeEmployeeFile(", " + command.substring(1), true);

                System.out.println(Constants.ADDED_MESSAGE);

            } catch (IOException exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Search for an employee by name.
        else if (command.startsWith(Constants.SEARCH_COMMAND)) {

            String searchName = command.substring(1).trim();

            if (searchName.isEmpty()) {
                System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                return;
            }

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                boolean employeeFound = false;

                for (String employeeName : readEmployeeFile().split(",")) {

                    if (employeeName.trim().equals(searchName)) {
                        employeeFound = true;
                        break;
                    }
                }

                if (employeeFound) {
                    System.out.println(
                            "Employee '" + searchName + "' found!");
                } else {
                    System.out.println(
                            "Employee '" + searchName + "' not found!");
                }

            } catch (IOException exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Count the total number of employees.
        else if (command.equals(Constants.COUNT_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readEmployeeFile();

                int totalEmployeeCount = employeeData.split(",").length;

                System.out.println(
                        totalEmployeeCount + " employee(s) found.");

            } catch (IOException exception) {
                System.out.println("Error counting employees.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Update an employee's name.
        else if (command.startsWith(Constants.UPDATE_COMMAND)) {

            String employeeNameToUpdate = command.substring(1).trim();

            if (employeeNameToUpdate.isEmpty()) {
                System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                return;
            }

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames = readEmployeeFile().split(",");

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].trim()
                            .equals(employeeNameToUpdate)) {

                        employeeNames[employeeIndex] =
                                Constants.UPDATED_VALUE;
                    }
                }

                writeEmployeeFile(String.join(",", employeeNames), false);

                System.out.println(Constants.UPDATED_MESSAGE);

            } catch (IOException exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Delete an employee from the employee file.
        else if (command.startsWith(Constants.DELETE_COMMAND)) {

            String employeeNameToDelete = command.substring(1).trim();

            if (employeeNameToDelete.isEmpty()) {
                System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                return;
            }

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                List<String> employeeList = new ArrayList<>(
                        Arrays.asList(readEmployeeFile().split(",")));

                employeeList.removeIf(
                        employeeName -> employeeName.trim()
                                .equals(employeeNameToDelete));

                writeEmployeeFile(String.join(",", employeeList), false);

                System.out.println(Constants.DELETED_MESSAGE);

            } catch (IOException exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        }

        // Display an error message for unsupported commands.
        else {
            System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
        }
    }

    /**
     * Reads employee information from the employee file.
     *
     * @return employee information as a String
     * @throws IOException if the file cannot be read
     */
    private static String readEmployeeFile() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(Constants.EMPLOYEE_FILE))) {

            return reader.readLine();
        }
    }

    /**
     * Writes employee information to the employee file.
     *
     * @param employeeData employee information to write
     * @param append       true to append data, false to overwrite the file
     * @throws IOException if the file cannot be written
     */
    private static void writeEmployeeFile(
            String employeeData, boolean append) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append))) {

            writer.write(employeeData);
        }
    }
}
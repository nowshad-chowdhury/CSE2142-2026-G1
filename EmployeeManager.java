import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println(Constants.INVALID_NUMBER_MESSAGE);
            return;
        }

        if (args[0].equals(Constants.LIST_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName : readFile().split(",")) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].equals(Constants.RANDOM_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames = readFile().split(",");

                System.out.println(
                        employeeNames[new Random().nextInt(employeeNames.length)]);

            } catch (Exception exception) {
                System.out.println("Error selecting random employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith(Constants.ADD_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                if (args[0].length() == 1) {
                    System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                    return;
                }

                writeFile(", " + args[0].substring(1), true);

            } catch (Exception exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith(Constants.SEARCH_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String searchName = args[0].substring(1);

                if (searchName.trim().isEmpty()) {
                    System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                    return;
                }

                boolean employeeFound = false;

                for (String employeeName : readFile().split(",")) {

                    if (employeeName.trim().equals(searchName.trim())) {
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

            } catch (Exception exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].equals(Constants.COUNT_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readFile();

                int employeeCount = employeeData.split(",").length;

                System.out.println(
                        employeeCount + " employee(s) found.");

            } catch (Exception exception) {
                System.out.println("Error counting employees.");
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith(Constants.UPDATE_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                if (args[0].length() == 1) {
                    System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                    return;
                }

                String[] employeeNames = readFile().split(",");

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].trim().equals(
                            args[0].substring(1).trim())) {

                        employeeNames[employeeIndex] =
                                Constants.UPDATED_VALUE;
                    }
                }

                writeFile(String.join(",", employeeNames), false);

            } catch (Exception exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println(Constants.UPDATED_MESSAGE);

        } else if (args[0].startsWith(Constants.DELETE_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                if (args[0].length() == 1) {
                    System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
                    return;
                }

                List<String> employeeList = new ArrayList<>(
                        Arrays.asList(readFile().split(",")));

                employeeList.removeIf(
                        employeeName -> employeeName.trim().equals(
                                args[0].substring(1).trim()));

                writeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println(Constants.DELETED_MESSAGE);

        } else {

            // Handle invalid commands
            System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
        }
    }

    // Read employee data from file
    private static String readFile() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));

        String fileContent = reader.readLine();

        reader.close();

        return fileContent;
    }

    // Write employee data to file
    private static void writeFile(String fileContent, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append));

        writer.write(fileContent);

        writer.close();
    }
}
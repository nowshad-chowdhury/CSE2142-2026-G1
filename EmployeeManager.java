import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            return;
        }

        if (args[0].equals(Constants.LIST_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName : readFile().split(",")) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].equals(Constants.RANDOM_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames = readFile().split(",");

                System.out.println(
                        employeeNames[new Random().nextInt(employeeNames.length)]);

            } catch (Exception exception) {
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith("+")) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                writeFile(", " + args[0].substring(1), true);

            } catch (Exception exception) {
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith("?")) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                for (String employeeName : readFile().split(",")) {

                    if (employeeName.equals(args[0].substring(1))) {
                        System.out.println(Constants.FOUND_MESSAGE);
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].equals(Constants.COUNT_COMMAND)) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String employeeData = readFile();
                char[] characters = employeeData.toCharArray();

                boolean insideWord = false;
                int wordCount = 0;

                for (char character : characters) {

                    if (character == ' ') {

                        if (!insideWord) {
                            wordCount++;
                            insideWord = true;

                        } else {
                            insideWord = false;
                        }
                    }
                }

                System.out.println(
                        wordCount + " word(s) found " + employeeData.length());

            } catch (Exception exception) {
            }

            System.out.println(Constants.LOADED_MESSAGE);

        } else if (args[0].startsWith("u")) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                String[] employeeNames = readFile().split(",");

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(
                            args[0].substring(1))) {

                        employeeNames[employeeIndex] = Constants.UPDATED_VALUE;
                    }
                }

                writeFile(String.join(",", employeeNames), false);

            } catch (Exception exception) {
            }

            System.out.println(Constants.UPDATED_MESSAGE);

        } else if (args[0].startsWith("d")) {

            System.out.println(Constants.LOADING_MESSAGE);

            try {
                List<String> employeeList = new ArrayList<>(
                        Arrays.asList(readFile().split(",")));

                employeeList.remove(args[0].substring(1));

                writeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
            }

            System.out.println(Constants.DELETED_MESSAGE);
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
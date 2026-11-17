import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Task 2: Check the number of arguments
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                String[] employeeNames = fileContent.split(",");

                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                System.out.println(fileContent);

                String[] employeeNames = fileContent.split(",");

                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employeeNames.length);

                System.out.println(employeeNames[randomIndex]);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {

            System.out.println("Loading data ...");

            try {
                String employeeName = args[0].substring(1);

                writeFile(", " + employeeName, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                String[] employeeNames = fileContent.split(",");

                boolean employeeFound = false;
                String searchName = args[0].substring(1);

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length && !employeeFound;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                char[] characters = fileContent.toCharArray();

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
                        wordCount + " word(s) found " + characters.length);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                String[] employeeNames = fileContent.split(",");

                String employeeName = args[0].substring(1);

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(employeeName)) {
                        employeeNames[employeeIndex] = "Updated";
                    }
                }

                writeFile(String.join(",", employeeNames), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {

            System.out.println("Loading data ...");

            try {
                String fileContent = readFile();
                String[] employeeNames = fileContent.split(",");

                String employeeName = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(
                        Arrays.asList(employeeNames));

                employeeList.remove(employeeName);

                writeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");
        }
    }

    // Task 4: Method for reading employee data
    private static String readFile() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));

        String fileContent = reader.readLine();

        reader.close();

        return fileContent;
    }

    // Task 4: Method for writing employee data
    private static void writeFile(String fileContent, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt", append));

        writer.write(fileContent);

        writer.close();
    }
}
package hu.robertszujo.seleniumproject.utils;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.constants.TestConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvDataReader {

    /**
     * Finds the value located at the intersection of a row identified by a value in the first column
     * and a column identified by its name in a CSV file.
     *
     * @param csvFileName the name of the CSV file (including path if necessary).
     * @param rowValue the value to search for in the first column.
     * @param columnName the name of the column whose value is to be retrieved.
     * @return the value found at the specified row and column intersection, or null if not found.
     * @throws IOException if there is an issue accessing the CSV file.
     */
    public static String getValueAt(String csvFileName, String rowValue, String columnName) throws IOException {
        String delimiter = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("CSV file is empty: " + csvFileName);
            }

            // Parse the header line to find the column index
            String[] headers = headerLine.split(delimiter);
            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column not found: " + columnName);
            }

            // Iterate through the file to find the row with the specified value in the first column
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length > 0 && values[0].trim().equalsIgnoreCase(rowValue)) {
                    if (columnIndex < values.length) {
                        return values[columnIndex].trim(); // Return the target value
                    } else {
                        throw new IllegalArgumentException("Column index out of bounds for row: " + rowValue);
                    }
                }
            }

            // If no matching row is found, return null or throw an exception
            throw new IllegalArgumentException("Row with value '" + rowValue + "' not found in the first column.");
        }
    }

    // Example usage
    public static String getTestData(String rowValue, String columnName) {
        try {
            String projectPath = System.getProperty("user.dir");
            String csvFile = projectPath+ TestConstants.TESTDATAPATH;

            String result = getValueAt(csvFile, rowValue, columnName);
            System.out.println("The test data is: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("No data found for row " + rowValue + " and column " + columnName + " in the specified CSV file");
            e.printStackTrace();
        }
        return null;
    }
}

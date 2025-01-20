package hu.robertszujo.seleniumproject.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {

    /**
     * Finds the value located at the intersection of a row identified by a value in the first column
     * and a column identified by its name.
     *
     * @param excelFileName the name of the Excel file (including path if necessary).
     * @param sheetName the name of the sheet within the workbook.
     * @param rowValue the value to search for in the first column.
     * @param columnName the name of the column whose value is to be retrieved.
     * @return the value found at the specified row and column intersection, or null if not found.
     * @throws IOException if there is an issue accessing the Excel file.
     */
    public static String getValueAt(String excelFileName, String sheetName, String rowValue, String columnName) throws IOException {

        try (
                FileInputStream fis = new FileInputStream(excelFileName);
                 Workbook workbook = new XSSFWorkbook(fis)) {
                // Get the specified sheet
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            // Find the column index based on the column name
            int columnIndex = getColumnIndex(workbook, sheet, columnName);

            // Iterate over the rows to find the row with the specified value in the first column
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (Row row : sheet) {
                Cell firstCell = row.getCell(0); // First column cell
                if (firstCell != null) {
                    String cellValue = dataFormatter.formatCellValue(firstCell, formulaEvaluator);

                    // Check if the value matches the desired row value
                    if (cellValue.equalsIgnoreCase(rowValue)) {
                        Cell targetCell = row.getCell(columnIndex);
                        return dataFormatter.formatCellValue(targetCell, formulaEvaluator); // Return the target cell value
                    }
                }
            }

            // If no matching row or column is found, return null or throw an exception
            throw new IllegalArgumentException("Row with value '" + rowValue + "' or column '" + columnName + "' not found.");
        }
    }

    /**
     * Retrieves the index of a column in an Excel sheet by its name.
     *
     * @param workbook the Workbook object representing the Excel file.
     * @param sheet the Sheet object representing the specific worksheet in the Excel file.
     * @param columnName the name of the column whose index is to be found.
     * @return the index of the column (zero-based) if the column name matches.
     * @throws IOException if an error occurs while processing the workbook.
     */
    private static int getColumnIndex(Workbook workbook, Sheet sheet, String columnName) throws IOException {
        DataFormatter dataFormatter = new DataFormatter();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

        Row headerRow = sheet.getRow(0); // Assume the first row contains headers
        if (headerRow == null) {
            throw new IllegalArgumentException("Header row is missing in sheet: " + sheet.getSheetName());
        }

        for (Cell cell : headerRow) {
            String cellValue = dataFormatter.formatCellValue(cell, formulaEvaluator);
            if (cellValue.equalsIgnoreCase(columnName)) {
                return cell.getColumnIndex();
            }
        }

        throw new IllegalArgumentException("Column not found: " + columnName);
    }
}

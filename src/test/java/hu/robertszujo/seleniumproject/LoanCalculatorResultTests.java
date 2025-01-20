package hu.robertszujo.seleniumproject;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.constants.TestConstants;
import hu.robertszujo.seleniumproject.constants.TestContextConstants;
import hu.robertszujo.seleniumproject.pages.LoanCalculatorPage;
import hu.robertszujo.seleniumproject.pages.components.CalculationResultTable;
import hu.robertszujo.seleniumproject.pages.components.CookiePopup;
import hu.robertszujo.seleniumproject.utils.CsvDataReader;
import org.assertj.core.api.Assertions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Test cases for verifying loan calculator results.
 * This class tests various scenarios related to the loan calculator.
 */

public class LoanCalculatorResultTests extends BaseTestClass{

    private ExtentTest reporter;

    // Page objects
    private LoanCalculatorPage loanCalculatorPage;
    private CookiePopup cookiePopup;
    private CalculationResultTable calculationResultTable;



    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, ITestResult result) {
        reporter = SuiteWideStorage.testReport.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        context.setAttribute(TestContextConstants.REPORTER, reporter);
        initializePageObjects();
    }

    public void initializePageObjects() {
        loanCalculatorPage = new LoanCalculatorPage(driver, reporter);
        cookiePopup = new CookiePopup(driver, reporter);
        calculationResultTable = new CalculationResultTable(driver,reporter);
    }

    @Test(description = "The maximum loan amount should increase when the property value increases.")
    public void testMaximumLoanIncreasesWithPropertyValue() throws IOException {
        String sTCID = "e06";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String testData_price2 = "";
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        String sMaxLoan = "";
        int iMaxLoan =0;
        String sMaxLoan2 = "";
        int iMaxLoan2 =0;
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        // Set income and existing loans to values that do not limit the maximum loan
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();

        sMaxLoan = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        testData_price2 = multiplyStringInteger(testData_price,2);
        driver.navigate().refresh();

        //When
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price2);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();
        sMaxLoan2 = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        iMaxLoan = Integer.parseInt(sMaxLoan);
        iMaxLoan2 = Integer.parseInt(sMaxLoan2);

        //Then
        Assertions.assertThat(iMaxLoan<iMaxLoan2)
                .as( "The maximum loan amount should increase when the property value increases.")
                .isTrue();

        reporter.pass("The maximum loan amount increases as the value of the property increases. First price of property: " +testData_price+"; First loan amount: " + sMaxLoan+"; Second price of property: " +testData_price2+"; First loan amount: "+sMaxLoan2);
    }


    @Test(description = "The maximum loan amount should increase when the income increases.")
    public void testMaximumLoanIncreasesWithIncome() throws IOException {
        String sTCID = "e07";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        String testData_income2 = "";
        String sMaxLoan = "";
        int iMaxLoan =0;
        String sMaxLoan2 = "";
        int iMaxLoan2 =0;
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        // Set income and existing loans to values that do not limit the maximum loan
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();

        sMaxLoan = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        testData_income2 = multiplyStringInteger(testData_income,2);
        driver.navigate().refresh();

        //When
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income2);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getCalculationBtn().click();
        sMaxLoan2 = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        iMaxLoan = Integer.parseInt(sMaxLoan);
        iMaxLoan2 = Integer.parseInt(sMaxLoan2);

        //Then
        Assertions.assertThat(iMaxLoan<iMaxLoan2)
                .as( "The maximum loan amount should increase when the income value increases.")
                .isTrue();

        reporter.pass("The maximum loan amount increases as the value of the income increases. First income value: " +testData_income+"; First loan amount: " + sMaxLoan+"; Second income value: " +testData_income2+"; First loan amount: "+sMaxLoan2);
    }

    @Test(description = "The maximum loan amount should decrease as the existing loan amount increases.")
    public void testMaximumLoanDecreasesWithExistingLoan() throws IOException {
        String sTCID = "e08";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        String sColumn_currentLoan = "meglevo_torleszto";
        String testData_currentLoan = CsvDataReader.getTestData(sTCID, sColumn_currentLoan);
        String testData_currentLoan0 = "0";
        String sMaxLoan = "";
        int iMaxLoan =0;
        String sMaxLoan2 = "";
        int iMaxLoan2 =0;
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        // Set income and existing loans to values that do not limit the maximum loan
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCurrentLoanInput().sendKeys(testData_currentLoan0);
        loanCalculatorPage.getCalculationBtn().click();

        sMaxLoan = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        driver.navigate().refresh();

        //When
        loanCalculatorPage.getCurrentLoanInput().sendKeys(testData_currentLoan);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();
        sMaxLoan2 = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        iMaxLoan = Integer.parseInt(sMaxLoan);
        iMaxLoan2 = Integer.parseInt(sMaxLoan2);

        //Then
        Assertions.assertThat(iMaxLoan>iMaxLoan2)
                .as( "The maximum loan amount should decrease when the current loan value increases.")
                .isTrue();

        reporter.pass("The maximum loan amount decreases as the value of the current loan increases. First current loan value: " +testData_currentLoan0+"; First loan amount: " + sMaxLoan+"; Second current loan value: " +testData_currentLoan+"; First loan amount: "+sMaxLoan2);
    }

    @Test(description = "The maximum loan amount should decrease as the credit card limit increases.")
    public void testMaximumLoanDecreasesWithCreditCardLimit() throws IOException {
        String sTCID = "e09";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        String sColumn_currentLoan = "meglevo_torleszto";
        String testData_currentLoan = CsvDataReader.getTestData(sTCID, sColumn_currentLoan);
        String sColumn_creditCardLimit = "folyoszamla";
        String testData_creditCardLimit = CsvDataReader.getTestData(sTCID, sColumn_creditCardLimit);
        String testData_creditCardLimit0 = "0";
        String sMaxLoan = "";
        int iMaxLoan =0;
        String sMaxLoan2 = "";
        int iMaxLoan2 =0;
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        // Set income and existing loans to values that do not limit the maximum loan
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCurrentLoanInput().sendKeys(testData_currentLoan);
        loanCalculatorPage.getAccountLimitInput().sendKeys(testData_creditCardLimit0);
        loanCalculatorPage.getCalculationBtn().click();

        sMaxLoan = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        driver.navigate().refresh();

        //When
        loanCalculatorPage.getCurrentLoanInput().sendKeys(testData_currentLoan);
        loanCalculatorPage.getAccountLimitInput().sendKeys(testData_creditCardLimit);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();
        sMaxLoan2 = calculationResultTable.getMaxLoanAmount().getText().replaceAll("\\D", "");
        iMaxLoan = Integer.parseInt(sMaxLoan);
        iMaxLoan2 = Integer.parseInt(sMaxLoan2);

        //Then
        Assertions.assertThat(iMaxLoan>iMaxLoan2)
                .as( "The maximum loan amount should decrease when the credit card limit value increases.")
                .isTrue();

        reporter.pass("The maximum loan amount decreases as the value of the credit card limit increases. First credit card limit value: " +testData_creditCardLimit0+"; First loan amount: " + sMaxLoan+"; Second credit card limit value: " +testData_creditCardLimit+"; First loan amount: "+sMaxLoan2);
    }


    @Test(description = "The APR value should decrease when the insurance discount checkbox is checked.")
    public void testAprDecreasesWithInsuranceDiscount() throws IOException {
        String sTCID = "e09";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        float fApr =0;
        float fApr2 =0;
        String sApr = "";
        String sApr2 = "";
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        // Set income and existing loans to values that do not limit the maximum loan
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();

        sApr = calculationResultTable.getApr().getText().replaceAll(",", ".");
        driver.navigate().refresh();

        //When
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getInsuranceDiscountInput().click();
        loanCalculatorPage.getCalculationBtn().click();
        sApr2 = calculationResultTable.getApr().getText().replaceAll(",", ".");
        fApr = Float.valueOf(sApr);
        fApr2 = Float.valueOf(sApr2);

        //Then
        Assertions.assertThat(fApr>fApr2)
                .as( "The APR value should decrease when the insurance discount checkbox is checked.")
                .isTrue();

        reporter.pass("The APR value decreases as the insurance discount checkbox is checked. First APR value: " +sApr+"; Second APR value: " +sApr2);
    }

    // Method to multiply a string-stored integer by a given number of times
    public String multiplyStringInteger(String numberStr, int times) {
        if (times <= 0) {
            throw new IllegalArgumentException("The multiplier must be greater than 0.");
        }
        try {
            int number = Integer.parseInt(numberStr);
            return String.valueOf(number * times);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The input string must be a valid integer.", e);
        }
    }
}

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

public class LoanCalulatorNegativeTests extends BaseTestClass {

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

    @Test(description = "Verifying age error message for age below 18")
    public void belowTheLower_AgeErrorDisappear() throws IOException {
        String sTCID = "e01";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();

        //When
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        //Fill in the other required fields with correct data
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();

        //Then
        Assertions.assertThat(loanCalculatorPage.isAgeErrorDisplayedAfterWaiting())
                .as("An error message was expected when checking the age limit. Test data used:" +testData_age)
                .isTrue();

        Assertions.assertThat(calculationResultTable.isMaxLoanAmountDisplayedAfterWaiting())
                .as("If the age is incorrect, the calculation will not run:" +testData_age)
                .isFalse();

        reporter.pass("The maximum loan amount is not displayed because the age is not correct. Error message: " +loanCalculatorPage.getAgeError()+"; Test data: " + testData_age);
    }


    @Test(description = "Verifying age error message for age above 65")
    public void upperBound_AgeErrorDisappear() throws IOException {
        String sTCID = "e02";
        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);

        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();

        //When
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        //Fill in the other required fields with correct data
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        //loanCalculatorPage.getCalculationBtn().click();

        //Then
        Assertions.assertThat(loanCalculatorPage.isAgeErrorDisplayedAfterWaiting())
                .as("An error message was expected when checking the age limit. Test data used:" +testData_age)
                .isTrue();
        Assertions.assertThat(loanCalculatorPage.isCalculatorBtnDisplayedAfterWaiting())
                .as("If the age is incorrect, the calculator button will not exist:" +testData_age)
                .isFalse();
        Assertions.assertThat(calculationResultTable.isMaxLoanAmountDisplayedAfterWaiting())
                .as("If the age is incorrect, the calculation will not run:" +testData_age)
                .isFalse();
        reporter.pass("The error message appeared when checking the age limit: " +loanCalculatorPage.getAgeError()+";  Test data: " + testData_age);
    }


    @Test(description = "Verifying property price error message for value below 5,000,000")
    public void belowTheLower_PriceErrorDisappear() throws IOException {
        String sTCID = "e03";

        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);

        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();

        //When
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        //Fill in the other required fields with correct data
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        loanCalculatorPage.getCalculationBtn().click();

        //Then
        Assertions.assertThat(loanCalculatorPage.isPriceOfPropertyErrorDisplayedAfterWaiting())
                .as("An error message was expected when checking the price limit. Test data used:" +testData_price)
                .isTrue();
        Assertions.assertThat(calculationResultTable.isMaxLoanAmountDisplayedAfterWaiting())
                .as("If the price is incorrect, the calculation will not run:" +testData_price)
                .isFalse();
        reporter.pass("The error message appeared when checking the price limit: " +loanCalculatorPage.getPriceOfPropertyErrorTxt()+";  Test data: " + testData_price);
    }


    @Test(description = "Verifying income error message for single person with income below 175,000")
    public void belowTheLower_IncomeAloneErrorDisappear() throws IOException {
        String sTCID = "e04";

        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);

        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        loanCalculatorPage.getAloneLabel().click();

        //When
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        //Fill in the other required fields with correct data
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        //loanCalculatorPage.getCalculationBtn().click();

        //Then
        Assertions.assertThat(loanCalculatorPage.isIncomeErrorDisplayedAfterWaiting())
                .as("An error message was expected when checking the income limit. Test data used:" +testData_income)
                .isTrue();
        Assertions.assertThat(loanCalculatorPage.isCalculatorBtnDisplayedAfterWaiting())
                .as("If the age is incorrect, the calculator button will not exist:" +testData_income)
                .isFalse();
        Assertions.assertThat(calculationResultTable.isMaxLoanAmountDisplayedAfterWaiting())
                .as("If the income amount is incorrect, the calculation will not run:" +testData_income)
                .isFalse();
        reporter.pass("The error message appeared when checking the income limit: " +loanCalculatorPage.getIncomeError()+";  Test data: " + testData_income);
    }


    @Test(description = "Verifying income error message for multiple people with income below 260,000")
    public void belowTheLower_IncomeMultipleErrorDisappear() throws IOException {
        String sTCID = "e05";

        String sColumn_age = "eletkor";
        String testData_age = CsvDataReader.getTestData(sTCID, sColumn_age);
        String sColumn_price = "ingatlan_erteke";
        String testData_price = CsvDataReader.getTestData(sTCID, sColumn_price);
        String sColumn_income = "jovedelem";
        String testData_income = CsvDataReader.getTestData(sTCID, sColumn_income);

        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        loanCalculatorPage.getMultiplLabel().click();

        //When
        loanCalculatorPage.getIncomeInput().sendKeys(testData_income);
        //Fill in the other required fields with correct data
        loanCalculatorPage.getPriceOfPropertyInput().sendKeys(testData_price);
        loanCalculatorPage.getAgeInput().sendKeys(testData_age);
        //loanCalculatorPage.getCalculationBtn().click();

        //Then
        Assertions.assertThat(loanCalculatorPage.isIncomeErrorDisplayedAfterWaiting())
                .as("An error message was expected when checking the income limit. Test data used:" +testData_income)
                .isTrue();
        Assertions.assertThat(loanCalculatorPage.isCalculatorBtnDisplayedAfterWaiting())
                .as("If the age is incorrect, the calculator button will not exist:" +testData_income)
                .isFalse();
        Assertions.assertThat(calculationResultTable.isMaxLoanAmountDisplayedAfterWaiting())
                .as("If the income amount is incorrect, the calculation will not run:" +testData_income)
                .isFalse();
        reporter.pass("The error message appeared when checking the income limit: " +loanCalculatorPage.getIncomeError()+";  Test data: " + testData_income);
    }

}

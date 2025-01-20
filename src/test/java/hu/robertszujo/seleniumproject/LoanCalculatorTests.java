package hu.robertszujo.seleniumproject;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.constants.TestConstants;
import hu.robertszujo.seleniumproject.constants.TestContextConstants;
import hu.robertszujo.seleniumproject.pages.LoanCalculatorPage;
import hu.robertszujo.seleniumproject.pages.components.CookiePopup;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoanCalculatorTests extends BaseTestClass {

    private ExtentTest reporter;

    // Page objects
    private LoanCalculatorPage loanCalculatorPage;
    private CookiePopup cookiePopup;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, ITestResult result) {
        reporter = SuiteWideStorage.testReport.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        context.setAttribute(TestContextConstants.REPORTER, reporter);
        initializePageObjects();
    }

    public void initializePageObjects() {
        loanCalculatorPage = new LoanCalculatorPage(driver, reporter);
        cookiePopup = new CookiePopup(driver, reporter);
    }

    // *** Tests ***

    @Test(description = "Cookie popup should be displayed after page load")
    public void loadCalculatorPage_cookiePopupShouldBeDisplayed() {
        // Given + When
        driver.get(TestConstants.CALCULATOR_PAGE_URL);

        // Then
        Assertions.assertThat(cookiePopup.isCookiePopupDisplayedAfterWaiting())
                .as("Cookie popup should have displayed after page load")
                .isTrue();
        reporter.pass("Cookie popup was displayed successfully");
    }

    @Test(description = "Cookie popup should disappear after accepting cookies")
    public void acceptCookies_CookiePopupShouldDisappear() {
        //Given
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();

        //When
        cookiePopup.clickOnCookieAcceptButton();

        //Then
        Assertions.assertThat(cookiePopup.hasCookiePopupDisappearedAfterWaiting())
                .as("Cookie popup should have disappeared")
                .isTrue();
        reporter.pass("Cookie popup has disappeared successfully");
    }
    
    @Test(description = "Calculator form should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_CalculatorFormShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();
        
        //Then
        loanCalculatorPage.isCalculatorFormDisplayedAfterWaiting();
        reporter.pass("Loan calculator page was displayed successfully");
    }
    @Test(description = "Price of property input should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_PriceOfPropertyInputShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isPriceOfPropertyInputDisplayedAfterWaiting());
        reporter.pass("Price of property input was displayed successfully");
    }

    @Test(description = "Age input should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_AgeInputShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isAgeInputDisplayedAfterWaiting());
        reporter.pass("Age input was displayed successfully");
    }

    @Test(description = "Alone label should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_AloneLabelShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isAloneLabelDisplayedAfterWaiting());
        reporter.pass("Alone label was displayed successfully");
    }

    @Test(description = "Multiple label should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_MultiplLabelShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isMultiplLabelDisplayedAfterWaiting());
        reporter.pass("Multiple label was displayed successfully");
    }

    @Test(description = "Income input should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_IncomeInputShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isIncomeInputDisplayedAfterWaiting());
        reporter.pass("Income input was displayed successfully");
    }

    @Test(description = "Existing installment input should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_ExInstallmentInputShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isCurrentLoanInputDisplayedAfterWaiting());
        reporter.pass("Existing installment input was displayed successfully");
    }

    @Test(description = "Account limit input should be displayed after page load & accepting cookies")
    public void loadPageAndAcceptCookies_AccountLimitInputShouldBeDisplayed() {
        //Given + When
        loadPageAndAcceptCookies();

        //Then
        Assert.assertTrue(loanCalculatorPage.isAccountLimitInputDisplayedAfterWaiting());
        reporter.pass("Account limit input was displayed successfully");
    }


    // *** Helper methods ***
    
    private void loadPageAndAcceptCookies() {
        driver.get(TestConstants.CALCULATOR_PAGE_URL);
        cookiePopup.waitForCookiePopupToBeDisplayed();
        cookiePopup.clickOnCookieAcceptButton();
        cookiePopup.waitForCookiePopupToDisappear();
    }
}

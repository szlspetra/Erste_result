package hu.robertszujo.seleniumproject.pages;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.utils.ElementActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanCalculatorPage extends BasePageObject {

    public LoanCalculatorPage(WebDriver driver, ExtentTest reporter) {
        super(driver, reporter);
    }

    // *** Elements ***

    @FindBy(css = "div[class='content_hitelmaximum']")
    private WebElement calculatorForm;

    @FindBy(xpath = "//input[@id='ingatlan_erteke']")
    private WebElement priceOfProperty_input;

    @FindBy(xpath = "//input[@id='meletkor']")
    private WebElement age_input;

    @FindBy(xpath = "//label[@for='egyedul']")
    private WebElement alone_label;

    @FindBy(xpath = "//label[@for='tobben']")
    private WebElement multipl_label;

    @FindBy(xpath = "//input[@id='mjovedelem']")
    private WebElement income_input;

    @FindBy(xpath = "//input[@id='meglevo_torleszto']")
    private WebElement exInstallment_input;

    @FindBy(xpath = "//input[@id='folyoszamla']")
    private WebElement accountLimit_input;

    @FindBy(xpath = "//label[@for='kedvezmeny_jovairasm']")
    private WebElement ersteDiscount_label;

    @FindBy(xpath = "//input[@id='jovairasm']")
    private WebElement monthlyCredit_input;

    @FindBy(xpath = "//input[@id='kedvezmeny_babavarom']")
    private WebElement babyDiscount_input;

    @FindBy(xpath = "//input[@id='kedvezmeny_biztositasm']")
    private WebElement insuranceDiscount_input;


    // *** Element methods ***

    public void waitForCalculatorFormToBeDisplayed() {
        reporter.info("Waiting for calculator form to be displayed");
        ElementActions.waitForElementToBeDisplayed(calculatorForm, driver);
    }
    public void waitForPriceOfPropertyInputToBeDisplayed() {
        reporter.info("Waiting for price of property input to be displayed");
        ElementActions.waitForElementToBeDisplayed(priceOfProperty_input, driver);
    }

    public void waitForAgeInputToBeDisplayed() {
        reporter.info("Waiting for age input to be displayed");
        ElementActions.waitForElementToBeDisplayed(age_input, driver);
    }

    public void waitForAloneLabelToBeDisplayed() {
        reporter.info("Waiting for alone label to be displayed");
        ElementActions.waitForElementToBeDisplayed(alone_label, driver);
    }

    public void waitForMultiplLabelToBeDisplayed() {
        reporter.info("Waiting for multiple label to be displayed");
        ElementActions.waitForElementToBeDisplayed(multipl_label, driver);
    }

    public void waitForIncomeInputToBeDisplayed() {
        reporter.info("Waiting for income input to be displayed");
        ElementActions.waitForElementToBeDisplayed(income_input, driver);
    }

    public void waitForExInstallmentInputToBeDisplayed() {
        reporter.info("Waiting for existing installment input to be displayed");
        ElementActions.waitForElementToBeDisplayed(exInstallment_input, driver);
    }

    public void waitForAccountLimitInputToBeDisplayed() {
        reporter.info("Waiting for account limit input to be displayed");
        ElementActions.waitForElementToBeDisplayed(accountLimit_input, driver);
    }

    public void waitForErsteDiscountLabelToBeDisplayed() {
        reporter.info("Waiting for Erste discount label to be displayed");
        ElementActions.waitForElementToBeDisplayed(ersteDiscount_label, driver);
    }

    public void waitForMonthlyCreditInputToBeDisplayed() {
        reporter.info("Waiting for monthly credit input to be displayed");
        ElementActions.waitForElementToBeDisplayed(monthlyCredit_input, driver);
    }

    public void waitForBabyDiscountInputToBeDisplayed() {
        reporter.info("Waiting for baby discount input to be displayed");
        ElementActions.waitForElementToBeDisplayed(babyDiscount_input, driver);
    }

    public void waitForInsuranceDiscountInputToBeDisplayed() {
        reporter.info("Waiting for insurance discount input to be displayed");
        ElementActions.waitForElementToBeDisplayed(insuranceDiscount_input, driver);
    }


    public boolean isCalculatorFormDisplayedAfterWaiting() {
        try {
            waitForCalculatorFormToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean isPriceOfPropertyInputDisplayedAfterWaiting() {
        try {
            waitForPriceOfPropertyInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAgeInputDisplayedAfterWaiting() {
        try {
            waitForAgeInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAloneLabelDisplayedAfterWaiting() {
        try {
            waitForAloneLabelToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isMultiplLabelDisplayedAfterWaiting() {
        try {
            waitForMultiplLabelToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isIncomeInputDisplayedAfterWaiting() {
        try {
            waitForIncomeInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isExInstallmentInputDisplayedAfterWaiting() {
        try {
            waitForExInstallmentInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAccountLimitInputDisplayedAfterWaiting() {
        try {
            waitForAccountLimitInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErsteDiscountLabelDisplayedAfterWaiting() {
        try {
            waitForErsteDiscountLabelToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isMonthlyCreditInputDisplayedAfterWaiting() {
        try {
            waitForMonthlyCreditInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isBabyDiscountInputDisplayedAfterWaiting() {
        try {
            waitForBabyDiscountInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isInsuranceDiscountInputDisplayedAfterWaiting() {
        try {
            waitForInsuranceDiscountInputToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


}

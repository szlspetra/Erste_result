package hu.robertszujo.seleniumproject.pages;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanCalculatorPage extends BasePageObject {

    public LoanCalculatorPage(WebDriver driver, ExtentTest reporter) {
        super(driver, reporter);
    }

    // *** Elements ***
    // **** Input objects ****

    @FindBy(css = "div[class='content_hitelmaximum']")
    private WebElement calculatorForm;

    @FindBy(xpath = "//input[@id='ingatlan_erteke']")
    private WebElement priceOfPropertyInput;

    @FindBy(xpath = "//input[@id='meletkor']")
    private WebElement ageInput;

    @FindBy(xpath = "//label[@for='egyedul']")
    private WebElement aloneLabel;

    @FindBy(xpath = "//label[@for='tobben']")
    private WebElement multiplLabel;

    @FindBy(xpath = "//input[@id='mjovedelem']")
    private WebElement incomeInput;

    @FindBy(xpath = "//input[@id='meglevo_torleszto']")
    private WebElement currentLoanInput;

    @FindBy(xpath = "//input[@id='folyoszamla']")
    private WebElement accountLimitInput;

    @FindBy(xpath = "//label[@for='kedvezmeny_jovairasm']")
    private WebElement ersteDiscountLabel;

    @FindBy(xpath = "//input[@id='jovairasm']")
    private WebElement monthlyCreditInput;

    @FindBy(xpath = "//input[@id='kedvezmeny_babavarom']")
    private WebElement babyDiscountInput;

    @FindBy(xpath = "//input[@id='kedvezmeny_biztositasm']")
    private WebElement insuranceDiscountInput;

    // **** Error objects ****

    @FindBy(xpath = "//div[@id='ingatlan_erteke_error']")
    private WebElement priceOfPropertyError;

    @FindBy(xpath = "//div[@id='eletkor_error']")
    private WebElement ageError;

    @FindBy(xpath = "//div[@id='mjovedelem_error']")
    private WebElement incomeError;

    @FindBy(xpath = "//div[@id='mennyit_button']//input[@type='button']")
    private WebElement calculationBtn;




    // *** Element methods ***
    // **** Wait for element to be displayed ***

    public void waitForCalculatorFormToBeDisplayed() {
        reporter.info("Waiting for calculator form to be displayed");
        ElementActions.waitForElementToBeDisplayed(calculatorForm, driver);
    }
    public void waitForPriceOfPropertyInputToBeDisplayed() {
        reporter.info("Waiting for price of property input to be displayed");
        ElementActions.waitForElementToBeDisplayed(priceOfPropertyInput, driver);
    }

    public void waitForAgeInputToBeDisplayed() {
        reporter.info("Waiting for age input to be displayed");
        ElementActions.waitForElementToBeDisplayed(ageInput, driver);
    }

    public void waitForAloneLabelToBeDisplayed() {
        reporter.info("Waiting for alone label to be displayed");
        ElementActions.waitForElementToBeDisplayed(aloneLabel, driver);
    }

    public void waitForMultiplLabelToBeDisplayed() {
        reporter.info("Waiting for multiple label to be displayed");
        ElementActions.waitForElementToBeDisplayed(multiplLabel, driver);
    }

    public void waitForIncomeInputToBeDisplayed() {
        reporter.info("Waiting for income input to be displayed");
        ElementActions.waitForElementToBeDisplayed(incomeInput, driver);
    }

    public void waitCurrentLoanInputToBeDisplayed() {
        reporter.info("Waiting for currentloan input to be displayed");
        ElementActions.waitForElementToBeDisplayed(currentLoanInput, driver);
    }

    public void waitForAccountLimitInputToBeDisplayed() {
        reporter.info("Waiting for account limit input to be displayed");
        ElementActions.waitForElementToBeDisplayed(accountLimitInput, driver);
    }

    public void waitForErsteDiscountLabelToBeDisplayed() {
        reporter.info("Waiting for Erste discount label to be displayed");
        ElementActions.waitForElementToBeDisplayed(ersteDiscountLabel, driver);
    }

    public void waitForMonthlyCreditInputToBeDisplayed() {
        reporter.info("Waiting for monthly credit input to be displayed");
        ElementActions.waitForElementToBeDisplayed(monthlyCreditInput, driver);
    }

    public void waitForBabyDiscountInputToBeDisplayed() {
        reporter.info("Waiting for baby discount input to be displayed");
        ElementActions.waitForElementToBeDisplayed(babyDiscountInput, driver);
    }

    public void waitForInsuranceDiscountInputToBeDisplayed() {
        reporter.info("Waiting for insurance discount input to be displayed");
        ElementActions.waitForElementToBeDisplayed(insuranceDiscountInput, driver);
    }


    /**
     * Waits for the price of property error element to be displayed.
     */
    public void waitForPriceOfPropertyErrorToBeDisplayed() {
        reporter.info("Waiting for price of property error to be displayed");
        ElementActions.waitForElementToBeDisplayed(priceOfPropertyError, driver);
    }

    /**
     * Waits for the age error element to be displayed.
     */
    public void waitForAgeErrorToBeDisplayed() {
        reporter.info("Waiting for age error to be displayed");
        ElementActions.waitForElementToBeDisplayed(ageError, driver);
    }

    /**
     * Waits for the income error element to be displayed.
     */
    public void waitForIncomeErrorToBeDisplayed() {
        reporter.info("Waiting for income error to be displayed");
        ElementActions.waitForElementToBeDisplayed(incomeError, driver);
    }

    /**
     * Waits for the calculation button element to be displayed.
     */
    public void waitForcalCulationBtnToBeDisplayed() {
        reporter.info("Waiting for calculation button to be displayed");
        ElementActions.waitForElementToBeDisplayed(calculationBtn, driver);
    }

    // **** Check the element to be displayed ***

    /**
     * Checks if the price of property error is displayed after waiting.
     * @return true if the error is displayed, false otherwise
     */
    public boolean isPriceOfPropertyErrorDisplayedAfterWaiting() {
        try {
            waitForPriceOfPropertyErrorToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the age error is displayed after waiting.
     * @return true if the error is displayed, false otherwise
     */
    public boolean isAgeErrorDisplayedAfterWaiting() {
        try {
            waitForAgeErrorToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if the income error is displayed after waiting.
     * @return true if the error is displayed, false otherwise
     */
    public boolean isIncomeErrorDisplayedAfterWaiting() {
        try {
            waitForIncomeErrorToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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

    public boolean isCurrentLoanInputDisplayedAfterWaiting() {
        try {
            waitCurrentLoanInputToBeDisplayed();
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

    public boolean isCalculatorBtnDisplayedAfterWaiting() {
        try {
            waitForcalCulationBtnToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // **** Get elements ***

    /**
     * Gets the calculator form element after ensuring it's displayed.
     * @return WebElement representing the calculator form
     */
    public WebElement getCalculatorForm() {
        isCalculatorBtnDisplayedAfterWaiting();
        return calculatorForm;
    }

    /**
     * Gets the price of property input element after ensuring it's displayed.
     * @return WebElement representing the price of property input
     */
    public WebElement getPriceOfPropertyInput() {
        isPriceOfPropertyInputDisplayedAfterWaiting();
        return priceOfPropertyInput;
    }

    /**
     * Gets the age input element after ensuring it's displayed.
     * @return WebElement representing the age input
     */
    public WebElement getAgeInput() {
        isAgeInputDisplayedAfterWaiting();
        return ageInput;
    }

    /**
     * Gets the alone label element after ensuring it's displayed.
     * @return WebElement representing the alone label
     */
    public WebElement getAloneLabel() {
        isAloneLabelDisplayedAfterWaiting();
        return aloneLabel;
    }

    /**
     * Gets the multiple label element after ensuring it's displayed.
     * @return WebElement representing the multiple label
     */
    public WebElement getMultiplLabel() {
        isMultiplLabelDisplayedAfterWaiting();
        return multiplLabel;
    }

    /**
     * Gets the income input element after ensuring it's displayed.
     * @return WebElement representing the income input
     */
    public WebElement getIncomeInput() {
        isIncomeInputDisplayedAfterWaiting();
        return incomeInput;
    }

    /**
     * Gets the existing installment input element after ensuring it's displayed.
     * @return WebElement representing the existing installment input
     */
    public WebElement getCurrentLoanInput() {
        isCurrentLoanInputDisplayedAfterWaiting();
        return currentLoanInput;
    }

    /**
     * Gets the account limit input element after ensuring it's displayed.
     * @return WebElement representing the account limit input
     */
    public WebElement getAccountLimitInput() {
        isAccountLimitInputDisplayedAfterWaiting();
        return accountLimitInput;
    }

    /**
     * Gets the Erste discount label element after ensuring it's displayed.
     * @return WebElement representing the Erste discount label
     */
    public WebElement getErsteDiscountLabel() {
        isErsteDiscountLabelDisplayedAfterWaiting();
        return ersteDiscountLabel;
    }

    /**
     * Gets the monthly credit input element after ensuring it's displayed.
     * @return WebElement representing the monthly credit input
     */
    public WebElement getMonthlyCreditInput() {
        isMonthlyCreditInputDisplayedAfterWaiting();
        return monthlyCreditInput;
    }

    /**
     * Gets the baby discount input element after ensuring it's displayed.
     * @return WebElement representing the baby discount input
     */
    public WebElement getBabyDiscountInput() {
        isBabyDiscountInputDisplayedAfterWaiting();
        return babyDiscountInput.findElement(By.xpath("./.."));
    }

    /**
     * Gets the insurance discount input element after ensuring it's displayed.
     * @return WebElement representing the insurance discount input
     */
    public WebElement getInsuranceDiscountInput() {
        isInsuranceDiscountInputDisplayedAfterWaiting();
        return insuranceDiscountInput.findElement(By.xpath("./.."));
    }

    /**
     * Gets the price of property error element.
     * Note: Consider implementing a wait method for this error element.
     * @return String representing the price of property error
     */
    public String getPriceOfPropertyErrorTxt() {
        isPriceOfPropertyErrorDisplayedAfterWaiting();
        return priceOfPropertyError.getText();
    }

    /**
     * Gets the age error element.
     * Note: Consider implementing a wait method for this error element.
     * @return String representing the age error
     */
    public String getAgeError() {
        isAgeErrorDisplayedAfterWaiting();
        return ageError.getText();
    }

    /**
     * Gets the income error element.
     * Note: Consider implementing a wait method for this error element.
     * @return String representing the income error
     */
    public String getIncomeError() {
        isIncomeErrorDisplayedAfterWaiting();
        return incomeError.getText();
    }

    /**
     * Gets the income error element.
     * Note: Consider implementing a wait method for this error element.
     * @return String representing the income error
     */
    public WebElement getCalculationBtn() {
        isCalculatorBtnDisplayedAfterWaiting();
        return calculationBtn;
    }
}

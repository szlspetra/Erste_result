package hu.robertszujo.seleniumproject.pages.components;

import com.aventstack.extentreports.ExtentTest;
import hu.robertszujo.seleniumproject.pages.BasePageObject;
import hu.robertszujo.seleniumproject.utils.ElementActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculationResultTable extends BasePageObject {

    public CalculationResultTable(WebDriver driver, ExtentTest reporter) {
        super(driver, reporter);
    }

    @FindBy(xpath = "//span[@id='box_1_max_desktop']")
    private WebElement maxLoanAmount;

    @FindBy(xpath = "//span[@id='box_1_thm']")
    private WebElement apr;

    public void waitForMaxLoanAmountToBeDisplayed() {
        reporter.info("Waiting for max loan amount to be displayed");
        ElementActions.waitForElementToBeDisplayed(maxLoanAmount, driver);
    }

    public void waitForAprToBeDisplayed() {
        reporter.info("Waiting for APR to be displayed");
        ElementActions.waitForElementToBeDisplayed(apr, driver);
    }

    public boolean isMaxLoanAmountDisplayedAfterWaiting() {
        try {
            waitForMaxLoanAmountToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public boolean isThmDisplayedAfterWaiting() {
        try {
            waitForAprToBeDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public WebElement getMaxLoanAmount() {
        waitForMaxLoanAmountToBeDisplayed();
        return maxLoanAmount;
    }

    public WebElement getApr() {
        waitForAprToBeDisplayed();
        return apr;
    }
}

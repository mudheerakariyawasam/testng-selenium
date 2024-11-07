package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(css = "button[data-auto-id='customer-info-button']")
    WebElement loginButton;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "two-step-form-button")
    WebElement continueButton;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-submit-button")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void clickContinueButton() {
        continueButton.click();
        waitUntilVisible(passwordInput);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void login(String email, String password) {
        clickLoginButtonWithJS();
        enterEmail(email);
        clickContinueButton();
        enterPassword(password);
        clickSignInButton();
    }

    private void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickLoginButtonWithJS() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        waitFor2Seconds();
    }

}

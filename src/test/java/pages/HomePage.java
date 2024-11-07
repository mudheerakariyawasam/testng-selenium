package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(css = "input[data-auto-id='searchinput-desktop']")
    WebElement searchBox;

    @FindBy(xpath = "//button[@class='_user_icon_xw3y3_1']")
    WebElement loginButton;

    // Cart button
    @FindBy(css = "a[data-auto-id='cart-button']")
    private WebElement cartButton;

    // Best Sellers button
    @FindBy(css = "button[data-testid='tab-button-2']")
    private WebElement bestSellersButton;

    // Wishlist button
    @FindBy(css = "button[data-auto-id='wishlist-active-button']")
    private WebElement wishlistButton;

    // Get Started button
    @FindBy(css = "a[data-auto-id='glass-empty-cart-continue-shopping']")
    private WebElement getStartedButton;

    public HomePage clickGetStarted() {
        getStartedButton.click();
        return this;
    }

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        loadURL("https://www.adidas.com/us");
    }

    public SearchResultsPage searchForProduct(String productName) {
        searchBox.sendKeys(productName);
        searchBox.sendKeys(Keys.RETURN);
        return new SearchResultsPage(driver);
    }

    public LoginPage goToLogin() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public CartPage clickCart() {
        cartButton.click();
        return PageFactory.initElements(driver, CartPage.class);
    }

    public void scrollPageDown() {
        scrollPage(driver, 0, 500);  // Example scroll down, can adjust the value based on requirement
    }

    public HomePage clickBestSellers() {
        bestSellersButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

}

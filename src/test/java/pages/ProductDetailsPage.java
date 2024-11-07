package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductDetailsPage extends BasePage {

    @FindBy(css = "div.color-chooser-grid___1ZBx_")
    WebElement colorChooserGrid;

    @FindBy(xpath = "//button[contains(@class, 'gl-label') and contains(@class, 'size___2lbev')]/span[text()='11']")
    WebElement size11;

    @FindBy(css = "button.gl-cta.gl-cta--primary.gl-cta--full-width[data-auto-id='add-to-bag']")
    WebElement addToBagButton;

    @FindBy(id = "gl-modal__close-mf-account-portal")
    WebElement closeButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectColorByIndex(int index) {
        List<WebElement> colorOptions = colorChooserGrid.findElements(By.cssSelector("a[data-testid='color-variation-link']"));
        if (index >= 0 && index < colorOptions.size()) {
            colorOptions.get(index).click();
            waitFor2Seconds();
        } else {
            throw new IndexOutOfBoundsException("Invalid index for color selection.");
        }
    }

    public void selectSize() {
        size11.click();
    }

    public void addToBag() {
        addToBagButton.click();
        waitFor2Seconds();
    }

    public void closeModal() {
        closeButton.click();
        waitFor2Seconds();
    }
}

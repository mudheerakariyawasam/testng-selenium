package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Header extends BasePage {

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> headerLinks = driver.findElements(By.cssSelector("ul._flyouts_558sn_37 li a"));

    public void clickHeaderLink(int index) {
        headerLinks.get(index).click();
    }

    public int getHeaderLinksCount() {
        return headerLinks.size();
    }
}

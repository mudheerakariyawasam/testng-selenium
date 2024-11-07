package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage loadURL(String url){
        driver.get(url);
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void scrollPage(WebDriver driver, int x, int y){
        new Actions(driver).scrollByAmount(x,y).perform();
    }

    public void waitFor2Seconds() {
        try {
            Thread.sleep(2000);  // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

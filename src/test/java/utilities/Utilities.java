package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Utilities {

    protected WebDriver driver;
    protected BrowserFactory browserFactory;

    @BeforeMethod
    public void initializeBrowser(){
        browserFactory = BrowserFactory.getBrowserFactory();
        driver = browserFactory.getDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        browserFactory.removeDriver();
    }

}

package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.Utilities;


public class LoginTests extends Utilities {
    @Test
    public void login() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("mudheera@gmail.com", "Ucsc@20020554");
    }
}

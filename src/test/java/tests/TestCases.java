package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utilities.ExcelUtil;
import utilities.Utilities;

import java.io.IOException;
import java.util.List;


public class TestCases extends Utilities {

    @Test
    public void testSearchProducts() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        // Search for "running shoes"
        SearchResultsPage resultsPage = homePage.searchForProduct("running shoes");

        // Apply filters
        resultsPage.openFilterOptions();
        resultsPage.selectPriceLowToHigh();
        resultsPage.selectGenderMen();
        resultsPage.applyFilters();

        // Assert that the filters are applied correctly
        Assert.assertTrue(resultsPage.isFilterApplied("Men"));

        // Scroll down to load more results
        resultsPage.scrollDownResultsPage();

        // Click on the first product in the results
        ProductDetailsPage productDetailsPage = resultsPage.clickOnProductByIndex(3);

        // Select color and size on the product details page
        productDetailsPage.selectColorByIndex(1);
        productDetailsPage.waitFor2Seconds();
        productDetailsPage.closeModal();
        productDetailsPage.selectSize();

        // Add the product to the cart
        productDetailsPage.scrollPage(driver,0,200);
    }


    @Test
    public void testDifferentFilters() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        // Search for "bags"
        SearchResultsPage resultsPage = homePage.searchForProduct("bags");

        // Apply various filters
        resultsPage.openFilterOptions();
        resultsPage.selectTopSellersFilter();
        resultsPage.selectSportFilter();
        resultsPage.selectHikingCheckbox();
        resultsPage.applyFilters();

        resultsPage.scrollDownResultsPage();

        // Click on the first product after applying filters
        ProductDetailsPage productDetailsPage = resultsPage.clickOnProductByIndex(0);
        productDetailsPage.scrollPage(driver,0,200);
        productDetailsPage.addToBag();
    }


    @Test
    public void testHyperlinks() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        // Search for "slides"
        SearchResultsPage resultsPage = homePage.searchForProduct("slides");

        // Click on the "Week of Deals" link
        resultsPage.clickHomeLink();

        // Verify navigation to "Week of Deals" section
        Assert.assertTrue(resultsPage.isOnHomePage(), "Failed to navigate to the Home page after clicking the link.");
        resultsPage.scrollDownResultsPage();
    }

    @Test
    public void testHeaderLinks() {
        // Step 1: Load the home page
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();  // Assuming you have this method in your HomePage class

        // Step 2: Create a HeaderPage instance
        Header headerPage = new Header(driver);

        // Step 3: Get the count of header links
        int headerLinksCount = headerPage.getHeaderLinksCount();

        // Step 4: Loop through all header links, click each one, wait, and go back to the homepage
        for (int i = 0; i < headerLinksCount; i++) {
            headerPage.clickHeaderLink(i);
            headerPage.waitFor2Seconds();  // Wait for 2 seconds after clicking

            // Check if the page loaded correctly, assuming each header link leads to a different page
            Assert.assertTrue(driver.getCurrentUrl().contains("us"), "URL validation failed after clicking link " + i);

            // Step 5: Go back to the homepage
            homePage.openHomePage();  // Reload the home page
            headerPage.waitFor2Seconds();  // Wait for 2 seconds after going back
        }
    }

    @Test
    public void testNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        homePage.clickCart();
        homePage.waitFor2Seconds();

        homePage.clickGetStarted();
        homePage.waitFor2Seconds();

        homePage.scrollPageDown();

        homePage.clickBestSellers();
        homePage.waitFor2Seconds();
    }

    @DataProvider(name = "searchQueries")
    public Object[][] getSearchQueries() throws IOException {
        List<Object[]> dataList = ExcelUtil.getSearchQueryData();
        return dataList.toArray(new Object[0][0]); // Convert List<Object[]> to Object[][]
    }

    @Test(dataProvider = "searchQueries")
    public void testSearch(String searchQuery) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        // Perform search using the provided search query
        SearchResultsPage resultsPage = homePage.searchForProduct(searchQuery);
    }
}

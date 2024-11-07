package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchResultsPage extends BasePage {

    @FindBy(css = "button[data-testid='open-filter-panel-cta-btn']")
    WebElement filterButton;

    @FindBy(css = "a[data-testid='sorting-option'][title='Price (low - high)']")
    WebElement pricesortlowtohigh;

    @FindBy(css = "a[data-testid='filter-item'][title='Men']")
    WebElement genderMenOption;

    @FindBy(xpath = "//a[@title='Top Sellers']")
    WebElement topSellersFilter;

    @FindBy(xpath = "//summary[contains(text(), 'Sport')]")
    WebElement sportFilterSummary;

    @FindBy(xpath = "//span[text()='Hiking']")
    WebElement hikingCheckbox;

    @FindBy(css = "button.gl-cta.gl-cta--primary.gl-cta--full-width.filter-panel_apply-button__B7AZ0[aria-label='Apply']")
    WebElement applyButton;

    @FindBy(xpath = "//section[@data-testid='product-grid']//article[@data-testid='plp-product-card']")
    List<WebElement> productList;

    @FindBy(xpath = "//a[@itemprop='item' and @href='/us']//span[@itemprop='name' and text()='Home']")
    WebElement homeLink;

    @FindBy(css = "div.applied-filters_applied-filters-row__s5Ysd") // Locator for applied filters section
    WebElement appliedFiltersContainer;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openFilterOptions() {
        filterButton.click();
        waitFor2Seconds();
    }

    public void selectPriceLowToHigh() {
        pricesortlowtohigh.click();
        waitFor2Seconds();
    }

    public void selectGenderMen() {
        genderMenOption.click();
        waitFor2Seconds();
    }

    public void selectTopSellersFilter() {
        topSellersFilter.click();
        waitFor2Seconds();
    }

    public void selectSportFilter() {
        sportFilterSummary.click();
        waitFor2Seconds();
    }

    public void selectHikingCheckbox() {
        hikingCheckbox.click();
        waitFor2Seconds();
    }

    public void applyFilters() {
        applyButton.click();
    }

    public void scrollDownResultsPage() {
        scrollPage(driver, 0, 500);
        waitFor2Seconds();
    }

    public ProductDetailsPage clickOnProductByIndex(int index) {
        if (productList.size() > index) {
            productList.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Product index is out of range");
        }
        return new ProductDetailsPage(driver);
    }

    public void clickHomeLink() {
        homeLink.click();
        waitFor2Seconds();
    }

    public boolean isFilterApplied(String filterName) {
        List<WebElement> appliedFilterValues = appliedFiltersContainer.findElements(By.className("applied-filters_filter-value__DOyt6"));
        for (WebElement filter : appliedFilterValues) {
            if (filter.getText().equals(filterName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOnHomePage() {
        try {
            WebElement logo = driver.findElement(By.cssSelector("a._logo_1lkce_1"));
            return logo.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}

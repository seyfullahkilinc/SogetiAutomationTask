package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomePage extends BasePage {

    private final Actions actions;

    // Globe icon: "Global | EN" link in the header
    private final By globeIconLocator =
            By.xpath("//a[contains(normalize-space(), 'Global')][@href='#']");

    // Used in clickWorldIcon() to wait until dropdown links are visible
    private final By countryLinksVisibilityLocator =
            By.xpath("//a[contains(@href,'sogeti.') and not(contains(@href,'sogeti.com/'))" +
                    " or contains(@href,'fr.sogeti')" +
                    " or contains(@href,'uk.sogeti')" +
                    " or contains(@href,'us.sogeti')]");

    // Scoped to desktop dropdown only — avoids duplicate links from mobile menu
    private final By countryLinksLocator =
            By.cssSelector("div.header-lang-wrapper-desktop ul a[href*='sogeti']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
        handlePopups();
    }

    public void hoverOverElement(String linkText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[normalize-space()='" + linkText + "']" +
                        " | //span[normalize-space()='" + linkText + "']")));
        actions.moveToElement(element).perform();
    }

    public void clickElement(String linkText) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + linkText + "']" +
                        " | //span[normalize-space()='" + linkText + "']")));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isTextVisible(String text) {
        By locator = text.equalsIgnoreCase("Quality Engineering")
                ? By.xpath("//span[contains(@class,'banner-tag') and contains(.,'Quality Engineering')]" +
                " | //h1//span[contains(.,'Quality Engineering')]")
                : By.xpath("//*[contains(text(),'" + text + "')]");

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickWorldIcon() {
        WebElement globeLink = wait.until(ExpectedConditions.elementToBeClickable(globeIconLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", globeLink);

        try {
            actions.moveToElement(globeLink).click().perform();
        } catch (Exception e) {
            // Fallback to JS click if Actions fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", globeLink);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(countryLinksVisibilityLocator));
    }

    public List<WebElement> getCountryLinks() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(countryLinksLocator));
    }
}
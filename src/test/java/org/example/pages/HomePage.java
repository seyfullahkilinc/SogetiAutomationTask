package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private Actions actions;

    public HomePage(WebDriver driver) {
        super(driver); // BasePage constructor'ını çağırır
        this.actions = new Actions(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
        handlePopups(); // İlk girişte temizle
    }

    public void hoverOverElement(String linkText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[normalize-space()='" + linkText + "'] | //span[normalize-space()='" + linkText + "']")));
        actions.moveToElement(element).perform();
    }

    public void clickElement(String linkText) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + linkText + "'] | //span[normalize-space()='" + linkText + "']")));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QualityEngineeringPage extends BasePage {

    private final By bannerTag = By.xpath("//span[@class='banner-tag'][normalize-space()='Quality Engineering']");

    public QualityEngineeringPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMainBannerTagVisible() {
        try {
            handlePopups();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bannerTag)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
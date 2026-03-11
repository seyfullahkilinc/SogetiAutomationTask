package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

    private final By purposeDropdown = By.xpath("(//select)[1]");
    private final By countryDropdown = By.className("form_country_select");
    private final By rangeInputLocator = By.xpath("//input[@type='range'][contains(@id, 'slider-submit')]");
    private final By submitButton = By.xpath("//button[@type='submit']");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void clickContactUs() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'contact')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    public void fillField(String fieldName, String value) {
        String xpath = String.format(
                "//label[contains(text(),'%1$s')]/following-sibling::div//input | " +
                        "//label[contains(text(),'%1$s')]/following-sibling::input | " +
                        "//input[contains(@placeholder,'%1$s')] | " +
                        "//textarea[contains(@placeholder,'%1$s')] | " +
                        "//label[contains(.,'%1$s')]/..//textarea", fieldName);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.clear();
        element.sendKeys(value);
    }

    public void selectPurpose(int index) {
        new Select(wait.until(ExpectedConditions.elementToBeClickable(purposeDropdown))).selectByIndex(index);
    }

    public void selectCountry(int index) {
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(countryDropdown))).selectByIndex(index);
    }

    public void acceptAgreement() {
        By checkboxLocator = By.xpath("//input[@type='checkbox']");
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));

        if (!checkbox.isSelected()) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            } catch (Exception e) {
                System.out.println("Checkbox interaction fallback: " + e.getMessage());
            }
        }
    }

    public void performSlideToSubmit() {
        WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(rangeInputLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", slider);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "var el = arguments[0];" +
                        "var rect = el.getBoundingClientRect();" +
                        "var x = rect.left + rect.width;" +
                        "var y = rect.top + rect.height / 2;" +
                        "el.dispatchEvent(new MouseEvent('mousedown', {bubbles: true, clientX: rect.left, clientY: y}));" +
                        "el.value = 100;" +
                        "el.dispatchEvent(new MouseEvent('mousemove', {bubbles: true, clientX: x, clientY: y}));" +
                        "el.dispatchEvent(new Event('input', {bubbles: true}));" +
                        "el.dispatchEvent(new Event('change', {bubbles: true}));" +
                        "el.dispatchEvent(new MouseEvent('mouseup', {bubbles: true, clientX: x, clientY: y}));";

        js.executeScript(script, slider);

        try {
            Thread.sleep(1500);
            WebElement submitBtn = driver.findElement(submitButton);
            js.executeScript("arguments[0].click();", submitBtn);
        } catch (Exception e) {
            System.out.println("Submission trigger finished.");
        }
    }

    public boolean isThankYouVisible() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Thank you')]")));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.pages.QualityEngineeringPage;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommonStepDefinitions {
    private final HomePage homePage = new HomePage(DriverManager.getDriver());
    private final QualityEngineeringPage qePage = new QualityEngineeringPage(DriverManager.getDriver());

    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {
        homePage.navigateTo(url);
    }

    @And("User handles popups")
    public void userHandlesPopups() {
        homePage.handlePopups();
    }

    @Then("{string} text should be visible on the page")
    public void verifyTextVisibility(String text) {
        Assert.assertTrue(homePage.isTextVisible(text), "Text verification failed for: " + text);
    }

    @When("User clicks on the globe icon in the header")
    public void userClicksOnWorldIcon() {
        homePage.clickWorldIcon();
    }

//    @Then("Assert that all the Country specific Sogeti links are working")
//    public void verifyAllCountryLinks() {
//        List<WebElement> links = homePage.getCountryLinks();
//        SoftAssert softAssert = new SoftAssert();
//
//        System.out.println("=================================================");
//        System.out.println("Total country links found: " + links.size());
//        System.out.println("=================================================");
//
//        for (WebElement link : links) {
//            String url = link.getAttribute("href");
//
//            if (url == null || url.isEmpty() || url.contains("javascript:void(0)")) continue;
//
//            String countryLabel = extractCountryFromUrl(url);
//
//            try {
//                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//                connection.setRequestMethod("HEAD");
//                connection.setConnectTimeout(5000);
//                connection.setInstanceFollowRedirects(true);
//                connection.connect();
//                int responseCode = connection.getResponseCode();
//                connection.disconnect();
//
//                String status = responseCode < 400 ? "PASS" : "FAIL";
//                System.out.printf("[%s] %-20s | %d | %s%n", status, countryLabel, responseCode, url);
//
//                softAssert.assertTrue(responseCode < 400,
//                        "Link failed for " + countryLabel + " -> " + responseCode);
//
//            } catch (Exception e) {
//                System.out.printf("[ERR ] %-20s | ERROR: %s%n", countryLabel, e.getMessage());
//                softAssert.fail("Connection error for " + countryLabel + ": " + e.getMessage());
//            }
//        }
//
//        System.out.println("=================================================");
//        softAssert.assertAll();
//    }
//
//    private String extractCountryFromUrl(String url) {
//        if (url.contains("sogeti.be"))  return "Belgium";
//        if (url.contains("sogeti.fi"))  return "Finland";
//        if (url.contains("sogeti.de"))  return "Germany";
//        if (url.contains("sogeti.ie"))  return "Ireland";
//        if (url.contains("sogeti.lu"))  return "Luxembourg";
//        if (url.contains("sogeti.nl"))  return "Netherlands";
//        if (url.contains("sogeti.no"))  return "Norway";
//        if (url.contains("sogeti.es"))  return "Spain";
//        if (url.contains("sogeti.se"))  return "Sweden";
//        if (url.contains("fr.sogeti"))  return "France";
//        if (url.contains("uk.sogeti"))  return "UK";
//        if (url.contains("us.sogeti"))  return "USA";
//        if (url.contains("sogeti.com")) return "Global";
//        try { return new URL(url).getHost(); } catch (Exception e) { return url; }
//    }
}
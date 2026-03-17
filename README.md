# Sogeti Automation Task

A comprehensive test automation framework for Sogeti.com website, implementing both UI and API test scenarios using industry-standard BDD approach with Cucumber.

## Project Overview

This framework is built as a technical assessment for Sogeti, demonstrating expertise in modern test automation practices. It covers UI testing (navigation, form submission, link validation) and API testing (geographic data validation) with a focus on maintainability, reusability, and scalability.

### Technologies & Tools

- **Java 11+** - Programming language
- **Selenium WebDriver 4.28.0** - Browser automation
- **Cucumber 7.15.0** - BDD framework for behavior-driven testing
- **TestNG 7.9.0** - Test execution and reporting
- **REST Assured 5.4.0** - API testing
- **JavaFaker 1.0.2** - Test data generation
- **WebDriverManager 5.7.0** - Automatic browser driver management
- **Maven** - Build and dependency management

## Architecture

The framework follows **Page Object Model (POM)** design pattern with the following structure:

```
src/
├── main/java/
│   ├── pages/
│   │   ├── BasePage.java          # Common page methods and utilities
│   │   ├── HomePage.java           # Home page objects and methods
│   │   ├── ServicesPage.java      # Services page objects
│   │   ├── QualityEngineeringPage.java
│   │   └── ContactUsPage.java
│   ├── utils/
│   │   ├── DriverManager.java     # ThreadLocal WebDriver management
│   │   ├── ConfigReader.java      # Configuration file reader
│   │   └── APIUtils.java          # API testing utilities
│   └── resources/
│       └── config.properties       # Environment configurations
└── test/
    ├── java/
    │   ├── runners/
    │   │   └── TestRunner.java    # TestNG + Cucumber runner
    │   └── stepdefinitions/
    │       ├── TC1StepDefinitions.java
    │       ├── TC2StepDefinitions.java
    │       ├── TC3StepDefinitions.java
    │       └── APIStepDefinitions.java
    └── resources/
        └── features/
            ├── TC1_NavigateToQualityEngineering.feature
            ├── TC2_ContactUsForm.feature
            ├── TC3_CountryLinks.feature
            └── API_ZippopotamTests.feature
```

### Key Design Principles

1. **Generic & Reusable Methods**: All Selenium interactions are parameterized and reusable across different pages
2. **Page Object Model**: Each page is represented as a separate class with its own locators and methods
3. **Lazy Initialization**: Page objects are instantiated per-step to avoid stale element references
4. **Thread Safety**: `ThreadLocal<WebDriver>` ensures parallel execution capability
5. **BDD Approach**: Feature files use Gherkin syntax with parameterized steps (`{string}`)

## Test Cases

### UI Test Cases

#### TC1: Navigate to Quality Engineering Page
- Opens Sogeti homepage
- Accepts cookies
- Navigates through Services menu to Quality Engineering page
- Validates page title and hover menu items

#### TC2: Contact Us Form Submission
- Fills contact form with dynamic test data
- Validates all required fields
- Submits the form (Note: CAPTCHA may block actual submission)

#### TC3: Country Links Validation
- Collects all country links from Worldwide dropdown
- Sends HTTP HEAD requests to verify each link's status
- Reports all broken links using SoftAssert

### API Test Cases

#### API1: Germany Postal Code Validation
- Validates postal code format for Germany
- Verifies state information for DE-BW (Baden-Württemberg)

#### API2: US Postal Code Validation
- Tests multiple postal codes
- Validates country abbreviation and state data

## Setup & Installation

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Google Chrome browser (latest version)
- Git

### Installation Steps

1. Clone the repository:
```bash
git clone https://github.com/seyfullahkilinc/SogetiAutomationTask.git
cd SogetiAutomationTask
```

2. Install dependencies:
```bash
mvn clean install -DskipTests
```

3. Verify installation:
```bash
mvn test
```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Case (Tag-based)

**TC1 - Quality Engineering Navigation:**
```bash
mvn clean test -Dcucumber.filter.tags="@TC1"
```

**TC2 - Contact Us Form:**
```bash
mvn clean test -Dcucumber.filter.tags="@TC2"
```

**TC3 - Country Links Validation:**
```bash
mvn clean test -Dcucumber.filter.tags="@TC3"
```

**All UI Tests:**
```bash
mvn clean test -Dcucumber.filter.tags="@UI"
```

**All API Tests:**
```bash
mvn clean test -Dcucumber.filter.tags="@API"
```

### Headless Mode

Run tests in headless mode (no browser UI):
```bash
mvn clean test -Dheadless=true
```

### Windows PowerShell/CMD Syntax

When running on Windows PowerShell or CMD, use quotes around the entire property:
```bash
mvn clean test -D"cucumber.filter.tags=@TC1"
```

## Framework Features

### 1. ThreadLocal WebDriver Management
Ensures thread safety for parallel execution:
```java
private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
```

### 2. Generic Locator Pattern
Single method handles multiple form fields via parameterized XPath:
```java
public void fillField(String labelText, String value) {
    String xpath = "//label[contains(text(), '" + labelText + "')]//following-sibling::input";
    WebElement field = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    field.sendKeys(value);
}
```

### 3. Shadow DOM Handling
Recursive JavaScript traversal for cookie consent banners:
```java
private void handlePopups() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    // Recursive shadow DOM traversal
    js.executeScript("...");
}
```

### 4. Soft Assertions
TC3 uses SoftAssert to report all link failures together:
```java
SoftAssert softAssert = new SoftAssert();
// Test all links
softAssert.assertAll();
```

### 5. Java 11 HttpClient
Modern HTTP client for link validation:
```java
HttpClient client = HttpClient.newHttpClient();
HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
```

## Known Limitations

1. **CAPTCHA on Contact Form**: Sogeti.com uses CAPTCHA which may prevent form submission in automated tests
2. **CDP Version Warning**: Chrome 145 may show ChromeDriver CDP version mismatch warnings (suppressed in config)
3. **Dynamic Content**: Some elements may require JavaScript executor for interaction due to sogeti.com's implementation

## Test Reports

After test execution, reports are generated in:
- `target/cucumber-reports/` - HTML reports
- Console output with `pretty` plugin formatting

## Troubleshooting

### StaleElementReferenceException
- Page objects use lazy initialization
- Elements are re-located on each interaction

### Click Intercepted Exception
- Framework uses JavascriptExecutor as fallback
- Popups are handled automatically

### Invalid Element State Exception
- `safeClear()` method checks element state before clearing
- JavaScript fallback for readonly fields



## Author

**Seyfullah Kilınç**
- GitHub: [@seyfullahkilinc](https://github.com/seyfullahkilinc)

practices including BDD, POM, thread safety, and both UI and API testing capabilities.
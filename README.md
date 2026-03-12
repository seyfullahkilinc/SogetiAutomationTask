# SogetiAutomationTask

A test automation framework built with Java, Selenium, Cucumber, and TestNG — covering UI and API test scenarios for the Sogeti website.

---

## Tech Stack

| Tool | Version |
|------|---------|
| Java | 17 |
| Selenium | 4.28.0 |
| Cucumber | 7.15.0 |
| TestNG | 7.9.0 |
| REST Assured | 5.4.0 |
| JavaFaker | 1.0.2 |
| WebDriverManager | 5.7.0 |

---

## Project Structure

```
src/test/java/org/example/
├── pages/
│   ├── BasePage.java               # Common Selenium utilities (wait, popup handling)
│   ├── HomePage.java               # Sogeti homepage interactions + country link checks
│   ├── ContactUsPage.java          # Contact Us form interactions
│   └── QualityEngineeringPage.java # Quality Engineering page assertions
├── stepDefinitions/
│   ├── CommonStepDefinitions.java  # Shared steps (navigate, handle popups)
│   ├── TC1StepDefinitions.java     # Quality Engineering navigation steps
│   ├── TC2StepDefinitions.java     # Contact Us form steps
│   ├── TC3StepDefinitions.java     # Country links validation steps
│   ├── APIStepDefinitions.java     # REST Assured API steps
│   └── Hooks.java                  # @Before / @After lifecycle (tag-based)
├── runners/
│   └── TestRunner.java             # Cucumber + TestNG runner
└── utils/
    ├── DriverManager.java          # ThreadLocal WebDriver management
    └── FakerUtils.java             # Centralized random test data generator

src/test/resources/
├── features/
│   ├── UI Tests/
│   │   ├── tc1_quality_engineering.feature
│   │   ├── tc2_contact_us_form.feature
│   │   └── tc3_global_links_healthcheck.feature
│   └── ApiTests/
│       ├── tc01_location_api.feature
│       └── tc02_international.feature
```

---

## Test Cases

### UI Tests

| ID | Description | Tag |
|----|-------------|-----|
| TC1 | Navigate to Quality Engineering page via Services menu | `@Case1` |
| TC2 | Submit Contact Us form with randomly generated data | `@Case2` |
| TC3 | Verify all country-specific Sogeti links return HTTP 200 | `@Case3` |

### API Tests

| ID | Description | Tag |
|----|-------------|-----|
| API1 | Validate geographical data for a German postal code | `@API_TC01` |
| API2 | Data-driven international postal code validation | `@API_TC02` |

---

## Design Principles

- **Page Object Model (POM)** — All page interactions are encapsulated in page classes
- **Generic & Reusable Methods** — Locators accept parameters; no hardcoded values
- **ThreadLocal DriverManager** — Safe for parallel test execution
- **Tag-based Hooks** — Browser only launches for `@UI` scenarios; API tests run without browser
- **Lazy Initialization** — Page objects are created per-step to always reference the active driver
- **FakerUtils** — All random test data generated from a single utility class

---

## How to Run

**Run all tests:**
```bash
mvn test
```

**Run only UI tests:**
```bash
mvn test -D"cucumber.filter.tags=@UI"
```

**Run only API tests:**
```bash
mvn test -D"cucumber.filter.tags=@API"
```

**Run a specific test case:**
```bash
mvn test -D"cucumber.filter.tags=@Case1"
```

**Run in headless mode:**
```bash
mvn test -Dheadless=true
```

---

## Prerequisites

- Java 17+
- Maven 3.6+
- Google Chrome (latest)

> WebDriverManager automatically downloads the matching ChromeDriver — no manual setup needed.

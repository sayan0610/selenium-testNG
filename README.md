# Selenium TestNG + Cucumber + Allure Parallel Multi-Browser Framework

This project is a Java Selenium automation framework using TestNG, Cucumber, and Maven, designed for parallel cross-browser testing and rich Allure reporting of your test-platform web app.

## Features
- **Selenium WebDriver**: Automates Chrome, Firefox, Edge, and Safari browsers (Edge is skipped on Mac).
- **TestNG**: Enables parallel execution and test management.
- **Cucumber**: BDD feature file support and step definitions.
- **Allure Reporting**: Automatic step and screenshot capture for every scenario and step.
- **Thread-safe WebDriver**: Uses ThreadLocal for safe parallel browser sessions.
- **TestNG Factory**: Runs the same test in multiple browsers simultaneously.
- **Headless Mode**: Chrome and Firefox run in headless mode by default.
- **Easy Command**: Run all tests in parallel with a single Maven command.

## Prerequisites
- Java 11 or higher
- Maven
- Chrome, Firefox, Safari browsers installed
- [Safari Only] Enable "Allow Remote Automation" in Safari > Develop menu
- Allure CLI installed (`npm install -g allure-commandline` or via [docs](https://docs.qameta.io/allure/))

## How to Run Tests
1. Clone the repository:
    ```sh
    git clone <repo-url>
    cd serenity-bdd-framework
    ```
2. Run all tests in parallel:
    ```sh
    mvn clean test
    ```
3. Generate and view Allure report:
    ```sh
    allure generate ./allure-results --clean -o ./allure-report
    allure open ./allure-report
    ```

## Project Structure
```
serenity-bdd-framework/
├── src/
│   └── test/
│       └── java/
│           ├── code/
│           │   ├── CombinedRunner.java
│           │   ├── StepDefinitions.java
│           │   ├── UnifiedTaskManagerTest.java
│           │   └── hooks/
│           │       ├── BrowserHooks.java
│           │       └── AllureStepHooks.java
├── testng.xml
├── pom.xml
├── README.md
```

## Key Files
- `CombinedRunner.java`: Cucumber-TestNG runner for BDD scenarios and Allure integration.
- `StepDefinitions.java`: Cucumber step definitions for feature files.
- `UnifiedTaskManagerTest.java`: Main Selenium test class, runs in parallel across browsers using TestNG @Factory.
- `hooks/BrowserHooks.java`: Manages thread-safe WebDriver instances for each browser and scenario.
- `hooks/AllureStepHooks.java`: Automatically attaches screenshots and step info to Allure after every step.
- `testng.xml`: TestNG suite configuration for parallel execution.
- `pom.xml`: Maven build configuration and dependencies.

## Supported Browsers
- **Chrome** (headless)
- **Firefox** (headless)
- **Safari** (requires remote automation enabled)
- **Edge** (skipped on Mac)

## Troubleshooting
- **Edge Fails on Mac**: Edge is automatically skipped on Mac OS X.
- **Safari Fails**: Enable "Allow Remote Automation" in Safari's Develop menu.
- **WebDriver Not Found**: Ensure browser drivers are installed and available in PATH.
- **Allure Not Recording Steps/Screenshots**: Ensure `AllureStepHooks.java` is present and glue path includes `code.hooks`.

## Customization
- Add more test classes or step definitions in `src/test/java/code`.
- Update `BrowserHooks.java` to support more browsers or custom options.
- Modify `testng.xml` for additional suite/test configurations.
- Edit `AllureStepHooks.java` to customize what is attached to Allure.

## License
MIT

---
For questions or contributions, open an issue or pull request.

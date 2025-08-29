# Selenium TestNG Parallel Multi-Browser Framework

This project is a Java Selenium automation framework using TestNG and Maven, designed for parallel cross-browser testing of your test-platform web app.

## Features
- **Selenium WebDriver**: Automates Chrome, Firefox, and Safari browsers (Edge is skipped on Mac).
- **TestNG**: Enables parallel execution and test management.
- **Thread-safe WebDriver**: Uses ThreadLocal for safe parallel browser sessions.
- **TestNG Factory**: Runs the same test in multiple browsers simultaneously.
- **Headless Mode**: Chrome and Firefox run in headless mode by default.
- **Easy Command**: Run all tests in parallel with a single Maven command.

## Prerequisites
- Java 11 or higher
- Maven
- Chrome, Firefox, Safari browsers installed
- [Safari Only] Enable "Allow Remote Automation" in Safari > Develop menu

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

## Project Structure
```
serenity-bdd-framework/
├── src/
│   └── test/
│       └── java/
│           ├── BrowserManager.java
│           ├── TaskManagerAdvancedTest.java
│           ├── StepDefinitions.java
│           └── TestNGSuiteRunner.java
├── testng.xml
├── pom.xml
└── README.md
```

## Key Files
- `BrowserManager.java`: Manages thread-safe WebDriver instances for each browser.
- `TaskManagerAdvancedTest.java`: Main Selenium test class, runs in parallel across browsers using TestNG @Factory.
- `testng.xml`: TestNG suite configuration for parallel execution.
- `pom.xml`: Maven build configuration.

## Supported Browsers
- **Chrome** (headless)
- **Firefox** (headless)
- **Safari** (requires remote automation enabled)
- **Edge** (skipped on Mac)

## Troubleshooting
- **Edge Fails on Mac**: Edge is automatically skipped on Mac OS X.
- **Safari Fails**: Enable "Allow Remote Automation" in Safari's Develop menu.
- **WebDriver Not Found**: Ensure browser drivers are installed and available in PATH.

## Customization
- Add more test classes in `src/test/java`.
- Update `BrowserManager.java` to support more browsers or custom options.
- Modify `testng.xml` for additional suite/test configurations.

## License
MIT

---
For questions or contributions, open an issue or pull request.

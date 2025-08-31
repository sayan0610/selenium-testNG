package code.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserHooks {
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Before
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        boolean isCI = System.getenv("CI") != null;
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        // Add browser label to Allure report
        Allure.label("browser", browser);

        // Factory logic: choose browsers based on OS/CI
        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");
            driver.set(new ChromeDriver(options));
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) options.addArguments("-headless");
            driver.set(new FirefoxDriver(options));
        } else if ("edge".equalsIgnoreCase(browser) && !os.contains("mac")) {
            EdgeOptions options = new EdgeOptions();
            if (headless) options.addArguments("--headless=new");
            driver.set(new EdgeDriver(options));
        } else if ("safari".equalsIgnoreCase(browser) && !isCI) {
            driver.set(new SafariDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @After
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

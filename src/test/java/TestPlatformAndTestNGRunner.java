
package test.java;
import java.io.IOException;

public class TestPlatformAndTestNGRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Start the test-platform (assumes npm start in test-platform directory)
        Process platformProcess = new ProcessBuilder()
            .directory(new java.io.File("test-platform"))
            .command("npm", "start")
            .inheritIO()
            .start();

        // Wait for the server to start (adjust as needed)
        System.out.println("Waiting for test-platform to start...");
        Thread.sleep(8000); // Wait 8 seconds for server startup

        // Run TestNG suite
        System.out.println("Starting TestNG tests...");
        org.testng.TestNG testng = new org.testng.TestNG();
        java.util.List<String> suites = new java.util.ArrayList<>();
        suites.add("testng.xml");
        testng.setTestSuites(suites);
        testng.run();

        // Stop the test-platform server
        System.out.println("Stopping test-platform...");
        platformProcess.destroy();
    }
}

package code;

public class ParallelBrowserTestLauncher {
    public static void main(String[] args) throws Exception {
        String[] browsers = {"chrome", "firefox", "edge", "safari"};
        Process[] processes = new Process[browsers.length];
        System.out.println("Starting parallel tests for browsers: ");
        for (int i = 0; i < browsers.length; i++) {
            String browser = browsers[i];
            ProcessBuilder pb = new ProcessBuilder(
                "mvn", "test", "-Dbrowser=" + browser,
                "-Dbrowser.headless=true",
                "-Dsurefire.suiteXmlFiles=testng.xml",
                "-Dallure.results.directory=allure-results/" + browser,
                "-Dbrowser.fullscreen=true",
                "-Dbrowser.clearCache=true",
                "-Dbrowser.clearCookies=true",
                "-Dbrowser.disableNotifications=true",
                "-Dbrowser.disableExtensions=true",
                "-Dbrowser.incognito=true",
                "-Dbrowser.disablePopups=true",
                "-Dbrowser.acceptInsecureCerts=true"
            );
            pb.inheritIO(); // Show output in console
            processes[i] = pb.start();
        }
        // Wait for all processes to finish
        for (Process p : processes) {
            p.waitFor();
        }
        System.out.println("All browser test runs complete.");
        // Merge Allure results
        ProcessBuilder allureMerge = new ProcessBuilder(
            "allure", "generate", "./allure-results/*", "--clean", "-o", "./allure-report"
        );
        allureMerge.inheritIO();
        Process mergeProcess = allureMerge.start();
        mergeProcess.waitFor();
        System.out.println("Allure report generated and merged.");
    }
}

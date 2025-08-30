package code;

import org.testng.annotations.Test;

public class ParallelBrowserTestLauncherTestNG {
    @Test
    public void runAllBrowsersInParallel() throws Exception {
        ParallelBrowserTestLauncher.main(new String[]{});
    }
}

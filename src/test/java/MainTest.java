import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.engine.support.hierarchical.SingleTestExecutor;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled("This test is disabled to prevent it from running automatically")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void testMainExecutionTime() {
        // Preparing the code to run the main method and check its execution time
        SingleTestExecutor.Executable executable = () -> {
            // Code to run the main method
            Main.main(new String[]{});
        };

        // Checking the execution time of the main method
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(22), (Executable) executable);
    }
}


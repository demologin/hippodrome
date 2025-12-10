import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Disabled("This test is disabled but can be run manually")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void testDurationOfExecutionOfMain() throws Exception {
        Main.main(new String[]{});
    }
}
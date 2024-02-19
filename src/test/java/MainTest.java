import org.junit.jupiter.api.*;

import static java.util.concurrent.TimeUnit.SECONDS;

class MainTest {
    @Disabled("Этот тест временно отключен")
    @Test
    @Timeout(value = 22, unit = SECONDS)
    void main_ShouldExecuteWithinTimeLimit() throws Exception {
        Main.main(new String[]{});
    }
}
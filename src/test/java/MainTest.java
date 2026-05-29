import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Test
    @Timeout(20)
    @Disabled
    @DisplayName("When run test => not time limit")
    void whenRunTestNotTimeLimit() throws Exception {
        Main.main(null);
    }
}
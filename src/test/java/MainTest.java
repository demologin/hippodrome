import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    String[] args;

    @Test
    @Timeout(value = 22)
    @Disabled
    @DisplayName("Method main time test")
    void mainTimeTest() throws Exception {
        Main.main(args);
    }
}
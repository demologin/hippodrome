import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String[] args;
    @Test
    @Timeout(value = 22)
    @Disabled
    void mainTimeTest() throws Exception {
        Main.main(args);
    }
}
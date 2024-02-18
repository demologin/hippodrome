import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22)
    void mainTest() throws Exception {
        String[] arg = {};
        Main.main(arg);
    }
}
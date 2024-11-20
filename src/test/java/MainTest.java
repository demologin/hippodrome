import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    String[] args;

    @Test
    @Timeout(value = 22)
    void mainTimeTest() throws Exception {
        Main.main(args);
    }
}

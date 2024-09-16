import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {

    @Test
    @Timeout(value = 22)
    @Disabled
    void mainExecutionTime() throws Exception {
        Main.main(new String[0]);
    }
}
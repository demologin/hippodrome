import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {
    private String[] args;
    @Timeout(22)
    @Test
    @Disabled
    void mainIsNotMoreThen22Seconds() throws Exception {
        Main.main(args);
    }

}
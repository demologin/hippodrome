import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    void givenStartMainWhenStartedMainThenWork22Seconds() throws Exception {
        Main.main(null);
    }
}
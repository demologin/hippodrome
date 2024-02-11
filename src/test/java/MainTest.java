import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    void testRunningTimeOfMainMethod() throws Exception {
        //Check that the method runs no longer than 22 seconds.
        Main.main(null);
    }
}
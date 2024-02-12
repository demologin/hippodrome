import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    String[] args;
    @Test
    @Timeout(22)
    @Disabled
    void publicStaticVoidMain() throws Exception {
        Main.main(args);
    }
}
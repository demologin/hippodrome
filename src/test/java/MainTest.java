import org.junit.jupiter.api.*;

class MainTest {

    @Disabled
    @Test
    @Timeout(22)
    void mainMethodExec22Secs() throws Exception {
        Main.main(null);
    }
}
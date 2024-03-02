import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Timeout(22)
    @Disabled
    void main() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(21);
    }
}
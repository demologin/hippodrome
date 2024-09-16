import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void whenRunMain_MethodRunsFasterThan22sec() throws Exception {
        Main.main(null);
//        Assertions.assertThrows(Exception.class, () -> Main.main(null));
    }
}
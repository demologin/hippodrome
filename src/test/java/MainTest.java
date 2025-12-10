import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled   // отключаем после проверки
    void test_main_executes_less_than_22_seconds() throws Exception {
        Main.main(new String[]{});
    }
}

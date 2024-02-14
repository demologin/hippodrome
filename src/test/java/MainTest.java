import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    public void testMainMethodTime() throws Exception {
        //вызываем метод main с пустым массивом аргументов
        Main.main(new String[]{});
    }
}
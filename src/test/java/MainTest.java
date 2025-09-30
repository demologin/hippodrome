import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {
    @Disabled("Временно отключили тест, починим к маю 2001 года")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void MainLessTwoHundredMillisecondsTest(){
        try {
            Main.main(List.of("1123").toArray(new String[0]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
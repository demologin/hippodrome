import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.engine.support.hierarchical.SingleTestExecutor;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled("This test is disabled to prevent it from running automatically")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void testMainExecutionTime() {
        // Подготовка кода для запуска метода main и проверки времени его выполнения
        SingleTestExecutor.Executable executable = () -> {
            // Код для запуска метода main
            Main.main(new String[]{});
        };

        // Проверка времени выполнения метода main
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(22), (Executable) executable);
    }
}


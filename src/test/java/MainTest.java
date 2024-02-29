import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    String[] args;

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled
    void main_takesNoLonger22Sec() throws Exception {
        Main.main(args);
    }
}
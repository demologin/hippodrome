import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void main() {
        try {
            Main.main(new String[]{"sdds", "ssdds"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
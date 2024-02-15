import org.junit.jupiter.api.*;

class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void main() throws Exception {
        Main.main(null);
//        Assertions.assertThrows(Exception.class, () -> Main.main(null));
    }
}
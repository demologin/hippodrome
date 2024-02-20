import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest
{
    @Test
//    @Disabled
    @Timeout(22)
    void testMainTime() throws Exception
    {
        Main.main(null);
    }
}
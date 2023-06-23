

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AircraftTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AircraftTest
{
    /**
     * Default constructor for test class AircraftTest
     */
    public AircraftTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testFindPrice()
    {
        assertEquals(11994.912, Aircraft.findPrice(25, 5, 2), 0.1);
    }

    @Test
    public void createAircraftObject()
    {
        Aircraft aircraft1 = new Aircraft(5, 1, 2, 0);
        User user1 = new User("Joao", "01234567890");
        aircraft1.sell("comprar a1", user1);
        assertEquals("Joao", aircraft1.seatPassengerName(0, 0));
    }
}





import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SeatTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SeatTest
{
    /**
     * Default constructor for test class SeatTest
     */
    public SeatTest()
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
    public void createSeatObject()
    {
        Seat seat1 = new Seat("A15", "Joao", "012.345.678-90", 11994.912, true);
        assertEquals(true, seat1.checkOccupied());
        assertEquals(11994.912, seat1.getPrice(), 0.1);
        seat1.setCode("B22");
        seat1.vacant();
        assertEquals(false, seat1.checkOccupied());
        assertEquals("", seat1.getName());
    }
}


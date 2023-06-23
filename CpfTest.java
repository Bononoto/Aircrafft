

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CpfTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CpfTest
{
    /**
     * Default constructor for test class CpfTest
     */
    public CpfTest()
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
    public void checkValidCpf()
    {
        assertEquals(true, Cpf.checkValid("01234567890"));
        assertEquals(false, Cpf.checkValid("0123456789"));
        assertEquals(false, Cpf.checkValid("abcdefghijk"));
        assertEquals(true, Cpf.checkValid("012.345.678-90"));
    }
}


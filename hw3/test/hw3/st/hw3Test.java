package hw3.st;

import org.junit.Before;
import org.junit.Test;

public class hw3Test {
    private PrintPrimes p;
    @Before
    public void setUp() throws Exception {
        p = new PrintPrimes();
    }

    @Test
    public void testPrintPrimes() {
        p.printPrimes(15);

    }
}

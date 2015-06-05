package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {
        assertEquals((long)10000, (long)SEK100.getAmount());
        assertEquals((long)1000, (long)EUR10.getAmount());
        assertEquals((long)0, (long)EUR0.getAmount());
        assertEquals((long)-10000, (long)SEKn100.getAmount());
    }

    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
        assertEquals(EUR0.getCurrency(), EUR10.getCurrency());
    }

    @Test
    public void testToString() {
        assertEquals("10 EUR", EUR10.toString());
    }

    @Test
    public void testGlobalValue() {
        assertEquals((long)1500, (long)EUR10.universalValue());
    }

    @Test
    public void testEqualsMoney() {
        assertEquals(new Money(1000, EUR), EUR10);
        assertEquals(SEK0, EUR0);
        assertEquals(SEK100, EUR10);
    }

    @Test
    public void testAdd() {
        assertEquals(EUR20, EUR10.add(SEK100));
        assertEquals(EUR20, EUR10.add(EUR10));
        assertEquals(SEK200, SEK100.add(SEK100));
    }

    @Test
    public void testSub() {
        assertEquals(EUR10, EUR20.sub(SEK100));
        assertEquals(EUR10, EUR20.sub(EUR10));
        assertEquals(SEK0, SEK100.sub(SEK100));
    }

    @Test
    public void testIsZero() {
        assertTrue(EUR0.isZero());
        assertTrue(SEK0.isZero());
        assertFalse(SEK100.isZero());
    }

    @Test
    public void testNegate() {
        assertEquals(SEKn100, SEK100.negate());
    }

    @Test
    public void testCompareTo() {
        assertEquals(-1, SEK100.compareTo(SEK200));
        assertEquals(0, EUR10.compareTo(SEK100));
        assertEquals(1, SEK200.compareTo(EUR0));
    }
}

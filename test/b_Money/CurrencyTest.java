package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {

    Currency SEK, DKK, NOK, EUR;
    Double rateSEK = 0.15, rateDKK = 0.20, rateNOK = 0.25, rateEUR = 1.5;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", rateSEK);
        DKK = new Currency("DKK", rateDKK);
        NOK = new Currency("NOK", rateNOK);
        EUR = new Currency("EUR", rateEUR);
    }

    @Test
    public void testGetName() {
        assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("NOK", NOK.getName());
        assertEquals("EUR", EUR.getName());
    }

    @Test
    public void testGetRate() {
        assertEquals(rateSEK, SEK.getRate());    
        assertEquals(rateNOK, NOK.getRate());  
        assertEquals(rateDKK, DKK.getRate());  
        assertEquals(rateEUR, EUR.getRate());  
    }

    @Test
    public void testSetRate() {
        rateSEK = 0.23;
        rateNOK = 0.22;
        rateDKK = 0.13;
        rateEUR = 1.62;
        SEK.setRate(rateSEK);
        NOK.setRate(rateNOK);
        DKK.setRate(rateDKK);
        EUR.setRate(rateEUR);
        assertEquals(rateSEK, SEK.getRate());    
        assertEquals(rateNOK, NOK.getRate());  
        assertEquals(rateDKK, DKK.getRate());  
        assertEquals(rateEUR, EUR.getRate());  
    }

    @Test
    public void testUniversalValue() {
        long EURNewVal = (long)(rateEUR*150);
        assertEquals(EURNewVal, (long)EUR.universalValue(150));
        long DKKNewVal = (long)(rateDKK*20);
        assertEquals(DKKNewVal, (long)DKK.universalValue(20));
    }

    @Test
    public void testValueInThisCurrency() {
        long valueInEUR = 10;
        assertEquals(valueInEUR, (long)EUR.valueInThisCurrency(100, SEK));
    }

}

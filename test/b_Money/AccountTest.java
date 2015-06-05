package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    Currency SEK, DKK;
    Bank Nordea;
    Bank DanskeBank;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        
        SweBank.openAccount("Alice");
        testAccount = SweBank.openAccount("Hans");
        
        SweBank.deposit("Alice", new Money(1000000, SEK));
        testAccount.deposit(new Money(10000000, SEK));
    }

    @Test
    public void testAddRemoveTimedPayment() throws AccountDoesNotExistException {
        SweBank.addTimedPayment("Alice", "1", 10, 1, new Money(1000, SEK), SweBank, "Hans");
        SweBank.removeTimedPayment("Alice", "1");
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        SweBank.addTimedPayment("Alice", "1", 10, 0, new Money(1000, SEK), SweBank, "Hans");
        SweBank.tick();
        assertEquals(1000000 - 1000, (long)SweBank.getBalance("Alice"));
        assertEquals(10000000 + 1000, (long)SweBank.getBalance("Hans"));
    }

    @Test
    public void testWithdraw() {
        int amount = 10000;
        testAccount.withdraw(new Money(amount, SEK));
        assertEquals(new Money(10000000 - amount, SEK), testAccount.getBalance());
    }

    @Test
    public void testGetBalance() {
        assertEquals(new Money(10000000, SEK), testAccount.getBalance());
    }
}

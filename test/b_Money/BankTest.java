package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTest {

    Currency SEK, DKK;
    Bank SweBank, Nordea, DanskeBank;

    @Before
    public void setUp() throws Exception {
        DKK = new Currency("DKK", 0.20);
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        Nordea = new Bank("Nordea", SEK);
        DanskeBank = new Bank("DanskeBank", DKK);
        SweBank.openAccount("Ulrika");
        SweBank.openAccount("Bob");
        Nordea.openAccount("Bob");
        DanskeBank.openAccount("Gertrud");
    }

    @Test
    public void testGetName() {
        assertEquals("SweBank", SweBank.getName());
        assertEquals("Nordea", Nordea.getName());
        assertEquals("DanskeBank", DanskeBank.getName());
    }

    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SweBank.getCurrency());
        assertEquals(DKK, DanskeBank.getCurrency());
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testOpenAccount() throws AccountExistsException {
        SweBank.openAccount("Alice");
        exception.expect(AccountExistsException.class);
        SweBank.openAccount("Bob");
    }

    @Test
    public void testDeposit() throws AccountDoesNotExistException {
        SweBank.deposit("Bob", new Money(1000, SEK));
        assertEquals(1000, (long)SweBank.getBalance("Bob"));
        exception.expect(AccountDoesNotExistException.class);
        SweBank.deposit("Alice", new Money(1000, SEK));
    }

    @Test
    public void testWithdraw() throws AccountDoesNotExistException {
        SweBank.withdraw("Bob", new Money(1000, SEK));
        assertEquals(-1000, (long)SweBank.getBalance("Bob"));
        exception.expect(AccountDoesNotExistException.class);
        SweBank.withdraw("Alice", new Money(1000, SEK));
    }

    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        assertEquals(0, (long)SweBank.getBalance("Bob"));
        exception.expect(AccountDoesNotExistException.class);
        assertEquals(0, (long)SweBank.getBalance("Alice"));
    }

    @Test
    public void testTransfer() throws AccountDoesNotExistException {
        SweBank.transfer("Bob", "Ulrika", new Money(1000, SEK));
        assertEquals(-1000, (long)SweBank.getBalance("Bob"));
        assertEquals(1000, (long)SweBank.getBalance("Ulrika"));
        exception.expect(AccountDoesNotExistException.class);
        SweBank.transfer("Bob", "Alice", new Money(1000, SEK));
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        SweBank.addTimedPayment("Bob", "Telephone", 2, 0, new Money(1000, SEK), SweBank, "Ulrika");
        SweBank.tick();
        assertEquals(0, (long)SweBank.getBalance("Bob"));
        exception.expect(AccountDoesNotExistException.class);
        SweBank.addTimedPayment("Bob", "Telephone", 1, 3, new Money(1000, SEK), SweBank, "Alice");
    }
}

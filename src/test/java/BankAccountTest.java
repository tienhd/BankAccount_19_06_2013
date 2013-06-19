import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    BankAccountDao bankAccountDao = mock(BankAccountDao.class);
    public final String accountNumber = "1234567890";
    @Before
    public void setUp() {
        reset(bankAccountDao);
        BankAccount.setBankAccountDao(bankAccountDao);
    }

    @Test
    public void testOpenNewAccountWithZeroBalanceAndSaveToDatabase() {
        BankAccount.openAccount(accountNumber);
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        verify(bankAccountDao).openAccount(accountNumberCaptor.capture());
        assertEquals(accountNumberCaptor.getValue(),accountNumber);
    }
}

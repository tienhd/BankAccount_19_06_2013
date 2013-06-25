import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    BankAccountDao bankAccountDao = mock(BankAccountDao.class);
    TransactionDao transactionDao = mock(TransactionDao.class);
    Calendar mockTime = mock(Calendar.class);
    public final String accountNumber = "1234567890";

    @Before
    public void setUp() {
        reset(bankAccountDao);
        reset(mockTime);
        reset(transactionDao);
        BankAccount.setBankAccountDao(bankAccountDao);
        BankAccount.setTimeSystem(mockTime);
        BankAccount.setTransactionDao(transactionDao);
    }

    @Test
    public void testOpenNewAccountWithZeroBalanceAndSaveToDatabase() throws Exception{
        BankAccount.openAccount(accountNumber);
        double balance = 0;
        long timeStamp = 10000;
        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);

        String log = "Open new account";
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> balanceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        verify(bankAccountDao).saveAccount(accountNumberCaptor.capture(),balanceCaptor.capture(),logCaptor.capture(),timeStampCaptor.capture());
        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(balanceCaptor.getValue(),balance,0.001);
        assertEquals(logCaptor.getValue(),log);
    }

    @Test
    public void testGetTheAccountByAccountNumber() throws Exception{
        BankAccount.getAccount(accountNumber);
        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        verify(bankAccountDao).getAccount(accountNumberCaptor.capture());
        assertEquals(accountNumberCaptor.getValue(),accountNumber);
    }

    @Test
    public void testOpenNewAccountThenGetByAccountNumber() throws Exception{
        BankAccount.openAccount(accountNumber);
        BankAccountDTO accountDTO = new BankAccountDTO(accountNumber);
        when(bankAccountDao.getAccount(accountNumber)).thenReturn(accountDTO);

        BankAccount.getAccount(accountNumber);
        verify(bankAccountDao).getAccount(accountNumber);
    }

    @Test
    public void testDepositMoneyToAccountThenSaveChangeToDatabase() throws Exception{
        double initBalance = 50;
        double amount = 50;
        double balance = 100;
        long timeStamp = 10000;
        String log = "deposited 50";

        BankAccountDTO accountDTO = new BankAccountDTO(accountNumber,initBalance);
        when(bankAccountDao.getAccount(accountNumber)).thenReturn(accountDTO);
        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);

        BankAccount.deposit(accountNumber,amount,log);
        verify(bankAccountDao).getAccount(accountNumber);

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> balanceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> timeCaptor = ArgumentCaptor.forClass(Long.class);
        verify(bankAccountDao).saveAccount(accountNumberCaptor.capture(),balanceCaptor.capture(),logCaptor.capture(),timeCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(balanceCaptor.getValue(),balance, 0.001);
        assertEquals(logCaptor.getValue(),log);
        assertEquals(timeCaptor.getValue().longValue(),timeStamp);
    }

    @Test
    public void testDepositAccountThenSaveTheTransactionLogToDatabase() throws Exception{

        double initBalance = 50;
        double amount = 50;
        double balance = 100;
        long timeStamp = 10000;
        String log = "deposited 50";

        BankAccountDTO accountDTO = new BankAccountDTO(accountNumber,initBalance);
        when(bankAccountDao.getAccount(accountNumber)).thenReturn(accountDTO);

        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);

        BankAccount.deposit(accountNumber,amount,log);
        verify(bankAccountDao).getAccount(accountNumber);

        verify(transactionDao).depositLog(accountNumber,amount,timeStamp,log);

    }

    @Test
    public void testWithdrawMoneyFromAccountThenSaveChangeToDatabase() throws Exception{
        double initBalance = 100;
        double amount = 50;
        double balance = 50;
        long timeStamp = 10000;
        String log = "withdraw 50";

        BankAccountDTO accountDTO = new BankAccountDTO(accountNumber,initBalance);
        when(bankAccountDao.getAccount(accountNumber)).thenReturn(accountDTO);
        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);
        BankAccount.withdraw(accountNumber,amount,log);
        verify(bankAccountDao).getAccount(accountNumber);

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> balanceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> timeCaptor = ArgumentCaptor.forClass(Long.class);
        verify(bankAccountDao).saveAccount(accountNumberCaptor.capture(),balanceCaptor.capture(),logCaptor.capture(),timeCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(balanceCaptor.getValue(),balance, 0.001);
        assertEquals(logCaptor.getValue(),log);
        assertEquals(timeCaptor.getValue().longValue(),timeStamp);
    }

    @Test
    public void testWithdrawAccountThenSaveTheTransactionLogToDatabase() throws Exception{

        double initBalance = 50;
        double amount = 50;
        double balance = 100;
        long timeStamp = 10000;
        String log = "deposited 50";

        BankAccountDTO accountDTO = new BankAccountDTO(accountNumber,initBalance);
        when(bankAccountDao.getAccount(accountNumber)).thenReturn(accountDTO);

        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);

        BankAccount.withdraw(accountNumber,amount,log);
        verify(bankAccountDao).getAccount(accountNumber);

        verify(transactionDao).withdrawLog(accountNumber,amount,timeStamp,log);

    }
}

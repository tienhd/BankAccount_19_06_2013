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
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionTest {
    TransactionDao transactionDao = mock(TransactionDao.class);
    Calendar mockTime = mock(Calendar.class);

    public final String accountNumber = "1234567890";
    @Before
    public void setUp() {
        reset(transactionDao);
        reset(mockTime);
        Transaction.setTransactionDao(transactionDao);
        Transaction.setTimeSystem(mockTime);
    }

    @Test
    public void testDepositAccountThenSaveTheTransactionLogToDatabase() {
        double amountMoney = 50;
        String log = "Deposit 50 to account";
        long timeStamp = 10000;

        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);
        Transaction.depositLog(accountNumber,amountMoney, log);

        verify(mockTime).getTimeInMillis();

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> amountMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(transactionDao).depositLog(accountNumberCaptor.capture(),amountMoneyCaptor.capture(),timeStampCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(amountMoneyCaptor.getValue(),amountMoney,0.001);
        assertEquals(timeStampCaptor.getValue().longValue(),timeStamp);
        assertEquals(logCaptor.getValue(),log);

    }

    @Test
    public void testWithdrawAccountThenSaveTheTransactionLogToDatabase() {
        double amountMoney = 50;
        String log = "Withdraw 50 from account";
        long timeStamp = 10000;

        when(mockTime.getTimeInMillis()).thenReturn(timeStamp);
        Transaction.withdrawLog(accountNumber,amountMoney, log);

        verify(mockTime).getTimeInMillis();

        ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> amountMoneyCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        verify(transactionDao).withdrawLog(accountNumberCaptor.capture(),amountMoneyCaptor.capture(),timeStampCaptor.capture(),logCaptor.capture());

        assertEquals(accountNumberCaptor.getValue(),accountNumber);
        assertEquals(amountMoneyCaptor.getValue(),amountMoney,0.001);
        assertEquals(timeStampCaptor.getValue().longValue(),timeStamp);
        assertEquals(logCaptor.getValue(),log);

    }

    @Test
    public void testGetAllTransactionOccurred() {
        Transaction.getTransactionsOccurred(accountNumber);
        verify(transactionDao).getTransactionOccurred(accountNumber);
    }

    @Test
    public void testGetTransactionOccurredBetween2TimeStamp() {
        long startTime = 10000;
        long endTime = 10500;
        Transaction.getTransactionsOccurred(accountNumber,startTime,endTime);
        verify(transactionDao).getTransactionOccurred(accountNumber,startTime,endTime);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetTransactionOccurredBetween2TimeStampThrowExceptionWhenEndTimeSmallerThanStartTime() {
        long startTime = 10500;
        long endTime = 10000;
        Transaction.getTransactionsOccurred(accountNumber,startTime,endTime);
    }


}

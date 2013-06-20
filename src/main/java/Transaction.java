import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private static TransactionDao transactionDao;
    private static Calendar timeSystem;

    public static void setTransactionDao(TransactionDao transactionDao) {
        Transaction.transactionDao = transactionDao;
    }

    public static void setTimeSystem(Calendar timeSystem) {
        Transaction.timeSystem = timeSystem;
    }

    public static void depositLog(String accountNumber, double amountMoney, String log) {
        long timeStamp = timeSystem.getTimeInMillis();
        transactionDao.depositLog(accountNumber,amountMoney,timeStamp,log);
    }

    public static void withdrawLog(String accountNumber, double amountMoney, String log) {
        long timeStamp = timeSystem.getTimeInMillis();
        transactionDao.withdrawLog(accountNumber, amountMoney, timeStamp, log);
    }

    public static ArrayList<TransactionDTO> getTransactionsOccurred() {
        return transactionDao.getTransactionOccurred();
    }

    public static ArrayList<TransactionDTO> getTransactionsOccurred(long startTime, long endTime) {
        return transactionDao.getTransactionOccurred(startTime,endTime);
    }
}

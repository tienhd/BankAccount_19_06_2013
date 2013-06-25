import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static BankAccountDao bankAccountDao;
    private static Calendar timeSystem;
    private static TransactionDao transactionDao;

    public static void setBankAccountDao(BankAccountDao bankAccountDao) {
        BankAccount.bankAccountDao = bankAccountDao;
    }

    public static void openAccount(String accountNumber) throws Exception{
        double balance = 0;
        String log = "Open new account";
        long timeStamp = timeSystem.getTimeInMillis();
        bankAccountDao.saveAccount(accountNumber,balance,log,timeStamp);
    }

    public static BankAccountDTO getAccount(String accountNumber) throws Exception{
        return bankAccountDao.getAccount(accountNumber);
    }

    public static void deposit(String accountNumber, double amount, String log) throws Exception{
        BankAccountDTO accountDTO = bankAccountDao.getAccount(accountNumber);
        double newBalance = accountDTO.getBalance() + amount;
        long timeStamp = timeSystem.getTimeInMillis();
        bankAccountDao.saveAccount(accountNumber,newBalance,log,timeStamp);

        Transaction.setTransactionDao(transactionDao);
        Transaction.setTimeSystem(timeSystem);
        Transaction.depositLog(accountNumber,amount,log);
    }

    public static void setTimeSystem(Calendar timeSystem) {
        BankAccount.timeSystem = timeSystem;
    }

    public static void setTransactionDao(TransactionDao transactionDao) {
        BankAccount.transactionDao = transactionDao;
    }

    public static Calendar getTimeSystem() {
        return timeSystem;
    }

    public static void withdraw(String accountNumber, double amount, String log) throws Exception{
        BankAccountDTO accountDTO = bankAccountDao.getAccount(accountNumber);
        double newBalance = accountDTO.getBalance() - amount;
        long timeStamp = timeSystem.getTimeInMillis();
        bankAccountDao.saveAccount(accountNumber,newBalance,log,timeStamp);

        Transaction.setTransactionDao(transactionDao);
        Transaction.setTimeSystem(timeSystem);
        Transaction.withdrawLog(accountNumber,amount,log);
    }
}

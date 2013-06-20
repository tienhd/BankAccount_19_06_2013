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

    public static void openAccount(String accountNumber) {
        double balance = 0;
        String log = "Open new account";
        bankAccountDao.saveAccount(accountNumber,balance,log);
    }

    public static BankAccountDTO getAccount(String accountNumber) {
        return bankAccountDao.getAccount(accountNumber);
    }

    public static void deposit(String accountNumber, double amount, String log) {
        BankAccountDTO accountDTO = bankAccountDao.getAccount(accountNumber);
        double newBalance = accountDTO.getBalance() + amount;
        bankAccountDao.saveAccount(accountNumber,newBalance,log);
        long timeStamp = timeSystem.getTimeInMillis();
        transactionDao.depositLog(accountNumber,amount,timeStamp,log);
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

    public static void withdraw(String accountNumber, double amount, String log) {
        BankAccountDTO accountDTO = bankAccountDao.getAccount(accountNumber);
        double newBalance = accountDTO.getBalance() - amount;
        bankAccountDao.saveAccount(accountNumber,newBalance,log);
    }
}

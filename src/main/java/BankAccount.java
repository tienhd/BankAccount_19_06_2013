/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static BankAccountDao bankAccountDao;

    public static void setBankAccountDao(BankAccountDao bankAccountDao) {
        BankAccount.bankAccountDao = bankAccountDao;
    }

    public static void openAccount(String accountNumber) {
        double balance = 0;
        String log = "Open new account";
        bankAccountDao.saveAccount(accountNumber,balance,log);
    }

    public static void getAccount(String accountNumber) {

    }
}

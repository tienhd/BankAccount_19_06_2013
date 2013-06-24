/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private String accountNumber;
    private double balance;
    private String log;
    private long timeStamp;

    public BankAccountDTO(String accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0;
        log = "Open new account";
    }

    public BankAccountDTO(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        log = "";
    }

    public BankAccountDTO(String accountNumber, double balance, String log) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.log = log;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getLog() {
        return log;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccountDTO that = (BankAccountDTO) o;

        if (Double.compare(that.balance, balance) != 0) return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
        if (log != null ? !log.equals(that.log) : that.log != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

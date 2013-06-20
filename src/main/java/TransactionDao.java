import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDao {

    public void depositLog(String accountNumber, Double amount, Long timeStamp, String log) {

    }

    public void withdrawLog(String accountNumber, Double amount, Long timeStamp, String log) {

    }

    public ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber) {
        return null;
    }

    public ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber, long startTime, long endTime) {
        return null;
    }

    public ArrayList<TransactionDTO> getTransactionOccurred(String accountNumber, int n) {
        return null;
    }
}

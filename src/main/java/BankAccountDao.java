import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/19/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDao {

    private Connection dbConnection;
    public BankAccountDao(DataSource dataSource) throws SQLException
    {
        this.dbConnection = dataSource.getConnection();
    }

    public void saveAccount(String accountNumber, Double balance, String log, Long timeStamp) throws SQLException{
        String description = log;
        String queryString = "INSERT INTO BANK_ACCOUNT " +
                "VALUES ('"+ accountNumber + "'," + balance + ",'" + description + "'," + timeStamp + ")";
        //System.out.println(queryString);
        dbConnection.createStatement().executeUpdate(queryString);
    }

    public BankAccountDTO getAccount(String accountNumber) throws SQLException{
        //return null;

        String queryString = "SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER = " + accountNumber;
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        if (resultSet.next())
        {
            return new BankAccountDTO(accountNumber,resultSet.getDouble("balance"),resultSet.getString("description"));
        }
        else
        {
            return null;
        }

    }
}

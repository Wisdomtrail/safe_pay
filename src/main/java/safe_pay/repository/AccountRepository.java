package safe_pay.repository;

import safe_pay.exceptions.DataBaseConnectionFailedException;
import safe_pay.exceptions.InvalidStatementException;
import safe_pay.models.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AccountRepository {
    private final Connection connection;

    public AccountRepository() {
        String url = "jdbc:mysql://localhost:3306/safe_pay?createDataBaseIfNotExist=true";
        String username = "root";
        String password = "Sunepa1234";

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

        } catch (SQLException e) {
            throw new DataBaseConnectionFailedException(e.getMessage());
        }
    }

    public Account save(Account account) {
        try {
            String sql = "INSERT INTO accounts (`id`, `name`, `balance`) VALUES (NULL, '" + account.getName() + "'," + account.getBalance() + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return account;
        } catch (SQLException e) {
            throw new InvalidStatementException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<Account> findById(int id){

        return Optional.empty();
    }

}

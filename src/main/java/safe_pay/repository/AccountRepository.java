package safe_pay.repository;

import safe_pay.exceptions.DataBaseConnectionFailedException;
import safe_pay.exceptions.InvalidIdException;
import safe_pay.exceptions.InvalidStatementException;
import safe_pay.models.Account;

import java.math.BigDecimal;
import java.sql.*;
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

    public Optional<Account> findById(int id) throws InvalidIdException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        Optional<Account> account = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                account = Optional.of(new Account(name, balance));
            }
        } catch (SQLException e) {
            throw new InvalidIdException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    public void deleteAccount(int id) throws InvalidIdException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new InvalidIdException("Account not found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new InvalidIdException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public int countAccountS(){
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

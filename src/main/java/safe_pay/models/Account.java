package safe_pay.models;

import java.math.BigDecimal;
import java.util.function.BiConsumer;

public class Account {

    private String name;

    private BigDecimal balance = BigDecimal.ZERO;

    private int id;

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

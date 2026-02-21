import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private double balance;
    private Map<Stock, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Map<Stock, Integer> getPortfolio() { return portfolio; }
}
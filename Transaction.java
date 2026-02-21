import java.time.LocalDateTime;

public class Transaction {
    private String type; // buy or sell
    private Stock stock;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(String type, Stock stock, int quantity, double price) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public String toString() {
        return timestamp + " | " + type.toUpperCase() + " | " +
               stock.getSymbol() + " | Qty: " + quantity + " | Price: $" + price;
    }
}
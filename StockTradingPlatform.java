import java.util.*;

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample stocks
        Stock apple = new Stock("AAPL", "Apple Inc.", 150.0);
        Stock tesla = new Stock("TSLA", "Tesla Inc.", 700.0);
        Stock amazon = new Stock("AMZN", "Amazon.com", 3300.0);
        List<Stock> market = Arrays.asList(apple, tesla, amazon);

        // User
        User user = new User("Dhivya", 10000.0);
        List<Transaction> history = new ArrayList<>();

        boolean running = true;
        while(running) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    System.out.println("\n--- Market ---");
                    for(Stock s : market) {
                        System.out.println(s.getSymbol() + " | " + s.getName() + " | $" + s.getPrice());
                    }
                    break;

                case 2: // Buy stock
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.nextLine();
                    Stock buyStock = null;
                    for(Stock s : market) {
                        if(s.getSymbol().equalsIgnoreCase(buySymbol)) buyStock = s;
                    }
                    if(buyStock == null) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    double cost = buyStock.getPrice() * buyQty;
                    if(cost > user.getBalance()) {
                        System.out.println("Insufficient balance!");
                        break;
                    }
                    user.setBalance(user.getBalance() - cost);
                    user.getPortfolio().put(buyStock, user.getPortfolio().getOrDefault(buyStock,0) + buyQty);
                    history.add(new Transaction("buy", buyStock, buyQty, buyStock.getPrice()));
                    System.out.println("Bought " + buyQty + " of " + buyStock.getSymbol());
                    break;

                case 3: // Sell stock
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.nextLine();
                    Stock sellStock = null;
                    for(Stock s : market) {
                        if(s.getSymbol().equalsIgnoreCase(sellSymbol)) sellStock = s;
                    }
                    if(sellStock == null || !user.getPortfolio().containsKey(sellStock)) {
                        System.out.println("You do not own this stock!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    int ownedQty = user.getPortfolio().get(sellStock);
                    if(sellQty > ownedQty) {
                        System.out.println("Not enough stock to sell!");
                        break;
                    }
                    user.setBalance(user.getBalance() + sellStock.getPrice() * sellQty);
                    if(sellQty == ownedQty) user.getPortfolio().remove(sellStock);
                    else user.getPortfolio().put(sellStock, ownedQty - sellQty);
                    history.add(new Transaction("sell", sellStock, sellQty, sellStock.getPrice()));
                    System.out.println("Sold " + sellQty + " of " + sellStock.getSymbol());
                    break;

                case 4: // Portfolio
                    System.out.println("\n--- Portfolio ---");
                    System.out.println("Balance: $" + user.getBalance());
                    if(user.getPortfolio().isEmpty()) System.out.println("No stocks owned.");
                    else {
                        for(Map.Entry<Stock, Integer> entry : user.getPortfolio().entrySet()) {
                            System.out.println(entry.getKey().getSymbol() + " | Qty: " + entry.getValue() + " | Price: $" + entry.getKey().getPrice());
                        }
                    }
                    break;

                case 5: // Transaction history
                    System.out.println("\n--- Transaction History ---");
                    if(history.isEmpty()) System.out.println("No transactions yet.");
                    else {
                        for(Transaction t : history) System.out.println(t);
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}
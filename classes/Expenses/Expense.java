package classes.Expenses;

public class Expense {

    // Class for expense object.
    public int amount;
    public String name;
    public String[] category;

    public Expense(int amount, String name, String[] category) {
        this.amount = amount;
        this.name = name;
        this.category = category;
    }
}

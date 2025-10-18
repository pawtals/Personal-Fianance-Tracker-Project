package classes.Expenses;

class Expense {

    // Class for expense object.
    int amount;
    String name;
    String[] category;

    Expense(int amount, String name, String[] category) {
        this.amount = amount;
        this.name = name;
        this.category = category;
    }
}

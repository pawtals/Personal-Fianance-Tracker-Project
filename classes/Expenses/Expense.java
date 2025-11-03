package classes.Expenses;

import java.time.*;

public class Expense {

    // Class for expense object.
    public int amount;
    public String name;
    public String category;
    public LocalDate date;

    public Expense(int amount, String name, String category, LocalDate date) {
        this.amount = amount;
        this.name = name;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        return (
            ". [" +
            this.amount +
            "$ - " +
            this.name +
            " - " +
            this.category +
            "] On: " +
            this.date.toString()
        );
    }
}

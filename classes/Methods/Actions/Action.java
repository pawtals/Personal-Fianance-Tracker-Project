package classes.Methods.Actions;

import classes.Expenses.Expense;

public class Action {

    public Expense previous;
    public String type;
    public int index; // Store the index for edit/delete operations

    public Action(Expense pExpense, String type) {
        this.previous = pExpense;
        this.type = type;
        this.index = -1; // Default for ADD operations
    }

    public Action(Expense pExpense, String type, int index) {
        this.previous = pExpense;
        this.type = type;
        this.index = index;
    }
}

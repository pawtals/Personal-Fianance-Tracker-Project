package classes.Expenses;

import java.time.*;
import java.util.LinkedList;

public class ExpenseManager {

    public LinkedList<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new LinkedList<>();
    }

    public void NewExpense(String name, int amount, String categories) {
        Expense expense = new Expense(
            amount,
            name,
            categories,
            LocalDate.now()
        );
        this.expenses.addLast(expense);
    }

    public void EditExpense(int index, Expense newExpense) {
        this.expenses.set(index, newExpense);
    }

    public void DeleteExpense(int index) {
        this.expenses.remove(index);
    }
}

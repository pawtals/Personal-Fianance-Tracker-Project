package classes.Expenses;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    public List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void NewExpense(String name, int amount, String[] categories) {
        Expense expense = new Expense(amount, name, categories);
        this.expenses.addLast(expense);
    }

    public void EditExpense(int index, Expense newExpense) {
        this.expenses.set(index, newExpense);
    }

    public void DeleteExpense(int index) {
        this.expenses.remove(index);
    }
}

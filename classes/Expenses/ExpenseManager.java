package classes.Expenses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseManager {

    public List<Expense> expenses;

    public ExpenseManager(Expense[] loadstate) {
        this.expenses = new ArrayList<>(Arrays.asList(loadstate));
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

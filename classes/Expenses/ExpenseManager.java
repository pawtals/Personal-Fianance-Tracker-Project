package classes.Expenses;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void NewExpense(String name, int amount, String[] categories) {
        Expense expense = new Expense(amount, name, categories);
        this.expenses.addLast(expense);
        System.out.println(this.expenses);
    }
}

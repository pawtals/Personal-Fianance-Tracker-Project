package classes.Expenses;

import classes.Methods.Actions.ActionStore;
import java.time.*;
import java.util.LinkedList;

public class ExpenseManager {

    public LinkedList<Expense> expenses;
    private ActionStore actionStore;

    public ExpenseManager() {
        this.expenses = new LinkedList<>();
        this.actionStore = new ActionStore(this);
    }

    public void NewExpense(String name, int amount, String categories) {
        Expense expense = new Expense(
            amount,
            name,
            categories,
            LocalDate.now()
        );
        this.expenses.addLast(expense);

        // Record the add action for undo
        actionStore.recordAdd();
    }

    public void EditExpense(int index, Expense newExpense) {
        // Save the old expense before editing
        if (index >= 0 && index < expenses.size()) {
            Expense oldExpense = expenses.get(index);
            actionStore.recordEdit(index, oldExpense);
            this.expenses.set(index, newExpense);
        }
    }

    public void DeleteExpense(int index) {
        // Save the expense before deleting
        if (index >= 0 && index < expenses.size()) {
            Expense deletedExpense = expenses.get(index);
            actionStore.recordDelete(index, deletedExpense);
            this.expenses.remove(index);
        }
    }

    // Undo the last action
    public boolean undo() {
        return actionStore.undo();
    }

    // Check if undo is available
    public boolean canUndo() {
        return actionStore.canUndo();
    }

    // Clear undo history
    public void clearUndoHistory() {
        actionStore.clearHistory();
    }

    // Get the number of undoable actions
    public int getUndoHistorySize() {
        return actionStore.getHistorySize();
    }

    // Get all expenses
    public LinkedList<Expense> getAllExpenses() {
        return expenses;
    }

    // Get expense by index
    public Expense getExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            return expenses.get(index);
        }
        return null;
    }

    // Get total number of expenses
    public int getExpenseCount() {
        return expenses.size();
    }
}

package classes.Methods.Actions;

import classes.Expenses.Expense;
import classes.Expenses.ExpenseManager;
/*
 * Stack data with old previous types, undo removes action & restores data.
 */
import java.util.Stack;

public class ActionStore {

    private Stack<Action> actions;
    private ExpenseManager expenseManager;

    public ActionStore(ExpenseManager expenseManager) {
        this.actions = new Stack<Action>();
        this.expenseManager = expenseManager;
    }

    // Record an ADD action
    public void recordAdd() {
        // For ADD, we just need to know it was added (last item)
        int index = expenseManager.expenses.size() - 1;
        Expense addedExpense = expenseManager.expenses.get(index);
        actions.push(new Action(addedExpense, "ADD", index));
    }

    // Record an EDIT action with the old expense data and its index
    public void recordEdit(int index, Expense oldExpense) {
        actions.push(new Action(oldExpense, "EDIT", index));
    }

    // Record a DELETE action with the deleted expense and its index
    public void recordDelete(int index, Expense deletedExpense) {
        actions.push(new Action(deletedExpense, "DELETE", index));
    }

    // Undo the last action
    public boolean undo() {
        if (actions.isEmpty()) {
            return false;
        }

        Action lastAction = actions.pop();

        switch (lastAction.type) {
            case "ADD":
                // Remove the last added expense
                if (!expenseManager.expenses.isEmpty()) {
                    expenseManager.expenses.removeLast();
                }
                break;
            case "EDIT":
                // Restore the old version at the same index
                if (
                    lastAction.index >= 0 &&
                    lastAction.index < expenseManager.expenses.size()
                ) {
                    expenseManager.expenses.set(
                        lastAction.index,
                        lastAction.previous
                    );
                }
                break;
            case "DELETE":
                // Re-insert the deleted expense at its original position
                if (
                    lastAction.index >= 0 &&
                    lastAction.index <= expenseManager.expenses.size()
                ) {
                    expenseManager.expenses.add(
                        lastAction.index,
                        lastAction.previous
                    );
                } else {
                    // If index is out of bounds, add to the end
                    expenseManager.expenses.add(lastAction.previous);
                }
                break;
            default:
                return false;
        }

        return true;
    }

    // Check if undo is available
    public boolean canUndo() {
        return !actions.isEmpty();
    }

    // Clear all stored actions
    public void clearHistory() {
        actions.clear();
    }

    // Get the number of actions that can be undone
    public int getHistorySize() {
        return actions.size();
    }

    // Peek at the last action without removing it
    public String getLastActionType() {
        if (actions.isEmpty()) {
            return null;
        }
        return actions.peek().type;
    }
}

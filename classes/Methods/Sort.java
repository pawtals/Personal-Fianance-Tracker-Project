package classes.Methods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import classes.Expenses.*;

public class Sort {
    private ExpenseManager linkedManager;
    private HashMap<String, ArrayList<Integer>> categoryMap;    
    public Sort(ExpenseManager manager) {
    }
    
    public void UpdateEntries() {
        LinkedList<Expense> newExpenses = this.linkedManager.expenses;
        this.categoryMap.clear();
        for(int i = 0; i < newExpenses.size(); i++) {
            if (this.categoryMap.containsKey(newExpenses.get(i).category)) {
                ArrayList<Integer> previous = this.categoryMap.get(newExpenses.get(i).category);
                previous.addLast(i);
                this.categoryMap.put(newExpenses.get(i).category, previous);
            }
        }
    }
}

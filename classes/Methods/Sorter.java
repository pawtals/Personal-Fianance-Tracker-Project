package classes.Methods;

import classes.Expenses.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Sorter {

    private ExpenseManager linkedManager;
    private HashMap<String, ArrayList<Integer>> categoryMap;

    public Sorter(ExpenseManager manager) {
        this.linkedManager = manager;
        UpdateEntries();
    }

    public Hashmap<String, ArrayList<Integer>> UpdateEntries() {
        LinkedList<Expense> newExpenses = this.linkedManager.expenses;
        this.categoryMap.clear();
        for (int i = 0; i < newExpenses.size(); i++) {
            if (this.categoryMap.containsKey(newExpenses.get(i).category)) {
                ArrayList<Integer> previous = this.categoryMap.get(
                    newExpenses.get(i).category
                );
                previous.addLast(i);
                this.categoryMap.put(newExpenses.get(i).category, previous);
            }
        }
        return this.category;
    }

    /*
     * Update to return LinkedList or other
     * Use meh sorting algo
     */
    public void Sort() {}
}

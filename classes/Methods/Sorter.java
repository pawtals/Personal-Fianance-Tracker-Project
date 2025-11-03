package classes.Methods;

import classes.Expenses.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Sorter {

    private ExpenseManager linkedManager;
    private HashMap<String, ArrayList<Integer>> categoryMap;

    public Sorter(ExpenseManager manager) {
        this.linkedManager = manager;
        this.categoryMap = new HashMap<>();
        UpdateEntries();
    }

    public HashMap<String, ArrayList<Integer>> UpdateEntries() {
        LinkedList<Expense> newExpenses = this.linkedManager.expenses;
        this.categoryMap.clear();
        for (int i = 0; i < newExpenses.size(); i++) {
            String category = newExpenses.get(i).category;
            if (this.categoryMap.containsKey(newExpenses.get(i).category)) {
                ArrayList<Integer> previous = this.categoryMap.get(
                    newExpenses.get(i).category
                );
                previous.addLast(i);
                this.categoryMap.put(newExpenses.get(i).category, previous);
            } else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                this.categoryMap.put(category, newList);
            }
        }
        return this.categoryMap;
    }

    public LinkedHashMap<String, ArrayList<Integer>> SortByCategory() {
        UpdateEntries();
        LinkedHashMap<String, ArrayList<Integer>> sorted =
            new LinkedHashMap<>();
        this.categoryMap.entrySet()
            .stream()
            .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
            .forEach(entry -> sorted.put(entry.getKey(), entry.getValue()));

        return sorted;
    }

    public LinkedList<Expense> SortByAmount() {
        LinkedList<Expense> sorted = this.linkedManager.expenses;
        sorted.sort((e1, e2) -> Double.compare(e1.amount, e2.amount));
        return sorted;
    }
}

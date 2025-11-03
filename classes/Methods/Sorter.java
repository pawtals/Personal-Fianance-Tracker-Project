package classes.Methods;

import classes.Expenses.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sorter {

    private ExpenseManager linkedManager;
    private HashMap<String, ArrayList<Integer>> categoryMap;
    private DateTreeNode dateTree;

    public Sorter(ExpenseManager manager) {
        this.linkedManager = manager;
        this.categoryMap = new HashMap<>();
        this.dateTree = new DateTreeNode("Root", DateTreeNode.NodeType.YEAR);
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
        BuildDateTree();
        return this.categoryMap;
    }

    private void BuildDateTree() {
        this.dateTree = new DateTreeNode("Root", DateTreeNode.NodeType.YEAR);
        LinkedList<Expense> expenses = this.linkedManager.expenses;

        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            LocalDate date = expense.date; // Assumes Expense has a LocalDate field

            // Extract year, month, day
            String year = String.valueOf(date.getYear());
            String month = date.getMonth().toString() + " " + date.getYear();
            String day = date.format(
                DateTimeFormatter.ofPattern("MMM dd, yyyy")
            );

            // Navigate/create the tree path: Root -> Year -> Month -> Day
            DateTreeNode yearNode = dateTree.getOrCreateChild(
                year,
                DateTreeNode.NodeType.YEAR
            );
            DateTreeNode monthNode = yearNode.getOrCreateChild(
                month,
                DateTreeNode.NodeType.MONTH
            );
            DateTreeNode dayNode = monthNode.getOrCreateChild(
                day,
                DateTreeNode.NodeType.DAY
            );

            // Add expense index to the day node
            dayNode.addExpenseIndex(i);
        }
    }

    public DateTreeNode GetDateTree() {
        return this.dateTree;
    }

    public ArrayList<Integer> GetExpensesByYear(int year) {
        DateTreeNode yearNode = dateTree
            .getChildren()
            .get(String.valueOf(year));
        if (yearNode == null) {
            return new ArrayList<>();
        }
        return yearNode.getAllExpenseIndices();
    }

    public ArrayList<Integer> GetExpensesByMonth(int year, Month month) {
        String yearKey = String.valueOf(year);
        String monthKey = month.toString() + " " + year;

        DateTreeNode yearNode = dateTree.getChildren().get(yearKey);
        if (yearNode == null) {
            return new ArrayList<>();
        }

        DateTreeNode monthNode = yearNode.getChildren().get(monthKey);
        if (monthNode == null) {
            return new ArrayList<>();
        }

        return monthNode.getAllExpenseIndices();
    }

    public ArrayList<Integer> GetExpensesByDay(LocalDate date) {
        String year = String.valueOf(date.getYear());
        String month = date.getMonth().toString() + " " + date.getYear();
        String day = date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));

        DateTreeNode yearNode = dateTree.getChildren().get(year);
        if (yearNode == null) return new ArrayList<>();

        DateTreeNode monthNode = yearNode.getChildren().get(month);
        if (monthNode == null) return new ArrayList<>();

        DateTreeNode dayNode = monthNode.getChildren().get(day);
        if (dayNode == null) return new ArrayList<>();

        return dayNode.getExpenseIndices();
    }

    public LinkedList<Expense> SortByDate() {
        LinkedList<Expense> sorted = new LinkedList<>(
            this.linkedManager.expenses
        );
        sorted.sort(Comparator.comparing(e -> e.date));
        return sorted;
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

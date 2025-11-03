package classes;

import classes.Expenses.*;
import classes.Methods.Sorter;
import classes.Storage.StorageLoader;
import java.time.*;
import java.util.*;
import java.util.Scanner;

public class CLI {

    private ExpenseManager exm;
    private Scanner scanner;
    private StorageLoader store;
    private Sorter sorter;

    public CLI() {
        // initalization
        print("Personal Fianance Tracker");
        this.store = new StorageLoader();
        try {
            this.exm = store.loadExpenses();
        } catch (Exception e) {
            System.exit(0);
        }
        this.scanner = new Scanner(System.in);
        this.sorter = new Sorter(this.exm);

        // command handling past here
        while (true) {
            print(
                "Enter command [add, del, edt] [list, save] [sort:amount, sort:category] [sort:date:day, sort:date:month, sort:date:year]"
            );
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "add":
                    print("enter amount");
                    int amt = scanner.nextInt();
                    scanner.nextLine();
                    print("name of expense");
                    String nme = scanner.nextLine();
                    print("category of transaction");
                    String cat = scanner.nextLine();
                    this.exm.NewExpense(nme, amt, cat);
                    break;
                case "edt":
                    print("index of expense");
                    int eidx = scanner.nextInt();
                    scanner.nextLine();
                    print("enter amount");
                    int eamt = scanner.nextInt();
                    scanner.nextLine();
                    print("name of expense");
                    String enme = scanner.nextLine();
                    print("category of transaction");
                    String ecat = scanner.nextLine();
                    Expense ex = new Expense(eamt, enme, ecat, LocalDate.now());
                    this.exm.EditExpense(eidx, ex);
                    break;
                case "del":
                    boolean doneSignal = false;
                    List<Integer> indexes = new ArrayList<>();
                    while (!doneSignal) {
                        print(
                            "Add index of transaction to be removed | Enter -1 to exit"
                        );
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if (index == -1) {
                            if (indexes.size() > 0) {
                                indexes.sort(Collections.reverseOrder());
                                for (int i = 0; i < indexes.size(); i++) {
                                    exm.DeleteExpense(indexes.get(i));
                                }
                            }
                            doneSignal = true;
                        } else {
                            indexes.add(index);
                        }
                    }
                    break;
                case "list":
                    LinkedList<Expense> expenses = exm.expenses;
                    for (int i = 0; i < expenses.size(); i++) {
                        Expense exp = expenses.get(i);
                        print(i + ". " + exp);
                    }
                    break;
                case "save":
                    try {
                        store.saveExpenses(exm.expenses);
                    } catch (Exception e) {
                        print(e.toString());
                    }
                    break;
                case "sort:amount":
                    LinkedList<Expense> sortedByAmount = sorter.SortByAmount();
                    for (int i = 0; i < sortedByAmount.size(); i++) {
                        Expense exp = sortedByAmount.get(i);
                        print(i + ". " + exp);
                    }
                    break;
                case "sort:category":
                    LinkedHashMap<String, ArrayList<Integer>> sortedByCategory =
                        sorter.SortByCategory();
                    for (String category : sortedByCategory.keySet()) {
                        print("\n" + category + ":");
                        ArrayList<Integer> indices = sortedByCategory.get(
                            category
                        );
                        for (int index : indices) {
                            Expense exp = exm.expenses.get(index);
                            print("  - " + exp);
                        }
                    }
                    break;
                case "sort:date:day":
                    print("enter date [YYYY-MM-DD]");
                    String dateStr = scanner.nextLine();
                    try {
                        LocalDate searchDate = LocalDate.parse(dateStr);
                        ArrayList<Integer> dayIndices = sorter.GetExpensesByDay(
                            searchDate
                        );

                        if (dayIndices.isEmpty()) {
                            print("no expenses on" + searchDate);
                        } else {
                            for (int index : dayIndices) {
                                Expense exp = exm.expenses.get(index);
                                print("  " + index + ". " + exp);
                            }
                            print("found " + dayIndices.size() + " expenses");
                        }
                    } catch (Exception e) {
                        print(
                            "invalid string, string couldn't be parsed to date"
                        );
                    }
                    break;
                case "sort:date:month":
                    print("enter year [XXXX]:");
                    int monthYear = scanner.nextInt();
                    scanner.nextLine();
                    print("enter month  [1-12]:");
                    int monthNum = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Month searchMonth = Month.of(monthNum);
                        ArrayList<Integer> monthIndices =
                            sorter.GetExpensesByMonth(monthYear, searchMonth);

                        if (monthIndices.isEmpty()) {
                            print(
                                "no expenses on " +
                                    searchMonth +
                                    "-" +
                                    monthYear
                            );
                        } else {
                            print(
                                "\nexpenses in " + searchMonth + " " + monthYear
                            );
                            for (int index : monthIndices) {
                                Expense exp = exm.expenses.get(index);
                                print("  " + index + ". " + exp);
                            }
                            print("found " + monthIndices.size() + " expenses");
                        }
                    } catch (Exception e) {
                        print("invalid month or year given");
                    }

                    break;
                case "sort:date:year":
                    print("enter year [XXXX]:");
                    int searchYear = scanner.nextInt();
                    scanner.nextLine();

                    ArrayList<Integer> yearIndices = sorter.GetExpensesByYear(
                        searchYear
                    );

                    if (yearIndices.isEmpty()) {
                        print("no expenses in " + searchYear);
                    } else {
                        print("\nexpenses in " + searchYear + "");
                        for (int index : yearIndices) {
                            Expense exp = exm.expenses.get(index);
                            print("  " + index + ". " + exp);
                        }
                        print("found " + yearIndices.size() + " expenses");
                    }
                    break;
            }
        }
    }

    private void print(String str) {
        System.out.println(str);
    }
}

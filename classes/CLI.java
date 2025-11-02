package classes;

import classes.Expenses.*;
import classes.Storage.StorageLoader;
import java.util.*;
import java.util.Scanner;

public class CLI {

    private ExpenseManager exm;
    private Scanner scanner;
    private StorageLoader store;

    public CLI() {
        print("Personal Fianance Tracker");
        this.store = new StorageLoader();
        try {
            this.exm = store.loadExpenses();
        } catch (Exception e) {
            System.exit(0);
        }
        this.scanner = new Scanner(System.in);
        while (true) {
            print("Enter command [add, del, edt] [list, save]");
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
                        print(
                            i +
                                ". [" +
                                exp.amount +
                                "$ - " +
                                exp.name +
                                " - " +
                                exp.category +
                                "] On: " +
                                exp.date.toString()
                        );
                    }
                    break;
                case "save":
                    try {
                        store.saveExpenses(exm.expenses);
                    } catch (Exception e) {
                        print(e.toString());
                    }
                    break;
            }
        }
    }

    private void print(String str) {
        System.out.println(str);
    }
}

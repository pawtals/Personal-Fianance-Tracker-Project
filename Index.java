import classes.Expenses.ExpenseManager;
import classes.UI;
import java.awt.*;
import javax.swing.*;

class Index {

    public static void main(String[] args) {
        UI ui = new UI();
<<<<<<< HEAD
        ui.createLabel("Personal Finance Tracker", new Dimension(400, 30));
        ui.createButton(
            "Add Transaction",
            new Dimension(400, 30),
            (e -> System.out.println("Button clicked"))
=======
        ExpenseManager exm = new ExpenseManager();
        ui.createButton(
            "Add Transaction",
            new Dimension(100, 30),
            (e -> exm.NewExpense("hi", 10, new String[] { "a" }))
>>>>>>> 081552bde75e8e280591aa557bc9ab9602253a35
        );
        ui.createButton(
            "testNewButton",
            new Dimension(400, 30),
            (e -> System.out.println("it works"))
        );
    }
}

import classes.Expenses.ExpenseManager;
import classes.UI;
import java.awt.*;
import javax.swing.*;

class Index {

    public static void main(String[] args) {
        UI ui = new UI();
        ExpenseManager exm = new ExpenseManager();
        ui.createButton(
            "Add Transaction",
            new Dimension(100, 30),
            (e -> exm.NewExpense("hi", 10, new String[] { "a" }))
        );
    }
}

import classes.Expenses.ExpenseManager;
import classes.UI;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

class Index {

    public static void main(String[] args) {
        UI ui = new UI();
        ui.createLabel("Side", new Dimension(200, 30), ui.sidePanel);
        ui.createLabel("Search", new Dimension(200, 30), ui.searchPanel);
        ui.createLabel("Top", new Dimension(200, 30), ui.topPanel);
    }
}

import classes.UI;
import java.awt.*;
import javax.swing.*;

class Index {

    public static void main(String[] args) {
        UI ui = new UI();
        ui.createLabel("Personal Finance Tracker", new Dimension(400, 30));
        ui.createButton(
            "Add Transaction",
            new Dimension(400, 30),
            (e -> System.out.println("Button clicked"))
        );
        ui.createButton(
            "testNewButton",
            new Dimension(400, 30),
            (e -> System.out.println("it works"))
        );
    }
}

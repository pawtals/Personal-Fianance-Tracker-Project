import classes.UI;
import java.awt.*;
import javax.swing.*;

class Index {

    public static void main(String[] args) {
        UI ui = new UI();
        ui.createButton(
            "Add Transaction",
            new Dimension(100, 30),
            (e -> System.out.println("Button clicked"))
        );
    }
}

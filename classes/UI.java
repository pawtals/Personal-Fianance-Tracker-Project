package classes;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UI {

    private JFrame frame;
    private JButton button;
    private JLabel label;

    public UI() {
        frame = new JFrame("Personal Finance Tracker");
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new FlowLayout());
    }

    public void createButton(
        String text,
        Dimension scale,
        ActionListener action
    ) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        button.setPreferredSize(scale);
        frame.add(button);
        frame.pack();
    }

    public void createLabel(String text, Dimension scale) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(scale);
        frame.add(label);
        frame.pack();
    }
}

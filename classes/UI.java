package classes;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UI {

    public JFrame frame;
    private JButton button;
    private JLabel label;
    private JTextField textField;
    private JScrollPane scrollPane;
    public JPanel sidePanel, topPanel, searchPanel;

    public UI() {
        frame = new JFrame("Personal Finance Tracker");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.CENTER);

        sidePanel = new JPanel();
        frame.add(sidePanel, BorderLayout.WEST);

        searchPanel = new JPanel();
        frame.add(searchPanel, BorderLayout.CENTER);
    }

    public void createButton(
        String text,
        Dimension scale,
        ActionListener action,
        JPanel panel
    ) {
        button = new JButton(text);
        button.addActionListener(action);
        button.setPreferredSize(scale);
        panel.add(button);
    }

    public void createLabel(String text, Dimension scale, JPanel panel) {
        label = new JLabel(text);
        label.setPreferredSize(scale);
        panel.add(label);
    }
}

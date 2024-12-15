package dev.ak.qtcp.view;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AboutView extends JFrame {

    private static int pad = 5;

    public AboutView() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setBorder(new EmptyBorder(pad, pad, pad, pad));

        JTextArea textArea = new JTextArea("This is a small help text explaining what the origin and use of this application. It has yet to be written.");
        textArea.setSize(new Dimension(230, 50));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFont(new JLabel().getFont());
        panel.add(textArea);

        setTitle("About Quick TCP");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(250, 250);
        setVisible(true);
    }
}

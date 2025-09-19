package dev.ak.qtcp.view;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dev.ak.qtcp.core.*;

public class AboutView extends BaseFrame {

    private static int pad = 5;

    public AboutView() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setBorder(new EmptyBorder(pad, pad, pad, pad));

        JTextArea textArea = new JTextArea(String.join(" ",
            "Quick TCP is a small tool to easily setup TCP clients and servers, and send messages between them. It",
            "served a very specific use case, so there is nothing much to it and will probably never do much else."));

        textArea.setSize(new Dimension(230, 50));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFont(new JLabel().getFont());
        panel.add(textArea);

        int r = 96;
        Image iconImage = Assets.getIcon(r);
        ImageCanvas icon = new ImageCanvas(iconImage);
        icon.setSize(r, r);
        panel.add(icon);

        setTitle("About Quick TCP");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(250, 250);
        setVisible(true);
    }
}

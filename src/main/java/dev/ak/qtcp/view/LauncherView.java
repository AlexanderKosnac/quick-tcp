package dev.ak.qtcp.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import dev.ak.qtcp.viewModel.LauncherViewModel;

public class LauncherView extends JFrame {

    private static int pad = 5;

    public LauncherView(LauncherViewModel viewModel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem helpAbout = new JMenuItem("About");
        
        menuBar.add(help);
        help.add(helpAbout);

        helpAbout.addActionListener(e -> viewModel.HelpAboutCommand(e));
    
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridLayout(2, 1, pad, pad));
        add(panel);
        panel.setBorder(new EmptyBorder(pad, pad, pad, pad));

        panel.add(new JLabel("Create new server and client instances from this window."));

        JPanel startButtons = new JPanel(new GridLayout(1, 2, pad, pad));
        panel.add(startButtons);

        JButton createServer = new JButton("New Server");
        startButtons.add(createServer);
        createServer.addActionListener(e -> viewModel.CreateServerCommand(e));

        JButton createClient = new JButton("New Client");
        startButtons.add(createClient);
        createClient.addActionListener(e -> viewModel.CreateClientCommand(e));

        setTitle("Quick TCP Launcher");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}

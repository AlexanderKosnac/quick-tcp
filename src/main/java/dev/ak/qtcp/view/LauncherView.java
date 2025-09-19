package dev.ak.qtcp.view;

import java.net.URL;

import java.awt.*;

import javax.swing.*;

import dev.ak.qtcp.viewModel.LauncherViewModel;

public class LauncherView extends BaseFrame {

    private static int pad = 5;

    public LauncherView(LauncherViewModel vm) {
        setTitle("Quick TCP Launcher");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem helpAbout = new JMenuItem("About");

        menuBar.add(help);
        help.add(helpAbout);
        helpAbout.addActionListener(e -> vm.helpAboutCommand(e));
        setJMenuBar(menuBar);

        // Body
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(pad, pad, pad, pad);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Help Text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Spans two columns
        String helpText = "Create new server and client instances from this window.";
        contentPane.add(new JLabel(helpText), gbc);

        // Server Button
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1; // Reset gridwidth
        JButton createServer = new JButton("New Server");
        createServer.addActionListener(e -> vm.createServerCommand(e));
        contentPane.add(createServer, gbc);

        // Client Button
        gbc.gridx = 1;
        JButton createClient = new JButton("New Client");
        createClient.addActionListener(e -> vm.createClientCommand(e));
        contentPane.add(createClient, gbc);

        setVisible(true);
    }
}

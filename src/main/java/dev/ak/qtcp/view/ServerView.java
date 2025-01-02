package dev.ak.qtcp.view;

import java.awt.*;

import javax.swing.*;

import dev.ak.qtcp.viewModel.ServerViewModel;

public class ServerView extends JFrame {

    private static int number = 1;

    private static int pad = 5;

    private boolean isServerOpen = false;

    public ServerView(ServerViewModel vm) {
        setTitle(String.format("Quick TCP Server #%d", number++));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(pad, pad, pad, pad);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // IP
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        contentPane.add(new JLabel("IP:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField ipInput = new JTextField("127.0.0.1");
        ipInput.setEditable(false);
        contentPane.add(ipInput, gbc);

        // Port
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        contentPane.add(new JLabel("Port:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0;
        JTextField portInput = new JTextField("9999");
        contentPane.add(portInput, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.2;
        JButton start = new JButton("Start");
        start.addActionListener(e -> {
            if (isServerOpen) {
                vm.CloseServerCommand(e);
                start.setText("Start");
            } else {
                vm.ipInput = ipInput.getText();
                vm.portInput = portInput.getText();
                vm.OpenServerCommand(e);
                start.setText("Stop");
            }
            isServerOpen = !isServerOpen;
        });
        contentPane.add(start, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        contentPane.add(new JLabel("Message Log:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea messageLog = new JTextArea();
        messageLog.setEnabled(false);
        messageLog.setRows(10);
        vm.onSystemMessage = msg -> messageLog.append(msg + "\n");
        JScrollPane logPane = new JScrollPane(messageLog);
        contentPane.add(logPane, gbc);

        setVisible(true);
    }
}

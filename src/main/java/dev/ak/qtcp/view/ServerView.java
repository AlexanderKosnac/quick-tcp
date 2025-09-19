package dev.ak.qtcp.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import dev.ak.qtcp.viewModel.ServerViewModel;

public class ServerView extends BaseFrame {

    private static int number = 1;
    private static int pad = 5;
    private boolean isServerOpen = false;

    public ServerView(ServerViewModel vm) {
        setTitle(String.format("Quick TCP Server #%d", number++));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 300);

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
                vm.closeServerCommand(e);
                start.setText("Start");
            } else {
                vm.ipInput = ipInput.getText();
                vm.portInput = portInput.getText();
                vm.openServerCommand(e);
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
        messageLog.setDisabledTextColor(Color.BLACK);
        messageLog.setEnabled(false);
        messageLog.setRows(10);
        vm.onSystemMessage = msg -> messageLog.append(msg + "\n");
        JScrollPane logPane = new JScrollPane(messageLog);
        contentPane.add(logPane, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isServerOpen) {
                    vm.closeServerCommand(null);
                }
                dispose();
            }
        });

        setVisible(true);
    }
}

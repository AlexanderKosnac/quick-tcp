package dev.ak.qtcp.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import dev.ak.qtcp.core.InputFormat;
import dev.ak.qtcp.viewModel.ClientViewModel;

public class ClientView extends JFrame {

    private static int number = 1;
    private static int pad = 5;
    private boolean isClientConnected = false;

    public ClientView(ClientViewModel vm) {
        setTitle(String.format("Quick TCP Client #%d", number++));
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
        JButton connect = new JButton("Connect");
        connect.addActionListener(e -> {
            if (isClientConnected) {
                vm.DisconnectFromServerCommand(e);
                connect.setText("Connect");
            } else {
                vm.ipInput = ipInput.getText();
                vm.portInput = portInput.getText();
                vm.ConnectToServerCommand(e);
                connect.setText("Disconnect");
            }
            isClientConnected = !isClientConnected;
        });
        contentPane.add(connect, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        JTextField messageInput = new JTextField();
        contentPane.add(messageInput, gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 1;
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            vm.messageInput = messageInput.getText();
            vm.SendMessageCommand(e);
        });
        contentPane.add(sendButton, gbc);

        JRadioButton asAsciiInput = new JRadioButton("As ASCII");
        asAsciiInput.addActionListener(e -> {
            vm.SetInputFormat(InputFormat.ASCII);
        });
        asAsciiInput.setSelected(true);

        JRadioButton asHexInput = new JRadioButton("As Hex");
        asHexInput.addActionListener(e -> {
            vm.SetInputFormat(InputFormat.HEX);
        });

        ButtonGroup group = new ButtonGroup();
        group.add(asAsciiInput);
        group.add(asHexInput);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(asAsciiInput);
        panel.add(asHexInput);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        contentPane.add(panel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        contentPane.add(new JLabel("Message Log:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isClientConnected) {
                    vm.DisconnectFromServerCommand(null);
                }
                dispose();
            }
        });

        setVisible(true);
    }
}

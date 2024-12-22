package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.ak.qtcp.core.TcpClient;

public class ClientViewModel {

    public String ipInput;
    public String portInput;
    public String messageInput;

    private TcpClient client;
    private ExecutorService executor;

    public ClientViewModel() {
        client = new TcpClient();
        client.onSystemMessage = msg -> {
            System.out.println("[Client] " + msg);
        };

        executor = Executors.newSingleThreadExecutor();
    }

    public void ConnectToServerCommand(ActionEvent e) {
        executor.submit(() -> {
            client.connect(ipInput, getStringAsIntOrZero(portInput));
        });
    }

    public void SendMessageCommand(ActionEvent e) {
        client.send(messageInput);
    }

    public static int getStringAsIntOrZero(String input) {
        return getStringAsIntOrDefault(input, 0);
    }

    public static int getStringAsIntOrDefault(String input, int defaultVal) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}

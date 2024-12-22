package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import dev.ak.qtcp.core.TcpServer;

public class ServerViewModel {

    public String ipInput;
    public String portInput;
    public Consumer<String> onSystemMessage;

    public void OpenServerCommand(ActionEvent e) {
        TcpServer server = new TcpServer();
        server.onSystemMessage = onSystemMessage;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            server.open(getStringAsIntOrZero(portInput));
        });
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

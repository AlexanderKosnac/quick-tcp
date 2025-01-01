package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import dev.ak.qtcp.core.TcpClient;
import dev.ak.qtcp.core.Util;

public class ClientViewModel {

    public Consumer<String> onSystemMessage = (e) -> {};

    public String ipInput;
    public String portInput;
    public String messageInput;

    private TcpClient client;
    private ExecutorService executor;

    public void ConnectToServerCommand(ActionEvent e) {
        client = new TcpClient();
        client.onSystemMessage = onSystemMessage;

        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            client.connect(ipInput, Util.getStringAsIntOrZero(portInput));
        });
    }

    public void SendMessageCommand(ActionEvent e) {
        client.send(messageInput);
    }
}

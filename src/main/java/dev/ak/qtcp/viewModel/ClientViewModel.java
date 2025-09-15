package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import dev.ak.qtcp.core.InputFormat;
import dev.ak.qtcp.core.TcpClient;
import dev.ak.qtcp.core.Util;

public class ClientViewModel {

    public Consumer<String> onSystemMessage = (e) -> {};

    public String ipInput;
    public String portInput;

    public String messageInput;
    public InputFormat formatInput = InputFormat.ASCII;

    private TcpClient client;
    private ExecutorService executor;

    public void connectToServerCommand(ActionEvent e) {
        client = new TcpClient();
        client.onSystemMessage = onSystemMessage;

        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            client.connect(ipInput, Util.getStringAsIntOrZero(portInput));
        });
    }

    public void disconnectFromServerCommand(ActionEvent e) {
        if (client == null)
            return;
        client.disconnect();
        executor.shutdown();
        onSystemMessage.accept("Client has been disconnected.");
    }

    public void sendMessageCommand(ActionEvent e) {
        switch (formatInput) {
            case ASCII:
                client.send(messageInput);
                break;
            case HEX:
                byte[] bytes = Util.getHexStringAsByteArray(messageInput);
                String ascii = new String(bytes, StandardCharsets.US_ASCII);
                client.send(ascii);
                break;
            default:
                throw new IllegalStateException("Format Input: " + formatInput);
        }
    }

    public void setInputFormat(InputFormat format) {
        formatInput = format;
    }
}

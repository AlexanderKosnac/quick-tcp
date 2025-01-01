package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import dev.ak.qtcp.core.TcpServer;
import dev.ak.qtcp.core.Util;

public class ServerViewModel {

    public Consumer<String> onSystemMessage = (e) -> {};

    public String ipInput;
    public String portInput;

    public void OpenServerCommand(ActionEvent e) {
        TcpServer server = new TcpServer();
        server.onSystemMessage = onSystemMessage;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            server.open(Util.getStringAsIntOrZero(portInput));
        });
    }
}

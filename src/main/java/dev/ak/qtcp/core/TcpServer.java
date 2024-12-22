package dev.ak.qtcp.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class TcpServer {

    public Consumer<String> onSystemMessage = (e) -> {};

    public boolean opened = true;

    public void open(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            onSystemMessage.accept("Server opened on port " + port);

            while (opened) {
                Socket socket = serverSocket.accept();
                onSystemMessage.accept("Client connected");

                try (
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true)
                    ) {

                    String text;
                    while ((text = reader.readLine()) != null) {
                        onSystemMessage.accept("Received: " + text);
                        writer.println("Server: " + text);
                    }
                } catch (IOException ex) {
                    onSystemMessage.accept("Server exception: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            onSystemMessage.accept("Server exception: " + ex.getMessage());
        }
    }
}
package dev.ak.qtcp.core;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class TcpClient {

    public Consumer<String> onSystemMessage = (e) -> {};

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean connected = false;

    public void connect(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;

            String response;
            while (connected && (response = reader.readLine()) != null) {
                onSystemMessage.accept("Server Response: " + response);
            }
        } catch (UnknownHostException ex) {
            onSystemMessage.accept("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            onSystemMessage.accept("I/O error: " + ex.getMessage());
        } finally {
            disconnect();
        }
    }

    public void send(String message) {
        if (connected && writer != null) {
            writer.println(message);
        } else {
            onSystemMessage.accept("Not connected to a server.");
        }
    }

    public void disconnect() {
        try {
            connected = false;
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
            onSystemMessage.accept("Disconnected from the server.");
        } catch (IOException ex) {
            onSystemMessage.accept("Error during disconnect: " + ex.getMessage());
        }
    }
}
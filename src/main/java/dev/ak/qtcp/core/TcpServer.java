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

    private boolean running = false;

    private ServerSocket serverSocket;

    public void open(int port) {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            onSystemMessage.accept("Server opened on port " + port + ".");

            while (running) {
                Socket socket = serverSocket.accept();
                onSystemMessage.accept("Client (" + socket.getInetAddress().getHostAddress() + ") connected.");

                ClientHandler handler = new ClientHandler(socket);
                handler.onSystemMessage = onSystemMessage;
                handler.start();
            }
        } catch (IOException ex) {
            onSystemMessage.accept("Server exception: " + ex.getMessage());
        } finally {
            closeServerSocket();
        }
    }

    public void shutdown() {
        running = false;
        closeServerSocket();
    }

    private void closeServerSocket() {
        if (serverSocket == null || serverSocket.isClosed())
            return;
        try {
            serverSocket.close();
        } catch (IOException e) {
            onSystemMessage.accept("Error closing server socket: " + e.getMessage());
        }
    }
}


class ClientHandler extends Thread {

    public Consumer<String> onSystemMessage = (e) -> {};

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String text;
            while ((text = reader.readLine()) != null) {
                onSystemMessage.accept("Received: " + text);
                writer.println("Received " + text.length() + " bytes.");
            }
        } catch (IOException ex) {
            onSystemMessage.accept("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

package dev.ak.qtcp.core;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

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
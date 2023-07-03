package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class TCPServer {
    private final int PORT = 55400;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private Date date;

    public void start() {

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("TCP сервер запущен");
            while (true) {
                client(server.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void client(Socket socket) {
        try (Socket sock = socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            date = new Date();
            String outputMsg = formatter.format(date);
            System.out.println("Время приема сообщения: " + outputMsg + " ; Сообщение: " + sb +
                    "; Адрес хоста - клиента: " + sock.getRemoteSocketAddress());
            writer.write(outputMsg);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

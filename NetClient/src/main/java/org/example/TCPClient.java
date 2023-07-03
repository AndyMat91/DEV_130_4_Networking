package org.example;

import java.io.*;
import java.net.Socket;
import java.time.Instant;

public class TCPClient {
    private final int PORT = 55400;
    private final String HOST = "127.0.0.1";

    public void start() {

        try (Socket socket = new Socket(HOST, PORT);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите сообщение, которое хотите отправить: ");
            String outputMsg = bufferedReader.readLine();
            writer.write(outputMsg);
            writer.flush();
            socket.shutdownOutput();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("Время приема сообщения: " + sb);
        } catch (IOException e) {
            System.out.println("Подключение провалено, проверьте запущен ли сервер");
        }
    }
}

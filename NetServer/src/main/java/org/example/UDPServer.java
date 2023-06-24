package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UDPServer {

    private final int OUTPUT_PORT = 50999;
    private final int INPUT_PORT = 50888;
    private final int LENGTH = 1024;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private Date date;

    public void start() {

        try (DatagramSocket socketInput = new DatagramSocket(INPUT_PORT); DatagramSocket socketOutput = new DatagramSocket()) {
            DatagramPacket inputPacket = new DatagramPacket(new byte[LENGTH], LENGTH);
            DatagramPacket outputPacket = new DatagramPacket(new byte[LENGTH], LENGTH, InetAddress.getByName("127.0.0.1"), OUTPUT_PORT);
            System.out.println("UDP сервер запущен");
            while (true) {
                socketInput.receive(inputPacket);
                String inputMsg = new String(inputPacket.getData(), 0, inputPacket.getLength());
                date = new Date();
                String outputMsg = formatter.format(date);
                System.out.println("Время приема сообщения: " + outputMsg + " ; Сообщение: " + inputMsg +
                        "; Адрес хоста - клиента: " + inputPacket.getSocketAddress());
                byte[] buff = outputMsg.getBytes();
                for (int i = 0; i < outputMsg.length(); i += LENGTH) {
                    int len = (i + LENGTH) < buff.length ? LENGTH : buff.length - i;
                    outputPacket.setData(buff, i, len);
                    socketOutput.send(outputPacket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

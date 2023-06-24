package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient {
    private final int OUTPUT_PORT = 50888;
    private final int INPUT_PORT = 50999;
    private final int LENGTH = 1024;

    public void start() {

        try (DatagramSocket socketOutput = new DatagramSocket(); DatagramSocket socketInput = new DatagramSocket(INPUT_PORT)) {
            DatagramPacket outputPacket = new DatagramPacket(new byte[LENGTH], LENGTH, InetAddress.getByName("127.0.0.1"), OUTPUT_PORT);
            DatagramPacket inputPacket = new DatagramPacket(new byte[LENGTH], LENGTH);
            System.out.println("Введите сообщение, которое хотите отправить: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String outputMsg = br.readLine();
            byte[] buff = outputMsg.getBytes();
            for (int i = 0; i < outputMsg.length(); i += LENGTH) {
                int len = (i + LENGTH) < buff.length ? LENGTH : buff.length - i;
                outputPacket.setData(buff, i, len);
                socketOutput.send(outputPacket);
                socketInput.receive(inputPacket);
                String inputMsg = new String(inputPacket.getData(), 0, inputPacket.getLength());
                System.out.println("Время приема сообщения: " + inputMsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

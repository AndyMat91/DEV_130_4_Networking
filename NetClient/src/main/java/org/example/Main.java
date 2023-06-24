package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Какой клиент вы хотите запустить (UDP / TCP) ? ");
        Scanner scanner = new Scanner(System.in);
        String client = scanner.nextLine();
        if (client.equalsIgnoreCase("UDP")) {
            new UDPClient().start();
        } else if(client.equalsIgnoreCase("TCP")) {
            new TCPClient().start();
        }
    }
}
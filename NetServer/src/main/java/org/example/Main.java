package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Какой сервер вы хотите запустить (UDP / TCP) ? ");
        Scanner scanner = new Scanner(System.in);
        String server = scanner.nextLine();
        if (server.equalsIgnoreCase("UDP")) {
            new UDPServer().start();
        } else if (server.equalsIgnoreCase("TCP")) {
            new TCPServer().start();
        }
    }
}
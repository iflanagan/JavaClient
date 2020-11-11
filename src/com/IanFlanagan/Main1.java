package com.IanFlanagan;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args)  {
        try(Socket  socket = new Socket("localhost", 5000)) {

            BufferedReader echos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringtoEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter String to be echoed:");
                echoString = scanner.nextLine();
                stringtoEcho.println(echoString);
                if (!echoString.equals("exit")) {
                    response = echos.readLine();
                    System.out.println(response);
                }

            } while(!echoString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client Exception: " +e.getMessage());
        }

    }
}

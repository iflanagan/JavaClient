package com.IanFlanagan;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {




        try(Socket  socket = new Socket("localhost", MyConfig.myPort)) {

            socket.setSoTimeout(MyConfig.mySocketTimeout);

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

        } catch (SocketTimeoutException e) {
            System.out.println("Socket timed out " +e.getMessage());
        }

        catch (IOException e) {
            System.out.println("Client Exception: " +e.getMessage());
        }

    }
}

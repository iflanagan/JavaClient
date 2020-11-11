package com.IanFlanagan;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) throws Exception {

        try(Socket  socket = new Socket("localhost", MyConfig.myPort)) {

            socket.setSoTimeout(MyConfig.mySocketTimeout);

            BufferedReader echos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringtoEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter String to be echoed (type exit to quit):");
                echoString = scanner.nextLine();
                stringtoEcho.println(echoString);
                if (!echoString.equals("exit")) {
                    response = echos.readLine();
                    System.out.println(response);
                } if (echoString.equals("Exit")) {

                    throw new Exception("Exception generated");
                }

            } while(!echoString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client Exception: " +e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

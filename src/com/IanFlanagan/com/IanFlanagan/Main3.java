package com.IanFlanagan;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main3 {

    private static Rollbar rollbar = null;

    public static void main(String[] args) throws Exception {

       rollbar = Utils.createRollbarInstance(MyConfig.myRollbarAccessToken,MyConfig.myEnvironment, MyConfig.CodeVersion);

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

        } catch (IOException e) {
            rollbar.error(e, e.getMessage());
            System.out.println("Client Exception: " +e.getMessage());
        } finally {
            Utils.closeRollbar();
        }

    }
}

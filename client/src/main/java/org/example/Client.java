package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket("localhost", 7007);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String startResponse = bufferedReader.readLine();
            System.out.println(startResponse);
        } catch (IOException e) {
            System.out.println("Could not connect.");
        }
        MessageReceiver messageReceiver = new MessageReceiver(bufferedReader);
        MessageSender messageSender = new MessageSender(bufferedWriter);
        messageReceiver.start();
        messageSender.start();
//        while(true){
//            try {
//                String message = scanner.nextLine();
//                String toSend = name + ": " + message;
//                printWriter.flush();
//                printWriter.println(toSend);
//                printWriter.flush();
//                if(message.equalsIgnoreCase("bye")){
//                    break;
//                }
//                String response = bufferedReader.readLine();
//                System.out.println(response);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
    }
}

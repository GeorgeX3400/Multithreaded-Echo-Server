package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketThread extends Thread {
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public SocketThread(Socket socket) {
        this.socket = socket;
        try {
            System.out.println("New client conecting...");
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = bufferedReader.readLine();

        }
        catch(IOException e) {
            System.out.println("Could not connect.");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        try {
            System.out.println("New client connected");
            bufferedWriter.write("Hello " + name  + "! ");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while (true) {
                String clientMessage = bufferedReader.readLine();
                System.out.println("Received from " + name + ": " + clientMessage);
                if(clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected");
                    break;
                }
                //send the message back to client
                        bufferedWriter.write(this.name + ": " +clientMessage);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
            }
        }
        catch(IOException e) {
            System.out.println("Client disconnected");
            try{
                bufferedWriter.close();
                bufferedWriter.close();
                socket.close();

            }
            catch (IOException e2){
                throw new RuntimeException(e2);
            }
        }

    }
}

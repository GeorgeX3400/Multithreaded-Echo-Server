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
    public static List<SocketThread> socketThreadList = new ArrayList<>();
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public SocketThread(Socket socket) {
        this.socket = socket;
        this.name = name;
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter.write("Hello! Enter your username:");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            this.name = bufferedReader.readLine();

            socketThreadList.add(this);
        }
        catch(IOException e) {
            System.out.println("Could not connect.");

        }
    }

    @Override
    public void run(){

        try {

            System.out.println("New client connected");

            while (true) {
                String clientMessage = bufferedReader.readLine();
                System.out.println("new message received");

                if(clientMessage.contains(": bye")) {
                    System.out.println("Client disconnected");
                    break;
                }
                //send the message to all the other clients
                for(SocketThread socketThread: socketThreadList) {
                    if(!(socketThread.name.equals(this.name))) {
                        bufferedWriter.write(this.name + ": " +clientMessage);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("Client disconnected");
            try{
                socketThreadList.remove(this);
                bufferedWriter.close();
                bufferedWriter.close();
                socket.close();

            }
            catch (IOException e2){}
        }

    }
}

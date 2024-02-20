package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket;

        serverSocket = new ServerSocket(7007);
    while(true){
        try{
            socket = serverSocket.accept();
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Client connected!");
            bufferedWriter.write("Hello! To end the conversation, type \"^]\".\n");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while(true){
                String clientMessage = bufferedReader.readLine();
                System.out.println("new message received");
                if(clientMessage.equalsIgnoreCase("^]")) {
                    break;
                }
                bufferedWriter.write(clientMessage);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }
            System.out.println("Client disconnected.");
            inputStreamReader = null;
            outputStreamWriter = null;
            bufferedReader = null;
            bufferedWriter = null;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    }
}

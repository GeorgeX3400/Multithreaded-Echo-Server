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
            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }
        catch (IOException e) {
            System.out.println("Client disconnected.");
        }

    }


    }
}

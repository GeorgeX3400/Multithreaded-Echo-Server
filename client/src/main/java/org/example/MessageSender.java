package org.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MessageSender extends Thread{
    private BufferedWriter bufferedWriter;
    private Scanner scanner;


    public MessageSender(BufferedWriter bufferedWriter){
        this.bufferedWriter = bufferedWriter;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        scanner = new Scanner(System.in);
        while(this.bufferedWriter != null) {
            try {
                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.flush();
                if (message.contains(": bye")) {
                    System.out.println("Disconnected");
                    break;
                }
            }
            catch(IOException e){
                try{
                    bufferedWriter.close();
                }
                catch(IOException e2){}
            }
        }
    }
}

package org.example;

import java.io.BufferedReader;
import java.io.IOException;


public class MessageReceiver extends Thread {
    private BufferedReader bufferedReader;
    public MessageReceiver(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }
    @Override
    public void run() {
        while(true) {
            try{
                String message = bufferedReader.readLine();
                System.out.println(message);
            }
            catch(IOException e) {
                    try{
                        bufferedReader.close();
                    }
                    catch(IOException e1) {}

            }
        }

    }
}

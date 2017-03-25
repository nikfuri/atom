package ru.atom.http.server;

import org.eclipse.jetty.util.ConcurrentArrayQueue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by mkai on 3/22/17.
 */
public class ChatHistory implements Runnable {
    private static final ConcurrentArrayQueue<String> chat = new ConcurrentArrayQueue<>();


    public static void saveMessage(String msg) {
        /*File history = new File("./history.txt");
        if (history == null) {
            history.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(history, true); //the true will append the new data
            fw.write(msg + System.lineSeparator());//appends the string to the file
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }*/
        chat.add(msg);
    }

    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted()) {
            String msg = chat.poll();
            if (msg != null){
                File history = new File("./history.txt");
                if (history == null) {
                    history.mkdir();
                }
                try {
                    FileWriter fw = new FileWriter(history, true); //the true will append the new data
                    fw.write(msg + System.lineSeparator());//appends the string to the file
                    fw.close();
                } catch (IOException ioe) {
                    System.err.println("IOException: " + ioe.getMessage());
                }
            }

        }
    }
}


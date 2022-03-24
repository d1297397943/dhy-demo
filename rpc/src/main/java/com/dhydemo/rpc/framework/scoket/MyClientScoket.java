package com.dhydemo.rpc.framework.scoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyClientScoket {

    private static final Logger logger = LoggerFactory.getLogger(MyClientScoket.class);

    public Object send(final String host, final int port, final Message message) {
        try (Socket socket = new Socket(host, port)) {
//            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            objectOutputStream.writeObject(message);
//            Message o = (Message) objectInputStream.readObject();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(Message message, String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        MyClientScoket helloClient = new MyClientScoket();
        Message message = (Message) helloClient.send(new Message("content from client1"), "127.0.0.1", 6666);
        System.out.println("client receive message:" + message.getContent());
        helloClient.send("127.0.0.1", 6666, new Message("content from client2"));

    }
}
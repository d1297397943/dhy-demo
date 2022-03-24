package com.dhydemo.rpc.framework.scoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerScoket {

    private static Logger log = LoggerFactory.getLogger(MyServerScoket.class);

    public void start(int ip) {
        try (ServerSocket sc = new ServerSocket(ip)) {
            Socket accept;
            System.err.println("client connected");
            while ((accept = sc.accept()) != null) {
                System.out.println("jinru");
                ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                Message o = (Message) objectInputStream.readObject();
                System.out.println(o.getContent());
                System.out.println("服务端接受信息：" + o.getContent());
                objectOutputStream.writeObject(o);
                objectOutputStream.flush();

            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyServerScoket myServerScoket = new MyServerScoket();
        myServerScoket.start(6666);
    }

}

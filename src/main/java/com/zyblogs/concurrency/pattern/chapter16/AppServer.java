package com.zyblogs.concurrency.pattern.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: AppServer.java
 * @Package com.zyblogs.concurrency.pattern.chapter16
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AppServer extends Thread{

    private final int port ;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    /**
     *  线程池
     */
    private final  ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer(){
        this(DEFAULT_PORT);
    }

    public AppServer(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start){
                            // 被阻塞
            Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                // 添加集合管理
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            this.dispose();
        }
    }

    private void dispose() {
        System.out.println("AppServer.dispose");
        this.clientHandlers.stream().forEach(c -> c.stop());
        this.executor.shutdown();
    }

    public void shutdown(){
        this.start = false;
        this.interrupt();
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

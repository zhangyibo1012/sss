package com.zyblogs.concurrency.pattern.chapter16;

import java.io.*;
import java.net.Socket;

/**
 * @Title: ClientHandler.java
 * @Package com.zyblogs.concurrency.pattern.chapter16
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // try()使用完毕后会自动关闭
      try (InputStream inputStream = socket.getInputStream();
           OutputStream outputStream  = socket.getOutputStream();
           // 装饰器设计模式
           BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
           PrintWriter printWriter = new PrintWriter(outputStream))
      {
          while (running){
            String message = br.readLine();
            if (message == null){
                break;
            }
              System.out.println("Come from client > " + message);
              printWriter.write("echo " + message + "\n");
              // 刷新流
              printWriter.flush();
          }
      }catch (Exception ex){
          ex.printStackTrace();
          this.running = false;
      } finally {
          this.stop();
      }
    }

    public void stop() {
        if ( !running){
            return;
        }

        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            //
        }
    }
}

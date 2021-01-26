package com.atm.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 银行管理系统服务器端
 */
public class BankCenterServer {
    public BankCenterServer(){
        new LoopReceiver().start();
    }
    private class LoopReceiver extends Thread{
        private ServerSocket serverSocket =null;
        public LoopReceiver(){
            try {
                serverSocket = new ServerSocket(2021);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (this.isAlive()){   //利用while循环直到该线程死亡
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    if (clientSocket!=null){
                        new ServerProcessIOUtil(clientSocket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                        try {
                            if (clientSocket!=null) {
                                clientSocket.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

    public static void main(String[] args) {
        BankCenterServer bankCenterServer = new BankCenterServer();
    }
}

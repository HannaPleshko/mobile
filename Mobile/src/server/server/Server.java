package server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements Runnable {
    private ServerSocket serverSocket;
    private static Logger log = Logger.getLogger(Server.class.getName());
    private boolean isStopped = false;

    @Override
    public void run() {
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
                new Thread(new ServerWorker(clientSocket)).start();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Ошибка работы сервера! ", e);
            }
        }

    }

    private void openServerSocket() {
        System.out.println("Opening server socket...");
        this.isStopped = false;
        try {
            this.serverSocket = new ServerSocket(2525);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

}

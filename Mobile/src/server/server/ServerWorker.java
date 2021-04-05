package server.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

    public class ServerWorker implements Runnable {

        private Socket clientSocket;
        private ObjectOutputStream output = null;
        private ObjectInputStream input = null;

        public ServerWorker(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                output = new ObjectOutputStream(clientSocket.getOutputStream());
                input = new ObjectInputStream(clientSocket.getInputStream());
                while (true) {
                    String command = (String) input.readObject();
                    Object result = CommandsFromClient.split(command);
                    output.writeObject(result);
                }
            } catch (Exception e) {
                try {
                    output.close();
                    input.close();
                    clientSocket.close();
                } catch (IOException ioexc) {
                    e.printStackTrace();
                }
            }
        }
    }


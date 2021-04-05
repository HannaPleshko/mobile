package client.sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Client {
    private Socket clientSocket;
    private ObjectInputStream clientInpStream;
    private ObjectOutputStream clientOutpStream;

    public void connect() {
        try {
            clientSocket = new Socket("localhost", 2525);
            clientOutpStream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientInpStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Object obj) {
        try {
            clientOutpStream.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean receiveResultBool() {
        boolean result = true;
        try {
            result = (boolean) clientInpStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> receiveResultList() {
        ArrayList<String> list = null;
        try {
            list = (ArrayList<String>)clientInpStream.readObject();
        } catch (Exception e) {
        }
        return list;
    }

    public String receiveResultString() {
        String result = null;
        try {
            result = (String) clientInpStream.readObject();
        } catch (Exception e) {

        }
        return result;
    }
}

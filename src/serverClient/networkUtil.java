package serverClient;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.*;

public class networkUtil {
    private Socket client;
    private long token;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public networkUtil(Socket c, long t) {
        client = c;
        token = t;
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Object read() {
        try {
            Object request = in.readUnshared();
            if(request != null) {
                return request;
            }
            System.out.println("Null Object");
            return null;
        }catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean write(Object response) {
        try {
            out.writeUnshared(response);
            return true;
        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Socket getClient() {
        return client;
    }

    public long getToken() {
        return token;
    }

    public void closeUtil() {
        try {
            in.close();
            out.close();
            client.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}

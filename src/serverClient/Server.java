package serverClient;

import DatabasePack.Database;

import java.io.IOException;
import java.net.*;

public class Server {
    private static int port = 33333;

    public static void startServer () {
        Database database = Database.getSingletonInstance();
        database.openDatabase();
        try (ServerSocket server = new ServerSocket(port)) {
            long token = 1;
            while(true) {
                Socket client = server.accept();
                System.out.println("Client Accepted");
                new clientHandler(new networkUtil(client,token),database);
                token++;
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            database.closeDatabase();
        }
    }

    public static void main(String args[]) {
        startServer();
    }

}

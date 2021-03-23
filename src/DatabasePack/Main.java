package DatabasePack;

import DatabasePack.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Database db = Database.getSingletonInstance();
        db.openDatabase();

        //db.executeUpdateQuantity(2);

        db.closeDatabase();
    }
}

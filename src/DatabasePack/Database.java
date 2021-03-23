package DatabasePack;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection connection;

    private static final String tableName = "carDetails";
    private static final String regNo = "regNo";
    private static final String make = "make";
    private static final String model = "model";
    private static final String imagePath = "image";
    private static final String year = "year";
    private static final String color = "color";
    private static final String quantity = "quantity";
    private static final String editingBy = "beingEditedBy";
    private static final String orderBYClause = " ORDER BY " + make + " , " + model + " ;" ;
    private static final String userInfoTableName = "infoTable";
    private static final String userName = "user";
    private static final String pass = "password";

    private static final String createTable = "CREATE TABLE IF NOT EXISTS " + tableName +
                    " (" + regNo + " STRING, " + make + " STRING, " + model + " STRING, "
                    + imagePath + " STRING, " + year + " INTEGER, " + color + " STRING, " + quantity + " INTEGER, " +
                    editingBy + " STRING);" ;

    private static final String createTableForUserInfo = "CREATE TABLE IF NOT EXISTS " + userInfoTableName +
                    " (" + userName + " STRING, " + pass + " STRING);" ;
    private static final String selectPasswordForUserName = "SELECT " + pass + " FROM " + userInfoTableName + " WHERE " +
                    userName + " = ? ;" ;


    private static final String selectAllCars = "SELECT * FROM " + tableName + orderBYClause;
    private static final String selectByRegNo = "SELECT * FROM " + tableName + " WHERE " + regNo + " = ? ;" ;
    private static final String selectByMake = "SELECT * FROM " + tableName + " WHERE " + make + " = ? COLLATE NOCASE;" ;
    private static final String selectByMakeModel = "SELECT * FROM " + tableName + " WHERE " + make + " = ? COLLATE NOCASE and "+
                    model + " = ? COLLATE NOCASE;" ;

    private static final String insertCar = "INSERT INTO carDetails (" +  regNo + ", " + make + ", "
                    + model + ", " + imagePath + ", " + year + ", " + color + ", " + quantity + ", " +
                    editingBy+ ") values( ? , ? , ? , ? , ? , ? , ? , ? );" ;

    private static final String deleteCar = "DELETE FROM " + tableName + " WHERE " + regNo + " = ? ;" ;

    private static final String countCarByRegNo = "SELECT COUNT (*) FROM " + tableName + " WHERE " + regNo + " = ? ;";

    private static final String updateEditingBY = "UPDATE " + tableName + " SET " + editingBy + " = ? WHERE " + regNo + " = ? ;";
    private static final String selectEditingBy = "SELECT " + editingBy + " FROM " + tableName + " WHERE " + regNo + " = ? ;";
    private static final String updateQuantity = "UPDATE " + tableName + " SET " + quantity + " = ? WHERE " + regNo + " = ? ;";

    private static PreparedStatement createTableStatement;
    private static PreparedStatement createUserInfoTableStatement;
    private static PreparedStatement selectPasswordForUserStatement;
    private static PreparedStatement selectAllCarsStatement;
    private static PreparedStatement selectByRegNoStatement;
    private static PreparedStatement selectByMakeStatement;
    private static PreparedStatement selectByMakeModelStatement;
    private static PreparedStatement insertCarStatement;
    private static PreparedStatement deleteCarStatement;
    private static PreparedStatement countCarByRegNoStatement;
    private static PreparedStatement updateEditingByStatement;
    private static PreparedStatement selectEditingByStatement;
    private static PreparedStatement updateQuantityStatement;

    private static final Database singletonInstance = new Database();

    private Database () {

    }

    public void openDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:cars.db");

            createTableStatement = connection.prepareStatement(createTable);
            createUserInfoTableStatement = connection.prepareStatement(createTableForUserInfo);

            createTableStatement.execute();
            createUserInfoTableStatement.execute();

            selectPasswordForUserStatement = connection.prepareStatement(selectPasswordForUserName);
            selectAllCarsStatement = connection.prepareStatement(selectAllCars);
            selectByRegNoStatement = connection.prepareStatement(selectByRegNo);
            selectByMakeStatement = connection.prepareStatement(selectByMake);
            selectByMakeModelStatement = connection.prepareStatement(selectByMakeModel);
            insertCarStatement = connection.prepareStatement(insertCar);
            deleteCarStatement = connection.prepareStatement(deleteCar);
            countCarByRegNoStatement = connection.prepareStatement(countCarByRegNo);
            updateEditingByStatement = connection.prepareStatement(updateEditingBY);
            selectEditingByStatement = connection.prepareStatement(selectEditingBy);
            updateQuantityStatement = connection.prepareStatement(updateQuantity);


        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Car> executeSelectAllCars() {
        ArrayList<Car> allCars = new ArrayList<> ();
        try {
            ResultSet results = selectAllCarsStatement.executeQuery();
            while(results.next()) {
                Car car = new Car();
                parseFromResultToCar(results,car);
                System.out.println("Debugging");
                System.out.println(car.getRegistration());
                System.out.println(car.getMake());
                allCars.add(car);
            }
            System.out.println(allCars.size());
            return allCars;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Car executeSelectByRegNo(String reg) {
        try {
            countCarByRegNoStatement.setString(1,reg);
            ResultSet count = countCarByRegNoStatement.executeQuery();
            if(count.getInt(1) == 0) {
                System.out.println("No car in Reg");
                return null;
            }
            else {
                selectByRegNoStatement.setString(1,reg);
                ResultSet results = selectByRegNoStatement.executeQuery();
                Car car = new Car();
                parseFromResultToCar(results,car);
                return car;
            }
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Car> executeSelectByMake(String make) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            selectByMakeStatement.setString(1,make);
            ResultSet results = selectByMakeStatement.executeQuery();
            while (results.next()) {
                Car car = new Car() ;
                parseFromResultToCar(results,car);
                cars.add(car);
            }
            return cars;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Car> executeSelectByMakeModel(String make,String model) {
        ArrayList<Car> cars = new ArrayList<> ();
        try {
            selectByMakeModelStatement.setString(1,make);
            selectByMakeModelStatement.setString(2, model);
            ResultSet results = selectByMakeModelStatement.executeQuery();
            while(results.next()) {
                Car car = new Car();
                parseFromResultToCar(results,car);
                cars.add(car);
            }
            return cars;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean executeInsertCar(Car car) {
        try {
            countCarByRegNoStatement.setString(1,car.getRegistration());
            ResultSet results = countCarByRegNoStatement.executeQuery();
            if(results.getInt(1) != 0) {
                System.out.println("Same RegNo already exists!");
                return false;
            }
            insertCarStatement.setString(1,car.getRegistration());
            insertCarStatement.setString(2,car.getMake());
            insertCarStatement.setString(3,car.getModel());
            insertCarStatement.setString(4,car.getImagePath());
            insertCarStatement.setInt(5,car.getYear());
            insertCarStatement.setString(6,car.getColor());
            insertCarStatement.setInt(7,car.getQuantity());
            insertCarStatement.setString(8,car.getEditingBy());

            insertCarStatement.execute();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeDeleteCar(String regNo) {
        try {
            deleteCarStatement.setString(1,regNo);
            int res = deleteCarStatement.executeUpdate();
            if(res == 0) {
                System.out.println("No car found to Delete");
            }
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String executeSelectEditingBy(String regNo) {
        try {
            selectEditingByStatement.setString(1,regNo);
            ResultSet results = selectEditingByStatement.executeQuery();
            return results.getString(1);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean executeUpdateEditingBy(long token,String regNo) {
        try {
            updateEditingByStatement.setString(1, String.valueOf(token));
            updateEditingByStatement.setString(2, regNo);
            int f = updateEditingByStatement.executeUpdate();
            if(f == 0) {
                return false;
            }
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeUpdateQuantity(int q,String regNo) {
        try {
            updateQuantityStatement.setInt(1,q);
            updateQuantityStatement.setString(2,regNo);
            int f = updateQuantityStatement.executeUpdate();
            if(f == 0) {
                return false;
            }
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean executeCheckUserInfo(String user,String pass) {
        try {
            selectPasswordForUserStatement.setString(1, user);
            ResultSet results = selectPasswordForUserStatement.executeQuery();
            if(results != null && results.getString(1).equals(pass)) {
                return true;
            }
            return false;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void parseFromResultToCar(ResultSet results, Car car) {
        try {
            car.setRegistration(results.getString(1));
            car.setMake(results.getString(2));
            car.setModel(results.getString(3));
            car.setImagePath(results.getString(4));
            car.setYear(results.getInt(5));
            car.setColor(results.getString(6));
            car.setQuantity(results.getInt(7));
            car.setEditingBy(results.getString(8));
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getSingletonInstance() {
        return singletonInstance;
    }

    public void closeDatabase() {
        try {
            if(updateQuantityStatement != null) {
                updateQuantityStatement.close();
            }
            if(selectEditingByStatement != null) {
                selectEditingByStatement.close();
            }
            if(updateEditingByStatement != null) {
                updateEditingByStatement.close();
            }
            if(countCarByRegNoStatement != null) {
                countCarByRegNoStatement.close();
            }
            if(deleteCarStatement != null) {
                deleteCarStatement.close();
            }
            if(insertCarStatement != null) {
                insertCarStatement.close();
            }
            if(selectByMakeModelStatement != null) {
                selectByMakeModelStatement.close();
            }
            if(selectByMakeStatement != null) {
                selectByMakeStatement.close();
            }
            if(selectByRegNoStatement != null) {
                selectByRegNoStatement.close();
            }
            if(selectAllCarsStatement != null) {
                selectAllCarsStatement.close();
            }
            if(selectPasswordForUserStatement != null) {
                selectPasswordForUserStatement.close();
            }
            if(createUserInfoTableStatement != null) {
                createUserInfoTableStatement.close();
            }
            if(createTableStatement != null) {
                createTableStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}

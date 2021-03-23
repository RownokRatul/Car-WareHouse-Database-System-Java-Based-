package sample;

import DatabasePack.Car;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import DatabasePack.userLoginInfo;
import serverClient.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Optional;

public class Main extends Application {

    Stage primaryStage;

    private userLoginInfo manufacturer ;
    private typeOfClient clientType;

    private networkUtil util;


    @Override
    public void start(Stage primaryStage) throws Exception{
        openConnecion();
        this.primaryStage = primaryStage;
        showWelcomePage();
        primaryStage.setOnCloseRequest(e -> {
            closeConnection();
        });
    }

    private void openConnecion() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(),33333);
            util = new networkUtil(client,1);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        util.closeUtil();
    }

    public networkUtil getUtil() {
        return util;
    }

    public void showWelcomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomePage.fxml"));
        Parent root = loader.load();

        welcomePageController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Welcome Page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginScreen.fxml"));
        Parent root = loader.load();

        loginScreenController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showGuestMainMenuPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("guestMainMenu.fxml"));
        Parent root = loader.load();

        guestMainMenuController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showManufacturerMainMenuPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturerMainMenu.fxml"));
        Parent root = loader.load();

        manufacturerMainMenuController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showManufacturerAddCarPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturerAddCar.fxml"));
        Parent root = loader.load();

        manufacturerAddCarController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Add Car");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showCarDetailsHandler(Car car) throws IOException {
        if(clientType == typeOfClient.guest) {
            showGuestCarPage(car);
        }
        else if(clientType == typeOfClient.manufacturer) {
            showManufacturerCarPage(car);
        }
    }

    public void showMainMenuHandler() throws IOException {
        if(clientType == typeOfClient.guest) {
            showGuestMainMenuPage();
        }
        else if(clientType == typeOfClient.manufacturer) {
            showManufacturerMainMenuPage();
        }
    }

    public void showGuestCarPage(Car car) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("guestCar.fxml"));
        DialogPane dialogPane = loader.load();

        guestCarController controller = loader.getController();
        controller.setCar(car);
        controller.setMain(this);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Selected Car");
        dialog.showAndWait();
    }

    public void showManufacturerCarPage(Car car) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturerCar.fxml"));
        DialogPane dialogPane = loader.load();

        manufacturerCarController controller = loader.getController();
        controller.setCar(car);
        controller.setMain(this);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Selected Car");
        dialog.showAndWait();
    }

    public void showSearchByRegistrationPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchByRegistration.fxml"));
        Parent root = loader.load();

        searchByRegistrationController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Search By Registration");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showSearchByMakeModelPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchByMakeModel.fxml"));
        Parent root = loader.load();

        searchByMakeModelController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Search By Make Model");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showGuestBuyCarPage(String reg) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyCar.fxml"));
        DialogPane dialogPane = loader.load();

        guestBuyController controller = loader.getController();
        controller.setRegistration(reg);
        controller.setMain(this);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Buy Selected Car");
        dialog.showAndWait();
    }

    public void showManufacturerDeleteCarPage(String regNo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturerDeleteCar.fxml"));
        DialogPane dialogPane = loader.load();

        manufacturerDeleteCarController controller = loader.getController();
        controller.setRegistration(regNo);
        controller.setMain(this);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Delete Car");
        dialog.showAndWait();
    }

    public void showListOfCars(requestType type) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listOfCars.fxml"));
        Parent root = loader.load();

        listOfCarsController controller = loader.getController();
        controller.setRequestType(type);
        controller.setMain(this);

        primaryStage.setTitle("Car List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showListOfCars(requestType type,String s) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listOfCars.fxml"));
        Parent root = loader.load();

        listOfCarsController controller = loader.getController();
        controller.setRequestType(type);
        if(type == requestType.SearchByReg) {
            controller.setRegNo(s);
        }
        else if(type == requestType.SearchByMake){
            controller.setMake(s);
        }
        controller.setMain(this);

        primaryStage.setTitle("Car List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showListOfCars(requestType type,String s1,String s2) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listOfCars.fxml"));
        Parent root = loader.load();

        listOfCarsController controller = loader.getController();
        controller.setRequestType(type);
        controller.setMake(s1);
        controller.setModel(s2);
        controller.setMain(this);

        primaryStage.setTitle("Car List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showManufacturerEditCarPage(Car car) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturerEditCar.fxml"));
        DialogPane dialogPane = loader.load();

        manufacturerEditCarController controller = loader.getController();
        controller.setOldCar(car);
        controller.setMain(this);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Edit A Car");
        dialog.showAndWait();
    }

    public void setManufacturer(String user,String pass) {
        manufacturer = new userLoginInfo(user,pass);
    }

    public userLoginInfo getManufacturer() {
        return manufacturer;
    }

    public String getManufacturerName() {
        return manufacturer.getUserName();
    }

    public void setClientType(typeOfClient c) {
        clientType = c;
    }

    public typeOfClient getClientType() {
        return clientType;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

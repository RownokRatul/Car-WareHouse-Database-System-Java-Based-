package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serverClient.requestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manufacturerMainMenuController implements Initializable {
    private Main main;

    @FXML
    private Button viewCarsButton;

    @FXML
    private Button deleteCarButton;

    @FXML
    private Button addCarButton;

    @FXML
    private Button editCarButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView manufacturerAvatar;

    @FXML
    private void viewCar() throws IOException {
        System.out.println("In View Car");
        main.showListOfCars(requestType.viewAllCars);
    }

    @FXML
    private void deleteCar(ActionEvent actionEvent) throws IOException {
        main.showSearchByRegistrationPage();
    }

    @FXML
    private void addCar(ActionEvent actionEvent) throws IOException {
        main.showManufacturerAddCarPage();
    }

    @FXML
    private void editCar(ActionEvent actionEvent) throws IOException {
        main.showSearchByRegistrationPage();
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        main.showWelcomePage();
    }

    public void setMain(Main m) {
        main = m;
    }

    private void loadPeripherels() {
        new Thread(()-> {
            Platform.runLater(() -> {
                manufacturerAvatar.setImage(new Image(Main.class.getResourceAsStream("manufacturerAvatar.png")));
                userNameLabel.setText(userNameLabel.getText() + " " +main.getManufacturerName());
            });
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPeripherels();
    }
}

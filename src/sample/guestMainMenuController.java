package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serverClient.requestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class guestMainMenuController implements Initializable {
    private Main main;

    @FXML
    private Button viewCarsButton;

    @FXML
    private Button searchByRegButton;

    @FXML
    private Button buyCarButton;

    @FXML
    private Button searchByMakeModelButton;

    @FXML
    public ImageView guestImageAvatar;

    @FXML
    private void viewCar(ActionEvent actionEvent) throws IOException {
        main.showListOfCars(requestType.viewAllCars);
    }

    @FXML
    private void searchByRegistration(ActionEvent actionEvent) throws IOException {
        main.showSearchByRegistrationPage();
    }

    @FXML
    private void buyCar(ActionEvent actionEvent) throws IOException {
        main.showSearchByRegistrationPage();
    }

    @FXML
    private void searchByMakeModel(ActionEvent actionEvent) throws IOException {
        main.showSearchByMakeModelPage();
    }

    public void setMain(Main m) {
        main = m;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guestImageAvatar.setImage(new Image(Main.class.getResourceAsStream("guestAvatar.jpg")));
    }
}

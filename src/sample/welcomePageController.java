package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class welcomePageController {

    private Main main;

    @FXML
    private Button loginManufacturerButton;

    @FXML
    private Button exploreGuestButton;

    @FXML
    private void exploreGuestOption(ActionEvent actionEvent) throws IOException {
        main.setClientType(typeOfClient.guest);
        main.showGuestMainMenuPage();
    }

    @FXML
    private void loginManufacturerOption(ActionEvent actionEvent) throws IOException {
        main.setClientType(typeOfClient.manufacturer);
        main.showLoginPage();
    }

    public void setMain(Main m) {
        main = m;
    }

}

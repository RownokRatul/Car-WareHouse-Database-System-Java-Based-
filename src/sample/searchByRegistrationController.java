package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import serverClient.requestType;

import java.io.IOException;

public class searchByRegistrationController {
    private Main main;

    @FXML
    private TextField enterRegistration;

    @FXML
    private Button searchButton;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private void searchOnRegistration(ActionEvent actionEvent) throws IOException {
        main.showListOfCars(requestType.SearchByReg,enterRegistration.getText());
    }

    @FXML
    private void backToMainMenu(ActionEvent actionEvent) throws IOException {
        main.showGuestMainMenuPage();
    }

    public void setMain(Main m) {
        main = m;
    }

}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import serverClient.requestType;

import java.io.IOException;

public class searchByMakeModelController {
    private Main main;

    @FXML
    private TextField enterMake;

    @FXML
    private TextField enterModel;

    @FXML
    private Button searchButton;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private void searchOnMakeModel(ActionEvent actionEvent) throws IOException {
        String make = enterMake.getText();
        String model = enterModel.getText();
        if(model.equals("")) {
            main.showListOfCars(requestType.SearchByMake,make);
        }
        else {
            main.showListOfCars(requestType.SearchByMakeModel,make,model);
        }
    }

    @FXML
    private void backToMainMenu(ActionEvent actionEvent) throws IOException {
        main.showGuestMainMenuPage();
    }

    public void setMain(Main m) {
        main = m;
    }
}

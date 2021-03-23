package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import DatabasePack.Car;
import serverClient.*;

import java.io.IOException;

public class  manufacturerAddCarController {
    private Main main;

    @FXML
    private Button addCarButton;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private TextField enterRegistration;

    @FXML
    private TextField enterMake;

    @FXML
    private TextField enterModel;

    @FXML
    private TextField enterImagePath;

    @FXML
    private TextField enterYear;

    @FXML
    private TextField enterColors;

    @FXML
    private TextField enterQuantity;

    @FXML
    private void addCarOnInfo(ActionEvent actionEvent) {
        Car car = new Car();
        String reg = enterRegistration.getText();
        String make = enterMake.getText();
        String model = enterModel.getText();
        String year = enterYear.getText();
        String imagePath = enterImagePath.getText();
        String quantity = enterQuantity.getText();
        String color = enterColors.getText();

        if(reg.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Registration EMPTY!");
            alert.showAndWait();
        }
        else if(make.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Make EMPTY!");
            alert.showAndWait();
        }
        else if(model.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Model EMPTY!");
            alert.showAndWait();
        }
        else if(year.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Year EMPTY!");
            alert.showAndWait();
        }
        else if(imagePath.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Image EMPTY!");
            alert.showAndWait();
        }
        else if(quantity.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Quantity EMPTY!");
            alert.showAndWait();
        }
        else if(color.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Color EMPTY!");
            alert.showAndWait();
        }
        else {
            car.setEditingBy("");
            car.setImagePath(imagePath);
            car.setQuantity(Integer.parseInt(quantity));
            car.setColor(color);
            car.setModel(model);
            car.setMake(make);
            car.setRegistration(reg);
            car.setYear(Integer.parseInt(year));

            requestPrcoess request = new requestPrcoess();
            request.setRequestType(requestType.AddCar);
            request.setCar(car);
            main.getUtil().write(request);

            responseProcess response = (responseProcess)main.getUtil().read();
            if(response == null || response.getSuccessful() == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error Occured, Car cannot be Added, TRY AGAIN!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Car has been Added!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void backToMainMenu(ActionEvent actionEvent) throws IOException {
        main.showManufacturerMainMenuPage();
    }

    public void setMain(Main m) {
        main = m;
    }


}

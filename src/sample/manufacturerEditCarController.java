package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import DatabasePack.Car;
import serverClient.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manufacturerEditCarController implements Initializable {
    private Main main;
    private Car oldCar;

    @FXML
    private TextField editRegistration;

    @FXML
    private TextField editMake;

    @FXML
    private TextField editModel;

    @FXML
    private TextField editColors;

    @FXML
    private TextField editQuantity;

    @FXML
    private TextField editImagePath;

    @FXML
    private TextField editYear;

    @FXML
    private Button doneEditButton;

    @FXML
    private void doneOnEditing(ActionEvent actionEvent) throws IOException {
        Car car = new Car();
        String reg = editRegistration.getText();
        String make = editMake.getText();
        String model = editModel.getText();
        String year = editYear.getText();
        String imagePath = editImagePath.getText();
        String quantity = editQuantity.getText();
        String color = editColors.getText();

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
            request.setRequestType(requestType.editedCar);
            request.setRegNo(oldCar.getRegistration());
            request.setCar(car);
            main.getUtil().write(request);
            responseProcess response = (responseProcess) main.getUtil().read();

            Alert alert;
            if(response.getSuccessful()) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Edit Done");
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Something Went Wrong.Try Again!");
            }
            alert.showAndWait();
        }

        main.showMainMenuHandler();
    }

    public void setMain(Main m) {
        main = m;
    }

    public void setOldCar(Car c) {
        oldCar = c;
    }

    private void loadFields() {
        new Thread(() -> {
            Platform.runLater(() -> {
                editRegistration.setText(oldCar.getRegistration());
                editMake.setText(oldCar.getMake());
                editModel.setText(oldCar.getModel());
                editImagePath.setText(oldCar.getImagePath());
                editColors.setText(oldCar.getColor());
                editQuantity.setText(String.valueOf(oldCar.getQuantity()));
                editYear.setText(String.valueOf(oldCar.getYear()));
            });
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFields();
    }
}

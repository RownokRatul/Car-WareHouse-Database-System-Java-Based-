package sample;

import DatabasePack.Car;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serverClient.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manufacturerCarController implements Initializable {
    private Main main;
    private requestType type;

    private Car car;

    @FXML
    private Button deleteCarButton;

    @FXML
    private Button editCarButton;

    @FXML
    private Label details;

    @FXML
    private ImageView carImage;

    @FXML
    private void deleteThisCar(ActionEvent actionEvent) throws IOException {
        main.showManufacturerDeleteCarPage(car.getRegistration());
    }

    @FXML
    private void editThisCar(ActionEvent actionEvent) throws IOException {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.requestToEdit);
        request.setRegNo(car.getRegistration());
        main.getUtil().write(request);
        responseProcess response = (responseProcess)main.getUtil().read();
        if(response.getSuccessful()) {
            main.showManufacturerEditCarPage(car);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One of the Other Manufacturer is Editing.Wait and TRY LATER!");
            alert.showAndWait();
        }
    }

    public void setRequestType(requestType t) {
        type = t;
    }

    public void setCar(Car c) {
        car = c;
    }

    public void setMain(Main m) {
        main = m;
    }

    private void loadDetails() {
        new Thread(() -> {
            Platform.runLater(() -> {
                details.setText("Made By        : " +car.getMake() +
                        "\nModel           : " +car.getModel()+
                        "\nRegistration  : " + car.getRegistration()+
                        "\nAvailable      : "+ car.getQuantity()+
                        "\nColor           : "+car.getColor()+
                        "\nVersion        : "+car.getYear());
                carImage.setImage(new Image(Main.class.getResourceAsStream(car.getImagePath())));
            });
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDetails();
    }
}

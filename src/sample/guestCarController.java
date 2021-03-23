package sample;

import DatabasePack.Car;
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

public class guestCarController implements Initializable {
    private Main main;

    private Car car;

    @FXML
    private Button buyCarButton;

    @FXML
    private Label details;

    @FXML
    private ImageView carImage;

    @FXML
    private void buyThisCar(ActionEvent actionEvent) throws IOException {
        main.showGuestBuyCarPage(car.getRegistration());
    }

    public void setMain(Main m) {
        main = m;
    }

    public void setCar(Car c) {
        car = c;
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

package sample;

import DatabasePack.Car;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class carProperty {
    private Main main;
    public SimpleStringProperty carMake;
    public SimpleStringProperty carModel;
    public SimpleStringProperty carRegistration;
    public SimpleIntegerProperty carYear;
    public SimpleStringProperty editingBy;
    public SimpleStringProperty carImagePath;
    public SimpleIntegerProperty carQuantity;
    public SimpleStringProperty carColor;


    public Button detailsButton;

    public carProperty(Car c,Main m) {
        main = m;
        carMake = new SimpleStringProperty(c.getMake());
        carModel = new SimpleStringProperty(c.getModel());
        carImagePath = new SimpleStringProperty(c.getImagePath());
        carColor = new SimpleStringProperty(c.getColor());
        carQuantity = new SimpleIntegerProperty(c.getQuantity());
        carYear = new SimpleIntegerProperty(c.getYear());
        carRegistration = new SimpleStringProperty(c.getRegistration());
        editingBy = new SimpleStringProperty(c.getEditingBy());

        System.out.println("New Car property");

        detailsButton = new Button("Details");
        detailsButton.setOnAction( ae -> {
            try {
                main.showCarDetailsHandler(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("New Car property");
    }

    public String getMake() {
        return carMake.getValue();
    }

    public String getModel() {
        return carModel.getValue();
    }

    public int getQuantity() {
        return carQuantity.getValue();
    }

    public int getYear() {
        return carYear.getValue();
    }

    public String getColor() {
        return carColor.getValue();
    }

    public String getImagePath() {
        return carImagePath.getValue();
    }

    public String getEditingBy() {
        return editingBy.getValue();
    }

    public String getRegistration() {
        return carRegistration.getValue();
    }
}

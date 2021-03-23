package sample;

import DatabasePack.Car;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;
import serverClient.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class listOfCarsController implements Initializable {
    private Main main;
    private requestType type;
    private String regNo;
    private String make;
    private String model;

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<Car> carList;

    private ObservableList<carProperty> carData = FXCollections.observableArrayList();

    @FXML
    private void backToMainMenu(ActionEvent actionEvent) throws IOException {
        main.showMainMenuHandler();
    }

    @FXML
    public void showSelectedItem(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Car car = carList.getSelectionModel().getSelectedItem();
        if(car != null) {
            main.showCarDetailsHandler(car);
        }
    }

    @FXML
    private void refresh(ActionEvent actionEvent) throws IOException {
        if(type == requestType.viewAllCars) {
            main.showListOfCars(type);
        }
        else if(type == requestType.SearchByReg) {
            main.showListOfCars(type,regNo);
        }
        else if(type == requestType.SearchByMake) {
            main.showListOfCars(type,make);
        }
        else if(type == requestType.SearchByMakeModel) {
            main.showListOfCars(type,make,model);
        }
    }

    public void setMain(Main m) {
        main = m;
    }

    public void setRequestType(requestType r) {
        type = r;
    }

    public void setRegNo(String r) {
        regNo = r;
    }

    public void setMake(String mk) {
        make = mk;
    }

    public void setModel(String mod) {
        model = mod;
    }

    private void loadTable() throws IOException, ClassNotFoundException {

        TableColumn<Car,String> registrationCol = new TableColumn<>("Registration No.");
        registrationCol.setMinWidth(80);
        registrationCol.setCellValueFactory(new PropertyValueFactory<>("registration"));

        TableColumn<Car, String> makeCol = new TableColumn<>("Make");
        makeCol.setMinWidth(80);
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<Car, String> modelCol = new TableColumn<>("Model");
        modelCol.setMinWidth(80);
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Car, Integer> yearCol = new TableColumn<>("Version");
        yearCol.setMinWidth(80);
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Car, String> editingCol = new TableColumn<>("Editing By");
        editingCol.setMinWidth(80);
        editingCol.setCellValueFactory(new PropertyValueFactory<>("editingBy"));

        carList.getColumns().addAll(registrationCol, makeCol, modelCol, yearCol, editingCol);

        System.out.println("Loading Table done");

        new Thread(() -> {
            if(type == requestType.viewAllCars) {
                listOnAllCar();
            }
            else if(type == requestType.SearchByReg) {
                listOnReg();
            }
            else if(type == requestType.SearchByMake) {
                listOnMake();
            }
            else if(type == requestType.SearchByMakeModel) {
                listOnMakeModel();
            }
        }).start();

    }

    private void listOnReg() {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.SearchByReg);
        request.setRegNo(regNo);
        main.getUtil().write(request);
        responseProcess response = (responseProcess)main.getUtil().read();
        Car car = response.getCar();
        Platform.runLater(() -> carList.getItems().add(car));
    }

    private void listOnAllCar() {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.viewAllCars);
        main.getUtil().write(request);
        responseProcess response = (responseProcess)main.getUtil().read();
        ArrayList<Car> listCar = response.getCars();
        Platform.runLater(() -> carList.getItems().addAll(listCar));
    }

    private void listOnMake() {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.SearchByMake);
        request.setMake(make);
        main.getUtil().write(request);
        responseProcess response = (responseProcess)main.getUtil().read();
        ArrayList<Car> listCar = response.getCars();
        Platform.runLater(() -> carList.getItems().addAll(listCar));
    }

    private void listOnMakeModel() {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.SearchByMakeModel);
        request.setMake(make);
        request.setModel(model);
        main.getUtil().write(request);
        responseProcess response = (responseProcess)main.getUtil().read();
        ArrayList<Car> listCar = response.getCars();
        Platform.runLater(() -> carList.getItems().addAll(listCar));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("In Initialize");
            loadTable();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

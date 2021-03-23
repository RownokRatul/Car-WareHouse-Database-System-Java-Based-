module Car.WareHouse.System {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sample;
    opens serverClient;
    opens DatabasePack;
}
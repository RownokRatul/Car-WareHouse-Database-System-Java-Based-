package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import serverClient.*;

import java.io.IOException;

public class manufacturerDeleteCarController {
    private Main main;

    private String regNo;

    @FXML
    private Button confirmDeleteButton;

    public void setMain(Main m) {
        main = m;
    }

    public void setRegistration(String reg) {
        regNo = reg;
    }

    @FXML
    private void confirmDelete() throws IOException {
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.deleteCar);
        request.setRegNo(regNo);
        main.getUtil().write(request);
        responseProcess response = (responseProcess) main.getUtil().read();
        Alert alert;
        if(response.getSuccessful()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Successfully Deleted.Close The Window!");
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sorry Something Bad Happened! Try again later.");
        }
        alert.showAndWait();

        main.showMainMenuHandler();

    }

}

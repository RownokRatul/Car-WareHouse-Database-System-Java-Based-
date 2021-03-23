package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import serverClient.*;

import java.io.IOException;

public class guestBuyController {
    private Main main;
    private String registration;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField enterTransaction;

    public void setRegistration(String reg) {
        registration = reg;
    }

    public void setMain(Main m ) {
        main = m;
    }

    @FXML
    private void buyConfirm() throws IOException {
        String transactionID = enterTransaction.getText();
        if(!transactionID.equals("")) {
            requestPrcoess request = new requestPrcoess();
            request.setRequestType(requestType.buyCar);
            request.setRegNo(registration);
            main.getUtil().write(request);
            responseProcess response = (responseProcess) main.getUtil().read();
            Alert alert;
            if(response.getSuccessful()) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("CONGRATULATIONS, You've bought this car! KEEP the TransactionID for any inconvenience!");
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Sorry Something Bad Happened! Try again later.");
            }
            alert.showAndWait();

            main.showMainMenuHandler();

        }
    }
}

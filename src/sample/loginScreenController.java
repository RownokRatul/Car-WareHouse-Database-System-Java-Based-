package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import DatabasePack.userLoginInfo;
import serverClient.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginScreenController implements Initializable {

    private Main main;

    @FXML
    private ImageView loginAvatarImage;

    @FXML
    private PasswordField enterPassword;

    @FXML
    private TextField enterUserName;

    @FXML
    private void resetCredential(ActionEvent actionEvent) {
        enterUserName.setText("");
        enterPassword.setText("");
    }

    @FXML
    private void checkLoginCredential(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String userName = enterUserName.getText();
        String password = enterPassword.getText();
        requestPrcoess request = new requestPrcoess();
        request.setRequestType(requestType.loginRequest);
        request.setLoginInfo(new userLoginInfo(userName,password));
        main.getUtil().write(request);
        responseProcess response = (responseProcess) main.getUtil().read();
        if(response.getSuccessful()) {
            main.setManufacturer(userName,password);
            main.showManufacturerMainMenuPage();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unsuccessful Login Attempt");
            alert.setContentText("Failed to Login, check credentials again!");
            alert.showAndWait();
        }
    }

    public void setMain(Main m) {
        main = m;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginAvatarImage.setImage(new Image(Main.class.getResourceAsStream("loginAvatar.png")));
    }


}

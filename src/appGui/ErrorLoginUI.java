package appGui;

import javafx.scene.control.*;
import java.io.*;
import java.net.*;

public class ErrorLoginUI {
    public TextField usernameTextField;
    public Label errorUsername;
    public PasswordField passwordTextField;
    public Label errorPassword;

    public ErrorLoginUI(TextField usernameTextField, Label errorUsername, PasswordField passwordTextField, Label errorPassword) {
        this.usernameTextField = usernameTextField;
        this.errorUsername = errorUsername;
        this.passwordTextField = passwordTextField;
        this.errorPassword = errorPassword;
    }

    public void resetError() {
        usernameTextField.getStyleClass().remove("error");
        passwordTextField.getStyleClass().remove("error");
        errorUsername.setText("");
        errorPassword.setText("");
    }

    public void verifMsgServeur(String msgServ) {
        if (msgServ.equals("false")) {this.showWrongInfo();}
    }

    public void showWrongInfo() {
        usernameTextField.getStyleClass().add("error");
        passwordTextField.getStyleClass().add("error");
        errorPassword.setText("Username and Password do not match");
    }

    public void showError(Exception e) {
        errorPassword.setText("Unable to connect to the server");
    }
}

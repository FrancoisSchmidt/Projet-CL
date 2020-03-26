package appGui;

import javafx.scene.control.*;
import java.net.ConnectException;

public class ErrorRegisterUI {
    public TextField usernameTextField;
    public Label errorUsername;
    public PasswordField passwordTextField;
    public Label errorPassword;
    public PasswordField verifPasswordTextField;
    public Label errorVerifPassword;

    public ErrorRegisterUI(TextField usernameTextField, Label errorUsername, PasswordField passwordTextField, Label errorPassword, PasswordField verifPasswordTextField, Label errorVerifPassword) {
        this.usernameTextField = usernameTextField;
        this.errorUsername = errorUsername;
        this.passwordTextField = passwordTextField;
        this.errorPassword = errorPassword;
        this.verifPasswordTextField = verifPasswordTextField;
        this.errorVerifPassword = errorVerifPassword;
    }

    public void resetError() {
        usernameTextField.getStyleClass().remove("error");
        passwordTextField.getStyleClass().remove("error");
        verifPasswordTextField.getStyleClass().remove("error");
        errorUsername.setText("");
        errorPassword.setText("");
        errorVerifPassword.setText("");
    }

    public void verifMsgServeur(String msgServ) {
        if (msgServ.equals("false")) {this.showUsernameAlreadyUsed();}
        else if (msgServ.equals("conf")) {this.showPasswordVerif();}
    }

    public void showPasswordVerif() {
        //TODO - CHECK IF PASSWORD IS GOOD
        passwordTextField.getStyleClass().add("error");
        verifPasswordTextField.getStyleClass().add("error");
        errorVerifPassword.setText("Passwords does not match");
    }

    public void showUsernameAlreadyUsed() {
        //TODO - IF USERNAME IS USED
        usernameTextField.getStyleClass().add("error");
        errorUsername.setText("Username already used");
    }

    /* ERROR MANAGEMENT */
    public void showError(Exception e) {
        errorVerifPassword.setText("Username already used");
    }
}

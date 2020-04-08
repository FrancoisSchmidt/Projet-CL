package appGui;

import Register.ClientRegister;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import launchPattern.MonGrosClient;
import login.ClientLogin;

public class LoginUI extends Application implements ILoginUI {
    public VBox mainPane;
    public Stage primaryStage;
    public String unNomServeur = "localhost";
    public int unNumero = 6666;
    public MonGrosClient monGrosClient;

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            mainPane = new VBox();
            mainPane.getStyleClass().add("loginWin");
            this.primaryStage.setTitle("LampaulSkype");
            Scene myScene = new Scene(mainPane, 300, 400);
            myScene.getStylesheets().add(getClass().getResource("testSty.css").toExternalForm());
            this.primaryStage.setScene(myScene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();
            monGrosClient = new MonGrosClient(unNomServeur, unNumero);
            monGrosClient.connecterAuServeur();


            LoginWin();
        } catch(Exception e) {e.printStackTrace();}
    }

    public void LoginWin() throws Exception {
        mainPane = new VBox();
        mainPane.getStyleClass().add("loginWin");
        //--TEXTFIELDS--
        TextField usernameTextField = new TextField();
        usernameTextField.getStyleClass().add("loginTextField");
        usernameTextField.setPromptText("Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.getStyleClass().add("loginTextField");
        passwordTextField.setPromptText("password");
        passwordTextField.setMinSize(250,40);
        //textfield keypress events
        usernameTextField.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {
                passwordTextField.requestFocus();}
        }));
        //Error labels
        Label errorUsername = new Label("");
        errorUsername.getStyleClass().add("labelError");
        errorUsername.setAlignment(Pos.CENTER_LEFT);
        Label errorPassword = new Label("");
        errorPassword.getStyleClass().add("labelError");
        errorPassword.setAlignment(Pos.CENTER_LEFT);

        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(usernameTextField,errorUsername,passwordTextField,errorPassword);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(10);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //--BUTTONS--
        Button butLogin = new Button("Login");
        butLogin.setMinSize(150,40);
        butLogin.setOnAction(actionevent -> {
            //creating Login Protocole
            String log = usernameTextField.getText();
            String pwd = passwordTextField.getText();
            //TODO - deal with unNomServeur and port unNumero
            //MonGrosClient monGrosClient = new MonGrosClient(unNomServeur, unNumero);
            ClientLogin protocoleLog = new ClientLogin(unNomServeur, unNumero, log, pwd);
            //En argument de new ClientLogin(), mettre la socIn et la socOut qui ont été crées dans MonGrosClient
            ErrorLoginUI errorLogUI = new ErrorLoginUI(usernameTextField,errorUsername,passwordTextField,errorPassword);

            //Trying to connect
            try {
                //Reset error borders
                errorLogUI.resetError();
                //Setting right protocol

                monGrosClient.transmettreOrdre("login");
                //Connecting to server
                //protocoleLog.connecterAuServeur(); Pas nécessaire de se reconnecter
                //Une fois connecté au serveur, c'est comme si on avait ouvert un fichier côté client, et on peut y lire/ecrire
                //Si on s'y deconnecte, on ferme le fichier, dès qu'on se reconnecte, on ouvre un NOUVEAU fichier
                String msgServeur = protocoleLog.transmettreLogin(monGrosClient.getSocOut(),monGrosClient.getSocIn());
                errorLogUI.verifMsgServeur(msgServeur);
                //protocoleLog.deconnecterDuServeur();
                if (msgServeur.equals("true")) {
                    new MainUI(log, monGrosClient).start(new Stage());
                    primaryStage.close();
                }
            } catch (Exception e) {errorLogUI.showError(e);}

        });
        Button butNewAccount = new Button("Create new Account");
        butNewAccount.setMinSize(150,40);
        butNewAccount.setOnAction(actionevent -> {
            try {RegisterWin();} catch (Exception e) {e.printStackTrace();}
        });
        //Button Area
        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(butLogin,butNewAccount);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);


        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        Scene myScene = new Scene(mainPane, 300, 400);
        myScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void RegisterWin() throws Exception {
        mainPane = new VBox();
        mainPane.getStyleClass().add("loginWin");
        //--TEXTFIELDS--
        TextField usernameTextField = new TextField();
        usernameTextField.getStyleClass().add("loginTextField");
        usernameTextField.setPromptText("Enter your Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.getStyleClass().add("loginTextField");
        passwordTextField.setPromptText("Enter your Password");
        passwordTextField.setMinSize(250,40);
        PasswordField verifPasswordTextField = new PasswordField();
        verifPasswordTextField.getStyleClass().add("loginTextField");
        verifPasswordTextField.setPromptText("Confirm Password");
        verifPasswordTextField.setMinSize(250,40);
        //textfield keypress events
        usernameTextField.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {passwordTextField.requestFocus();}
        }));
        passwordTextField.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {verifPasswordTextField.requestFocus();}
        }));
        //Error labels
        Label errorUsername = new Label("");
        errorUsername.getStyleClass().add("labelError");
        errorUsername.setAlignment(Pos.CENTER_LEFT);
        Label errorPassword = new Label("");
        errorPassword.getStyleClass().add("labelError");
        errorPassword.setAlignment(Pos.CENTER_LEFT);
        Label errorVerifPassword = new Label("");
        errorVerifPassword.getStyleClass().add("labelError");
        errorVerifPassword.setAlignment(Pos.CENTER_LEFT);
        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(usernameTextField,errorUsername,passwordTextField,errorPassword,verifPasswordTextField,errorVerifPassword);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(5);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons
        Button butCreateAccount = new Button("Create New Account");
        butCreateAccount.setMinSize(150,40);
        butCreateAccount.setOnAction(actionevent -> {
            String log = usernameTextField.getText();
            String pwd = passwordTextField.getText();
            String verifpwd = verifPasswordTextField.getText();
            ClientRegister protocoleReg = new ClientRegister(unNomServeur, unNumero, log, pwd, verifpwd);
            //MonGrosClient monGrosClient = new MonGrosClient(unNomServeur , unNumero, "register");
            ErrorRegisterUI errorRegUI = new ErrorRegisterUI(usernameTextField,errorUsername,passwordTextField,errorPassword,verifPasswordTextField,errorVerifPassword);
            try {
                //Reset error borders
                errorRegUI.resetError();
                //Setting right protocol
                //monGrosClient.connecterAuServeur();
                this.monGrosClient.transmettreOrdre("register");
                //monGrosClient.deconnecterDuServeur();
                //Connecting to server
                //protocoleReg.connecterAuServeur();
                System.out.println("NIQUE TA MERE");
                String msgServeur = protocoleReg.transmettreReg(monGrosClient.getSocOut(),monGrosClient.getSocIn());
                errorRegUI.verifMsgServeur(msgServeur);
                //protocoleReg.deconnecterDuServeur();
            }
            catch (Exception e) {
                //this is a test
                errorRegUI.showError(e);
                System.out.print("dommageFromage");}
        });
        Button butLogin = new Button("I already have an account");
        butLogin.setMinSize(150,40);
        butLogin.setOnAction(actionevent -> {
            try {LoginWin();} catch (Exception e) {e.printStackTrace();}
        });
        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(butCreateAccount,butLogin);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);


        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        Scene myScene = new Scene(mainPane, 300, 400);
        myScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}



//fixing things
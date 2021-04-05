package client.controllers;

import client.sample.ErrorWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Button backButton;

    @FXML
    private Button regB;

    public static String login;

    public void signIn() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("login " + userLogin.getText() + " " + userPassword.getText());
        String resultString = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (resultString.equals("АдминистраторАктивен")) {
            userPassword.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
        } else if (resultString.equals("ПользовательАктивен")) {
            userPassword.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("userMenu", "");
        } else if (resultString.equals("ПользовательУволен") || resultString.equals("АдминистраторУволен")) {
            ErrorWindow.display("Этот пользователь больше не может пользоваться системой!");
        } else {
            ErrorWindow.display("Ошибка авторизации");
        }
        login = userLogin.getText();
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void registration() {
        regB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("registr", "");
    }
}

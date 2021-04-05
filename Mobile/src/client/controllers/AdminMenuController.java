package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenuController {
    @FXML
    private Button userButton;

    @FXML
    private Button yearButton;

    @FXML
    private Button brandButton;

    @FXML
    private Button osButton;

    @FXML
    private Button statButton;

    @FXML
    private Button goodsButton;

    @FXML
    private Button backButton;

    @FXML
    void userButt() {
        userButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("user", "");
    }

    @FXML
    void yearButt() {
        yearButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("year", "");
    }

    @FXML
    void brandButt() {
        brandButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
    }

    @FXML
    void osButt() {
        osButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("os", "");
    }

    @FXML
    void goodsButt() {
        goodsButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("goods", "");
    }

    @FXML
    void statButt() {
        statButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stat", "");
    }


    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
    }
}

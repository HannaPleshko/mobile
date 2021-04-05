package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserMenuController {

    @FXML
    private Button goodsButton;

    @FXML
    private Button accountingButton;

    @FXML
    private Button backButton;

    @FXML
    private Button presenceButton;

    @FXML
    void goodsButt() {
        goodsButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userGoods", "");

    }

    @FXML
    void accountingButt() {
        accountingButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userAccounting", "");

    }

    @FXML
    void presenceButt() {
        presenceButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userPresence", "");

    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
    }
}

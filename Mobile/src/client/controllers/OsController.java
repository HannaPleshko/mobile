package client.controllers;

import client.entityClass.Goods;
import client.sample.ErrorWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class OsController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Goods> osTable;

    @FXML
    private TableColumn<Goods, String> osColumn;

    @FXML
    private TextField os;


    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getOs");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Goods goods1 = new Goods();
            goods1.setOs(list.get(i));
            goods.add(goods1);
        }
        osColumn.setCellValueFactory(new PropertyValueFactory<>("os"));
        osTable.setItems(goods);
    }

    @FXML
    void add() {
        if (os.getText().equals("")) {
            ErrorWindow.display("Введите все данные!");
            return;
        }
        if (os.getLength() > 20) {
            ErrorWindow.display("Слишком длинное название ОС!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("addOs " + os.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Операционная система добавлена успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    public void del() {
        if (osTable.getSelectionModel().getSelectedItem() == null) {
            ErrorWindow.display("Выберите ОС для удаления!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("delOs " +
                    osTable.getSelectionModel().getSelectedItem().getOs());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                ErrorWindow.display("Операционная система удалена успешно!");
            } else {
                ErrorWindow.display("Ошибка удаления!");
            }
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}

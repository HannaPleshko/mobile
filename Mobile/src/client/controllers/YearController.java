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

public class YearController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Goods> yearTable;

    @FXML
    private TableColumn<Goods, String> yearColumn;

    @FXML
    private TextField year;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getYear");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Goods goods1 = new Goods();
            goods1.setYear(Integer.valueOf(list.get(i)));
            goods.add(goods1);
        }
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearTable.setItems(goods);
    }

    @FXML
    void add() {
        if (year.getText().equals("")) {
            ErrorWindow.display("Введите все данные!");
            return;
        }
        if (year.getLength() != 4) {
            ErrorWindow.display("Неверный формат данных!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("addYear " + year.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Год выпуска добавлен успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    public void del() {
        if (yearTable.getSelectionModel().getSelectedItem() == null) {
            ErrorWindow.display("Год для удаления!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("delYear " +
                    yearTable.getSelectionModel().getSelectedItem().getYear());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                ErrorWindow.display("Год выпуска удален успешно!");
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

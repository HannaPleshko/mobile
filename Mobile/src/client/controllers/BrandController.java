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
import java.util.logging.Logger;

public class BrandController {
    private static Logger log = Logger.getLogger(BrandController.class.getName());
    @FXML
    private Button backButton;

    @FXML
    private TableView<Goods> brandTable;

    @FXML
    private TableColumn<Goods, String> brandColumn;

    @FXML
    private TextField brand;


    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getBrand");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Goods goods1 = new Goods();
            goods1.setBrand(list.get(i));
            goods.add(goods1);
        }
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandTable.setItems(goods);
    }

    @FXML
    void add() {
        if (brand.getText().equals("")) {
            ErrorWindow.display("Введите все данные!");
            return;
        }
        if (brand.getLength() > 20) {
            ErrorWindow.display("Слишком длинное название!");
            log.info("Произведена попытка ввода большого значения!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("addBrand " + brand.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Бренд добавлен успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    public void del() {
        if (brandTable.getSelectionModel().getSelectedItem() == null) {
            ErrorWindow.display("Выберите бренд для удаления!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("delBrand " +
                    brandTable.getSelectionModel().getSelectedItem().getBrand());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                ErrorWindow.display("Бренд удален успешно!");
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

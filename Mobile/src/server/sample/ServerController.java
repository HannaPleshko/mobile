package server.sample;

import client.sample.ErrorWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.Database.DatabaseCommands;
import server.server.ServerInstance;

import java.util.logging.Logger;

public class ServerController {

    @FXML
    private TextField database;
    @FXML
    private TextField login;
    @FXML
    private TextField psswrd;

    @FXML
    private TextField prt;
    @FXML
    private Button srvOn;
    @FXML
    private Button srvOff;

    private static Logger log = Logger.getLogger(ServerController.class.getName());

    public void connectDB() {
        DatabaseCommands.connect(database.getText(), login.getText(), psswrd.getText(), prt.getText());
        ErrorWindow.display("Вы подключились к базе данных!");
        log.info("Успешное подключение к базе данных!");
    }

    public void startServer() {
        new Thread(ServerInstance.INSTANCE.getInstance()).start();
        srvOn.setVisible(false);
        srvOff.setVisible(true);
        log.info("Сервер запущен!");
    }

    public void stopServer() {
        ServerInstance.INSTANCE.getInstance().stop();
        srvOn.setVisible(true);
        srvOff.setVisible(false);
        log.info("Сервер остановлен!");
    }
}
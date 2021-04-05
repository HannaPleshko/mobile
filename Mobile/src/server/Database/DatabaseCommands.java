package server.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseCommands {
    private static Connection connection;
    private static Logger log = Logger.getLogger(DatabaseCommands.class.getName());
    /*
    Подключение к базе данных
     */
    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Ошибка подключения к базе данных! ", e);
            }
            sqlexc.printStackTrace();
        }
    }

    /*
    Команды, которые выполняются в базе данных
*/
    public static boolean addUser(String name, String surname, String login, String password, String role, String status) {
        String insertClient = "INSERT INTO mobile.user (name, surname, login, password, role, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementClient.setString(5, role);
            preparedStatementClient.setString(6, status);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static String login(String login, String password) {
        ResultSet resultSet;
        String role = "";
        String status = "";

        String select = "SELECT * FROM user WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
                status = resultSet.getString("status");
            } else {
                role = "";
                status = "";
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода ", e);
        }
        return role + status;
    }

    public static ArrayList<String> getUser() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM mobile.user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("login")).append(" ").
                        append(resultSet.getString("role")).append(" ").
                        append(resultSet.getString("status"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);

        }
        return list;
    }

    public static boolean off(String status) {
        String[] infoDetails = status.split(" ", 4);
        String insert = "UPDATE mobile.user SET status=? WHERE login=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getBrand() {
        ResultSet resultSet;
        ArrayList<String> brand = new ArrayList<>(0);

        String select = "SELECT * FROM mobile.brand";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                brand.add(resultSet.getString("brand"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);

        }
        return brand;
    }

    public static boolean addBrand(String brand) {
        String insert = "INSERT INTO mobile.brand (brand) VALUES (?)";
        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insert);
            preparedStatementClient.setString(1, brand);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static boolean delBrand(String brand) {
        String delete = "DELETE FROM mobile.brand WHERE brand=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, brand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static ArrayList<String> getYear() {
        ResultSet resultSet;
        ArrayList<String> country = new ArrayList<>(0);

        String select = "SELECT * FROM mobile.year";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.add(resultSet.getString("year"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
        }
        return country;
    }

    public static boolean addYear(String year) {
        String insert = "INSERT INTO mobile.year (year) VALUES (?)";
        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insert);
            preparedStatementClient.setString(1, year);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static boolean delYear(String year) {
        String delete = "DELETE FROM mobile.year WHERE year=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, year);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static ArrayList<String> getOs() {
        ResultSet resultSet;
        ArrayList<String> country = new ArrayList<>(0);

        String select = "SELECT * FROM mobile.os";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.add(resultSet.getString("os"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
        }
        return country;
    }

    public static boolean addOs(String os) {
        String insert = "INSERT INTO mobile.os (os) VALUES (?)";
        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insert);
            preparedStatementClient.setString(1, os);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static boolean delOs(String os) {
        String delete = "DELETE FROM mobile.os WHERE os=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, os);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static ArrayList<String> getGoods() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM mobile.goods";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("brand")).append(" ").
                        append(resultSet.getString("model")).append(" ").
                        append(resultSet.getString("type")).append(" ").
                        append(resultSet.getString("year")).append(" ").
                        append(resultSet.getString("os")).append(" ").
                        append(resultSet.getString("vendercode")).append(" ").
                        append(resultSet.getString("cost"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);

        }
        return list;
    }

    public static boolean addGoods(String mobile) {
        String[] infoDetails = mobile.split(" ", 9);
        String insertClient = "INSERT INTO mobile.goods (brand, model, type, year, os, vendercode, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, infoDetails[1]);
            preparedStatementClient.setString(2, infoDetails[2]);
            preparedStatementClient.setString(3, infoDetails[3]);
            preparedStatementClient.setString(4, infoDetails[4]);
            preparedStatementClient.setString(5, infoDetails[5] + " " + infoDetails[6]);
            preparedStatementClient.setString(6, infoDetails[7]);
            preparedStatementClient.setString(7, infoDetails[8]);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static boolean delGoods(String vendercode) {
        String delete = "DELETE FROM mobile.goods WHERE vendercode=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, vendercode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static ArrayList<String> getStatistic() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT type FROM mobile.goods";
        double product = 0;
        double product1 = 0;
        double product2 = 0;
        double product3 = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("type").equalsIgnoreCase("Смартфон")) product++;
                if (resultSet.getString("type").equalsIgnoreCase("Раскладушка")) product1++;
                if (resultSet.getString("type").equalsIgnoreCase("Кнопочный")) product2++;
                if (resultSet.getString("type").equalsIgnoreCase("Слайдер")) product3++;
            }
            double productPart = product / (product + product1 + product2 + product3);
            double product1Part = product1 / (product + product1 + product2 + product3 );
            double product2Part = product2 / (product + product1 + product2 + product3 );
            double product3Part = product3 / (product + product1 + product2 + product3 );
            list.add(Double.toString(productPart));
            list.add(Double.toString(product1Part));
            list.add(Double.toString(product2Part));
            list.add(Double.toString(product3Part));
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
        }
        return list;
    }

    public static ArrayList<String> getAccounting() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM mobile.accounting";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("id_accounting")).append(" ").
                        append(resultSet.getString("login")).append(" ").
                        append(resultSet.getString("vendercode")).append(" ").
                        append(resultSet.getString("date")).append(" ").
                        append(resultSet.getString("amount"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);

        }
        return list;
    }

    public static ArrayList<String> getVendercode() {
        ResultSet resultSet;
        ArrayList<String> country = new ArrayList<>(0);

        String select = "SELECT vendercode FROM mobile.presence";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.add(resultSet.getString("vendercode"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
        }
        return country;
    }

    public static boolean addAccounting(String login, String vendercode, String date, String amount) {
        String insertClient = "INSERT INTO mobile.accounting (login, vendercode, date, amount) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, login);
            preparedStatementClient.setString(2, vendercode);
            preparedStatementClient.setString(3, date);
            preparedStatementClient.setString(4, amount);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static boolean addPresence(String vendercode, String amount) {
        String insertClient = "INSERT INTO mobile.presence (vendercode, amount) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, vendercode);
            preparedStatementClient.setString(2, amount);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }

    public static ArrayList<String> getPresence() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM mobile.presence";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("vendercode")).append(" ").
                        append(resultSet.getString("amount"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);

        }
        return list;
    }

    public static ArrayList<String> getVendercodePresence() {
        ResultSet resultSet;
        ArrayList<String> country = new ArrayList<>(0);

        String select = "SELECT vendercode FROM mobile.goods";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.add(resultSet.getString("vendercode"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
        }
        return country;
    }

    public static boolean setPresence(String presence) {
        String[] infoDetails = presence.split(" ", 4);
        String insert = "UPDATE mobile.presence SET presence.amount=? WHERE vendercode=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Ошибка выполнения метода! ", e);
            return false;
        }
        return true;
    }
}
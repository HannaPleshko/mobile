package server.server;

import server.Database.DatabaseCommands;

public class CommandsFromClient {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            /*
            Команды с клиента, которые должна выполнить база данных
           */
            case "addUser":
                commands = command.split(" ", 7);
                result = DatabaseCommands.addUser(commands[1], commands[2], commands[3], commands[4], commands[5], commands[6]);
                break;
            case "login":
                commands = command.split(" ", 3);
                result = DatabaseCommands.login(commands[1], commands[2]);
                break;
            case "getUser":
                result = DatabaseCommands.getUser();
                break;
            case "off":
                result = DatabaseCommands.off(command);
                break;
                /*
            Команды для бренда
           */
            case "getBrand":
                result = DatabaseCommands.getBrand();
                break;
            case "delBrand":
                commands = command.split(" ", 2);
                result = DatabaseCommands.delBrand(commands[1]);
                break;
            case "addBrand":
                commands = command.split(" ", 2);
                result = DatabaseCommands.addBrand(commands[1]);
                break;
                /*
            Команды для года выпуска
           */
            case "getYear":
                result = DatabaseCommands.getYear();
                break;
            case "delYear":
                commands = command.split(" ", 2);
                result = DatabaseCommands.delYear(commands[1]);
                break;
            case "addYear":
                commands = command.split(" ", 2);
                result = DatabaseCommands.addYear(commands[1]);
                break;
                /*
            Команды для года выпуска
           */
            case "getOs":
                result = DatabaseCommands.getOs();
                break;
            case "delOs":
                commands = command.split(" ", 2);
                result = DatabaseCommands.delOs(commands[1]);
                break;
            case "addOs":
                commands = command.split(" ", 2);
                result = DatabaseCommands.addOs(commands[1]);
                break;
                /*
            Команды для продукции
           */
            case "getGoods":
                result = DatabaseCommands.getGoods();
                break;
            case "delGoods":
                commands = command.split(" ", 2);
                result = DatabaseCommands.delGoods(commands[1]);
                break;
            case "addGoods":
                result = DatabaseCommands.addGoods(command);
                break;
                /*
                Команда для статистики
                 */
            case "getStatistic":
                result = DatabaseCommands.getStatistic();
                break;
                /*
                Команда для учета товаров
                 */
            case "getAccounting":
                result = DatabaseCommands.getAccounting();
                break;
            case "getVendercode":
                result = DatabaseCommands.getVendercode();
                break;
            case "addAccounting":
                commands = command.split(" ", 5);
                result = DatabaseCommands.addAccounting(commands[1], commands[2], commands[3], commands[4]);
                break;

            case "addPresence":
                commands = command.split(" ", 3);
                result = DatabaseCommands.addPresence(commands[1], commands[2]);
                break;
            case "getPresence":
                result = DatabaseCommands.getPresence();
                break;
            case "getVendercodePresence":
                result = DatabaseCommands.getVendercodePresence();
                break;
            case "setPresence":
                result = DatabaseCommands.setPresence(command);
                break;

        }
        return result;
    }

}

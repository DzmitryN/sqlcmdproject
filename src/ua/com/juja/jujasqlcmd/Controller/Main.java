package ua.com.juja.jujasqlcmd.Controller;

import ua.com.juja.jujasqlcmd.View.Console;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.JDBCDatabaseManager;

/**
 * Created by Dima1 on 09.12.2017.
 */
public class Main {
    public static void main(String[] args) {

        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();

        MainController mainController = new MainController(view, manager);
        mainController.run();
    }
}

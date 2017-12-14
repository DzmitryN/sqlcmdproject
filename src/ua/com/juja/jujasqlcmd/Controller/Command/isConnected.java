package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

/**
 * Created by Dima1 on 13.12.2017.
 */
public class isConnected implements Command {

    private View view;
    private DatabaseManager manager;

    public isConnected(View view, DatabaseManager manager) {

        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected(command);
    }

    @Override
    public void process(String command) {
        view.write(String.format("Вы не можете пользоваться командой '%s', вам необходимо установить подключение к базе" +
                " с помощью команды Connect|database|userName|password", command));
    }
}

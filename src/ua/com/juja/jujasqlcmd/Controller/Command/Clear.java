package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

/**
 * Created by Dima1 on 17.12.2017.
 */
public class Clear implements Command {
    private View view;
    private DatabaseManager manager;

    public Clear(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.startsWith("Clear|");
    }

    @Override
    public void process(String command) {
        String [] data=command.split("\\|");
        if(data.length!=2){
            throw new IllegalArgumentException("Формат комаанды 'Clear|tableName', а было введено" + command);
        }
        manager.clear(data[1]);
        view.write(String.format("Таблица %s была успешно очищена!", data[1]));
    }
}

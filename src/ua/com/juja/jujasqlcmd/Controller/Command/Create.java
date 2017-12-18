package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

/**
 * Created by Dima1 on 17.12.2017.
 */
public class Create implements Command {
    private View view;
    private DatabaseManager manager;

    public Create(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.startsWith("Create|");
    }

    @Override
    public void process(String command) {
        String [] data = command.split("\\|");
        if(data.length %2 != 0){
            throw new IllegalArgumentException("Количество параметров должно быть четным! Формат запроса должен быть " +
                    "'Create|tableName|column1|value1|column2|value2....columnN|valueN', а ты ввел " + command);
        }

        String tableName=data[1];
        DataSet dataset = new DataSet();
        for (int i = 1; i < data.length/2; i++) {
            String column = data[i*2];
            String value = data[i*2+1];
            dataset.put(column,value);
        }

        manager.create(tableName, dataset);
        view.write(String.format("Запись %s в таблице %s была успешно создана!", dataset, tableName));
    }
}

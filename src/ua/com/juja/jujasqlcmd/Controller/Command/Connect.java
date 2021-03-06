package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;


public class Connect implements Command {

    private static String COMMAND_SAMPLE = "Connect|jujaproject|posgres1|123456";
    private View view;
    private DatabaseManager manager;

    public Connect(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("Connect|");
    }

    @Override
    public void process(String command) {
       // while(true) {
              /*view.write("Введите имя базы данных, имя пользователя и пароль в формате Connect|database|userName|password.");
                String info = view.read();*/
                String[] data = command.split("[|]");
                if(data.length != countLength()){
                    throw new IllegalArgumentException(String.format("Неверно количество параметров, разделенных символом |, количество" +
                            " должно быть %s, вы ввели: %s", countLength(), data.length + "."));

                }
                String database = data[1];
                String userName = data[2];
                String password = data[3];

                manager.connect(database, userName, password);
                view.write("Connected!");
               // break;


        //}

    }

    public int countLength() {
        return COMMAND_SAMPLE.split("\\|").length;
    }


    }


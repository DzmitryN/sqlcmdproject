package ua.com.juja.jujasqlcmd.Controller;


import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;


/**
 * Created by Dima1 on 08.12.2017.
 */
public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager){
        this.view=view;
        this.manager=manager;
    }

    public void run(){
        connectToDb();
    }

    private void connectToDb() {
        //View view = new Console();
        //DatabaseManager manager = new InMemoryDatabaseManager();
        //DatabaseManager manager = new JDBCDatabaseManager();
        view.write("Привет, дорогой пользователь!");
        while(true) {
          try {
            view.write("Введите имя базы данных, имя пользователя и пароль в формате database|userName|password.");
            String info = view.read();
            String[] data = info.split("[|]");
                if(data.length !=3){
                throw new IllegalArgumentException("Неверно количество параметров, разделенных символом |, количество должно быть 3, " +
                        "вы ввели: "+ data.length + ".");

                }
            String database = data[0];
            String userName = data[1];
            String password = data[2];

            manager.connect(database, userName, password);
            break;

          } catch (Exception e) {
              printError(e);


            }
        }
        view.write("Connected!");
    }

    private void printError(Exception e) {

        String message =e.getMessage();

        if(e.getCause() != null) {
            message += " " + e.getCause().getMessage();
        }
        else {

        }
            view.write("Подключение невозможно по причине: " + message);
            view.write("Повторите попытку.");
    }
}


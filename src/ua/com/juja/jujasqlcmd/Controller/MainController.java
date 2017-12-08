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
            view.write("Введите имя базы данных, имя пользователя и пароль в формате database|userName|password.");
            String info = view.read();
            String[] data = info.split("[|]");
            String database = data[0];
            String userName = data[1];
            String password = data[2];

            try {
                manager.connect(database, userName, password);
                break;
            } catch (Exception e) {
                if(e.getCause()!=null) {
                    view.write("Подключение невозможно по причине: " + e.getMessage() + " " + e.getCause().getMessage());
                    view.write("Повторите попытку.");
                }

            }
        }
        view.write("Connected!");
    }
}

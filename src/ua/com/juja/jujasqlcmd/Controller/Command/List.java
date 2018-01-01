package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import java.util.Arrays;


public class List implements Command{

    private View view;
    private DatabaseManager manager;

    public List(View view, DatabaseManager manager){
        this.view=view;
        this.manager=manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.equals("List");
    }

    @Override
    public void process(String command) {
        String [] list = manager.getTableNames();
        String message = Arrays.toString(list);
        view.write(message);
    }
}

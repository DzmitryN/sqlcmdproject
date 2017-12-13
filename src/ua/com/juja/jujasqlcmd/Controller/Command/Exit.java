package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;
import static java.lang.System.exit;

/**
 * Created by Dima1 on 13.12.2017.
 */
public class Exit implements Command {

    public View view;


    public Exit (View view){
        this.view=view;

    }

    @Override
    public boolean canProcess(String command) {
        return (command.equals("Exit"));
    }

    @Override
    public void process(String command) {
        view.write("Всего хорошего!");
        exit(0);

    }
}

package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;


public class Unsupported implements Command {

    private View view;

    public Unsupported(View view) {
        this.view=view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Введена несуществующая команда: " + command);
    }
}

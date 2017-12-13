package ua.com.juja.jujasqlcmd.Controller.Command;

/**
 * Created by Dima1 on 13.12.2017.
 */
public interface Command {

    boolean canProcess(String command);

    void process(String command);
}

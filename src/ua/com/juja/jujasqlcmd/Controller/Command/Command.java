package ua.com.juja.jujasqlcmd.Controller.Command;


public interface Command {

    boolean canProcess(String command);

    void process(String command);
}

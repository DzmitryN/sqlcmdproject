package ua.com.juja.jujasqlcmd.Controller.Command;

import ua.com.juja.jujasqlcmd.View.View;

/**
 * Created by Dima1 on 13.12.2017.
 */
public class Help implements Command {

   private View view;

   public Help(View view){
       this.view=view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("Help");
    }

    @Override
    public void process(String command) {
        view.write("Существующие команды: ");
        view.write("\tHelp");
        view.write("\t\t для помощи.");

        view.write("\tConnect|database|userName|password");
        view.write("\t\t для подключения к существующей базе данных.");

        view.write("\tList");
        view.write("\t\t для получения списка всех таблиц базы.");

        view.write("\tClear|tableName");//TODO double check asking user
        view.write("\t\t для очистки всей таблицы.");

        view.write("\tCreate|tableName|column1|value1|column2|value2....columnN|valueN");
        view.write("\t\t для создания записи в таблицы.");

        view.write("\tFind|tableName");
        view.write("\t\t для получения содержимого таблицы 'tableName'");

        view.write("\tExit");
        view.write("\t\t для выхода из программы.");

    }
}

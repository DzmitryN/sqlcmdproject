package ua.com.juja.jujasqlcmd.Controller;


import ua.com.juja.jujasqlcmd.Controller.Command.*;
import ua.com.juja.jujasqlcmd.View.View;
//import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;


/**
 * Created by Dima1 on 08.12.2017.
 */
public class MainController {

    private View view;
    private DatabaseManager manager;
    private Command [] commands;

    public MainController(View view, DatabaseManager manager){
        this.view=view;
        this.manager=manager;
        this.commands = new Command[]{
                new Connect(view, manager),
                new Help(view),
                new Exit(view),
                new isConnected(view,manager),
                new Clear(view, manager),
                new Create(view, manager),
                new List(view, manager),
                new Find(view, manager),
                new Unsupported(view)};
    }

    public void run() {
        view.write("Привет, дорогой пользователь!");
        view.write("Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.");
        //connectToDb();
    try {
        doWork();
        } catch(ExitException e){
          //doNothing
        }
    }

    public void doWork() {
        while (true) {
            String input = view.read();
            if(input==null){
                new Exit(view).process(input);
                break;
            }

            for (Command result : commands) {
                if (result.canProcess(input)) {
                    try {
                        result.process(input);
                        break;
                    } catch (Exception e) {
                        if (e instanceof ExitException) {
                            throw e;}
                        printError(e);

                        break;
                    }

                }
            }
            view.write("Введи команду(или Help для помощи): ");
            //break;
        }
    }
        private void printError(Exception e) {

            String message =e.getMessage();
            //Throwable cause = e.getCause();
            if(e.getCause() != null) {
                message += " " + e.getCause().getMessage();
            }
            else {
                message+=" ";
            }
            view.write("Подключение невозможно по причине: " + message);
            view.write("Повторите попытку.");
        }
    }
           /* } else if (commands[2].canProcess(command)) {
                commands[2].process(command);
            } else if (commands[3].canProcess(command)) {
               commands[3].process(command);
            } else if (commands[0].canProcess(command)) {
                commands[0].process(command);
            }

                view.write("Введена несуществующая команда: " + input);

        }*/

   /* private void connectToDb() {


        while(true) {
            try {

                String info = view.read();
                String[] data = info.split("[|]");
                if(data.length !=4){
                    throw new IllegalArgumentException("Неверно количество параметров, разделенных символом |, количество" +
                            " должно быть 4, вы ввели: "+ data.length + ".");

                }
                String database = data[1];
                String userName = data[2];
                String password = data[3];

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
    }*/
   /* private void doFind(String command) {
        String [] data = command.split("\\|");
        String tableName = data[1];

        String [] tableColumns=manager.getTableColumns(tableName);
        printHeader(tableColumns);

        DataSet [] tableData = manager.getTableData(tableName);
        printTable(tableData);


    }

    private void printTable(DataSet[] tableData) {

        for(DataSet row : tableData){
            printRow(row);
        }
        
    }

    private void printRow(DataSet row) {
        Object [] values = row.getValues();
        String result= "|";
        for(Object value : values){
            result += value +"|";
        }
        view.write(result);
    }


    private void printHeader(String[] tableColumns) {

        String result= "|";
        for(String name : tableColumns){
            result += name +"|";
        }
      view.write(result);
    }*/


    /*private void doExit() {
        view.write("Всего хорошего!");
        exit(0);
    }*/


  /*  private void doList() {
        String [] list = manager.getTableNames();
        String message = Arrays.toString(list);
        view.write(message);
    }*/

    /*private void doHelp() {
        view.write("Существующие команды: ");
        view.write("\tHelp");
        view.write("\t\t для помощи.");

        view.write("\tList");
        view.write("\t\t для получения списка всех таблиц базы.");

        view.write("\tFind|tableName");
        view.write("\t\t для получения содержимого таблицы 'tableName'");

        view.write("\tExit");
        view.write("\t\t для выхода из программы.");
    }
*/




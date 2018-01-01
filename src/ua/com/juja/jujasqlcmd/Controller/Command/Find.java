package ua.com.juja.jujasqlcmd.Controller.Command;


import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;


public class Find implements Command{

    private View view;
    private DatabaseManager manager;

    public Find(View view, DatabaseManager manager) {
        this.view = view;
        this.manager=manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("Find|");
    }

    @Override
    public void process(String command) {
        String [] data = command.split("\\|");
        String tableName = data[1];
            String[] tableColumns = manager.getTableColumns(tableName);
            printHeader(tableColumns);

            DataSet[] tableData = manager.getTableData(tableName);
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
    }

}


package ua.com.juja.jujasqlcmd.Controller.Command;


import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import java.util.*;


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
         if(data.length!=2){
            throw new IllegalArgumentException("Формат команды 'Find|tableName', а было введено " + command);
        }
        String tableName = data[1];
            Set<String> tableColumns = manager.getTableColumns(tableName);
            printHeader(tableColumns);

            LinkedList<DataSet> tableData = (LinkedList<DataSet>) manager.getTableData(tableName);
            printTable(tableData);



    }

    private void printTable(LinkedList<DataSet> tableData) {

        for(DataSet row : tableData){
            printRow(row);
        }

    }

    private void printRow(DataSet row) {
        java.util.List<Object> values = row.getValues();
        String result= "|";
        for(Object value : values){
            result += value +"|";
        }
        view.write(result);
    }


    private void printHeader(Set<String> tableColumns) {

        String result= "|";
        for(String name : tableColumns){
            result += name +"|";
        }
        view.write(result);
    }

}


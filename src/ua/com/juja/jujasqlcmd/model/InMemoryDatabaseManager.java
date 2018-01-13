package ua.com.juja.jujasqlcmd.model;


import java.util.*;


public class InMemoryDatabaseManager implements DatabaseManager {

   public static final String TABLE_NAME = "user, test";

    private LinkedList<DataSet> data = new LinkedList<DataSet>();

    @Override
    public LinkedList<DataSet> getTableData(String tableName) {
        validateTable(tableName);

        return data;
    }

    private void validateTable(String tableName) {
        if(!"user".equals(tableName)){
            throw new UnsupportedOperationException("Table name is user, but you`re trying to work with: " + tableName);
        }
    }

    @Override
    public Set<String> getTableNames() {

        return new LinkedHashSet<String>(Arrays.asList(TABLE_NAME));
    }

    @Override
    public void connect(String database, String userName, String password) {
    //do nothing
    }

    @Override
    public void clear(String tableName) {

        validateTable(tableName);
        data.clear();

    }


    @Override
    public void create(String tableName, DataSet input) {
        validateTable(tableName);
        data.add(input);
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        validateTable(tableName);

        for (DataSet dataSet : data) {
            if (dataSet.get("id").equals(Integer.toString(id))) {
                dataSet.updateFrom(newValue);
            }
        }
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
         return new LinkedHashSet<>(Arrays.asList("id", "name", "password" ));
    }

    @Override
    public boolean isConnected(String command) {
        return true;
    }
}

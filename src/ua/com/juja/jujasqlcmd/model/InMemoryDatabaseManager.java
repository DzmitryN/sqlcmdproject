package ua.com.juja.jujasqlcmd.model;


import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


public class InMemoryDatabaseManager implements DatabaseManager {

   public static final String TABLE_NAME = "user, test";
    private DataSet [] data = new DataSet[1000];
    private int freeIndex = 0;

    @Override
    public DataSet[] getTableData(String tableName) {
        validateTable(tableName);
        return Arrays.copyOf(data, freeIndex);
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
        data = new DataSet[100];
        freeIndex =0;
    }


    @Override
    public void create(String tableName, DataSet input) {
        validateTable(tableName);
        data[freeIndex]=input;
        freeIndex++;
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        validateTable(tableName);
        for (int i = 0; i < freeIndex ; i++) {
            if(data[i].get("id").equals(Integer.toString(id))){
                data[i].updateFrom(newValue);
            }
        }
    }

    @Override
    public String[] getTableColumns(String tableName) {
         return new String [] {"id", "name", "password" };
    }

    @Override
    public boolean isConnected(String command) {
        return true;
    }
}

package ua.com.juja.jujasqlcmd.model;


import java.util.*;


public class InMemoryDatabaseManager implements DatabaseManager {

    private Map<String, LinkedList<DataSet>> tables = new LinkedHashMap<>();

    @Override
    public List<DataSet> getTableData(String tableName) {
         return get(tableName);
    }

    public int getSize(String tableName){
        return get(tableName).size();
    }

    @Override
    public Set<String> getTableNames() {

        return tables.keySet();
    }

    @Override
    public void connect(String database, String userName, String password) {
    //do nothing
    }

    @Override
    public void clear(String tableName) {

       get(tableName).clear();

    }
    private List<DataSet>get(String tableName){
        if(!tables.containsKey(tableName)){
            tables.put(tableName, new LinkedList<DataSet>());
        }
        return tables.get(tableName);
    }


    @Override
    public void create(String tableName, DataSet input) {
       get(tableName).add(input);
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
         for (DataSet dataSet : get(tableName)) {
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

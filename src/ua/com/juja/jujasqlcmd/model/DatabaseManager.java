package ua.com.juja.jujasqlcmd.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface DatabaseManager {
    //SELECT TABLE NAME получение списка данных из таблицы
    List<DataSet> getTableData(String tableName);

    // SELECT LIST TABLES получаем имена таблиц базы
    Set<String> getTableNames();

    //----------------------------------------------------------------------------------------------------------------
        //CONNECT TO DATABASE соединение с базой
    void connect(String database, String userName, String password);

    //DELETE(CLEAR DATABASE)чистка базы-----------------------------------------------------------------------------------------------------
    void clear(String tableName);

    //INSERT TO DATABASE создание записи----------------------------------------------------------------------------------------------------------------
    void create(String tableName, DataSet input);

    //UPDATE DATA TABLE апдейт записи
    void update(String tableName, int id, DataSet newValue);

    Set<String> getTableColumns(String tableName);

    boolean isConnected(String command);
}

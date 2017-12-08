package ua.com.juja.jujasqlcmd.model;

/**
 * Created by Dima1 on 06.12.2017.
 */
public interface DatabaseManager {
    //SELECT TABLE NAME получение списка данных из таблицы
    DataSet[] getTableData(String tableName);

    // SELECT LIST TABLES получаем имена таблиц базы
    String[] getTableNames();

    //----------------------------------------------------------------------------------------------------------------
        //CONNECT TO DATABASE соединение с базой
    void connect(String database, String userName, String password);

    //DELETE(CLEAR DATABASE)чистка базы-----------------------------------------------------------------------------------------------------
    void clear(String tableName);

    //INSERT TO DATABASE создание записи----------------------------------------------------------------------------------------------------------------
    void create(String tableName, DataSet input);

    //UPDATE DATA TABLE апдейт записи
    void update(String tableName, int id, DataSet newValue);
}

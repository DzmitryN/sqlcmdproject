package model;

import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.JDBCDatabaseManager;


public class JDBCDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    protected DatabaseManager getDataBaseManager() {
        return new JDBCDatabaseManager();
    }
}

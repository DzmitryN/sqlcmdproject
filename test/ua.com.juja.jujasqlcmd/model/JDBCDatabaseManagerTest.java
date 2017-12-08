package model;

import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.JDBCDatabaseManager;

/**
 * Created by Dima1 on 07.12.2017.
 */
public class JDBCDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    protected DatabaseManager getDataBaseManager() {
        return new JDBCDatabaseManager();
    }
}

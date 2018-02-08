package model;

import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.InMemoryDatabaseManager;


public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {

       @Override
    protected DatabaseManager getDataBaseManager() {
        return new InMemoryDatabaseManager();
    }
}

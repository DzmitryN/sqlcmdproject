package model;

import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.InMemoryDatabaseManager;

/**
 * Created by Dima1 on 07.12.2017.
 */
public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {

       @Override
    protected DatabaseManager getDataBaseManager() {
        return new InMemoryDatabaseManager();
    }
}

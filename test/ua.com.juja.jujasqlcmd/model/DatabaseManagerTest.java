package model;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Dima1 on 24.11.2017.
 */
public abstract class DatabaseManagerTest {

    protected DatabaseManager manager;
    @Before
    public void setup (){
        manager = getDataBaseManager();
        manager.connect("jujaproject", "posgres1", "123456");

    }

    protected abstract DatabaseManager getDataBaseManager();


    @Test
    public void testGetAllTableNames () {

     String [] tableNames = manager.getTableNames();
     assertEquals("[user, test]", Arrays.toString(tableNames));

    }

@Test
    public void testgetTableData(){
        //given
        manager.clear("user");

        //when
        DataSet input = new DataSet();
        input.put("id", "13");
        input.put("name", "Stiven");
        input.put("password", "pass");


        manager.create("user", input);
        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[13, Stiven, pass]", Arrays.toString(user.getValues()));
    }
@Test
    public void testUpdateTableData() {
        //given
        manager.clear("user");


        DataSet input = new DataSet();
        input.put("id", "13");
        input.put("name", "Stiven");
        input.put("password", "pass");


        manager.create("user", input);

        //when
        DataSet newValue = new DataSet();
        newValue.put("password", "passick");
        manager.update("user", 13, newValue);

        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[13, Stiven, passick]", Arrays.toString(user.getValues()));
    }

    @Test
    public void testGetColumnNames() {
        //given
        manager.clear("user");

        //when
        String [] columnNames = manager.getTableColumns("user");
        //then
        assertEquals("[id, name, password]", Arrays.toString(columnNames));
    }

}

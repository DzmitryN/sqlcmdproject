package model;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

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

     //given
     manager.getTableData("user");
     manager.getTableData("test");
     //when
     Set<String> tableNames = manager.getTableNames();
     //then
     assertEquals("[user, test]", tableNames.toString());

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
        LinkedList<DataSet> users = (LinkedList<DataSet>) manager.getTableData("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[id, name, password]", user.getNames().toString());
        assertEquals("[13, Stiven, pass]", user.getValues().toString());
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
        LinkedList<DataSet> users = (LinkedList<DataSet>) manager.getTableData("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[id, name, password]", user.getNames().toString());
        assertEquals("[13, Stiven, passick]", user.getValues().toString());
    }

    @Test
    public void testGetColumnNames() {
        //given
        manager.clear("user");

        //when
        Set<String> columnNames = manager.getTableColumns("user");
        //then
        assertEquals("[id, name, password]", columnNames.toString());
    }
        public void testisGetConnected(String connect){
        //given
        //when
        //then
        assertTrue(manager.isConnected(connect));
    }
}

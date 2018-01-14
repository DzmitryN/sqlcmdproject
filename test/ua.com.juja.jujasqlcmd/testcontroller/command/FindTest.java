package testcontroller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Find;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DataSetImpl;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class FindTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

   @Before
   public void setup(){
       manager = mock(DatabaseManager.class);
       view = mock(View.class);
       command = new Find(view, manager);
   }


    @Test
    public void testPrintTableData(){
        //given

        //when
        when(manager.getTableColumns("user"))
                .thenReturn(new LinkedHashSet<String>(Arrays.asList("id", "name", "password")));

        DataSet user1 = new DataSet();
        user1.put("id", 80);
        user1.put("name", "Richard");
        user1.put("password", "333333");

        DataSet user2 = new DataSet();
        user2.put("id", 70);
        user2.put("name", "Ricko");
        user2.put("password", "000000");

       // LinkedList<DataSetImpl> data = );
        when(manager.getTableData("user")).thenReturn(new LinkedList<DataSet>(Arrays.asList(user1, user2)));

        command.process("Find|user");
        //then

        String expected = "[|id|name|password|, |80|Richard|333333|, |70|Ricko|000000|]";
        expectedOnPrint(expected);

        }

    private void expectedOnPrint(String expected) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()/*times(3)*/).write(captor.capture());
        assertEquals(expected, captor.getAllValues().toString());
    }

    @Test
        public void testCanProcessFindWithParameteresString(){

            //when
            boolean canProcess = command.canProcess("Find|user");
            //then
            assertTrue(canProcess);

        }
        @Test
        public void testCantProcessFindWithoutParameteresString(){

            //given

            //when
            boolean canProcess = command.canProcess("Find");
            //then
            assertFalse(canProcess);

    }
    @Test
    public void testCantProcessFindWithOPRTString(){

        //given
         //when
        boolean canProcess = command.canProcess("OPRT|user");
        //then
        assertFalse(canProcess);

    }

    @Test
    public void testPrintEmptyData(){
        //given

        //when
        when(manager.getTableColumns("user")).thenReturn(new LinkedHashSet<String>(Arrays.asList("id", "name", "password")));


        LinkedList<DataSet> data = new LinkedList<DataSet>();
        when(manager.getTableData("user")).thenReturn(data);

        command.process("Find|user");
        //then

        String expected = "[|id|name|password|]";
        expectedOnPrint(expected);

    }
    @Test
    public void testExceptionWhenBadCommandFormat(){
        //given

        //when



        //then
        try {
            command.process("Find|user|resu");
            fail("Exception expected");
        }catch (IllegalArgumentException e){
            assertEquals("Формат команды 'Find|tableName', а было введено Find|user|resu", e.getMessage());
        }


    }
}

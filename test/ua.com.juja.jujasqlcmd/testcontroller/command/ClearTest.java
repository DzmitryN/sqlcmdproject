package testcontroller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.jujasqlcmd.Controller.Command.Clear;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Find;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ClearTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

   @Before
   public void setup(){
       manager = mock(DatabaseManager.class);
       view = mock(View.class);
       command = new Clear(view, manager);

   }


    @Test
    public void testClearTable(){
         //when

        command.process("Clear|user");
        //then
        verify(manager).clear("user");
        verify(view).write("Таблица user была успешно очищена!");

        }



    @Test
        public void testCanProcessClearWithParameteresString(){
            //when
            boolean canProcess = command.canProcess("Clear|user");
            //then
            assertTrue(canProcess);

        }
        @Test
        public void testCantProcessClearWithoutParameteresString(){
            //when
            boolean canProcess = command.canProcess("Clear");
            //then
            assertFalse(canProcess);

    }
    @Test
    public void testCantProcessClearWithOPRTString(){
         //when
        boolean canProcess = command.canProcess("OPRT|user");
        //then
        assertFalse(canProcess);

    }
    @Test
    public void testCantProcessClearWithError_LengthLessThanTwo(){
        //when
        try {
            command.process("Clear");
            fail();
        }catch (IllegalArgumentException e){
            //then
            assertEquals("Формат команды 'Clear|tableName', а было введено Clear", e.getMessage());
        }


    }
    @Test
    public void testCantProcessClearWithError_LengthMoreThanTwo(){
        //when
        try {
            command.process("Clear|ret|rere");
            fail();
        }catch (IllegalArgumentException e){
            //then
            assertEquals("Формат команды 'Clear|tableName', а было введено Clear|ret|rere", e.getMessage());
        }



    }


}

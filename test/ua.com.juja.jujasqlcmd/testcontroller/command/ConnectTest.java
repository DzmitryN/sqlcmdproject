package testcontroller.command;


import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Connect;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConnectTest {
    private View view;
    private DatabaseManager manager;
    private Command command;

    @Before
    public void setup(){
        view = mock(View.class);
        manager = mock(DatabaseManager.class);
        command = new Connect(view, manager);
    }

    @Test
    public void testConnectCanProcess(){
        //when
        boolean canProcess = command.canProcess("Connect|");
        //then
        assertTrue(canProcess);
    }

    @Test
    public void testConnectCantProcess(){
        //when
        boolean canProcess = command.canProcess("Connect");
        //then
        assertFalse(canProcess);
    }
    @Test
    public void testProcessConnectWithParameteres_WrongNumbersError(){
        //given
        String string = "Connect|jujaproject|posgres1";

        try {
            //when
            command.process(string);
            fail();
        }catch(IllegalArgumentException e){
            //then
            assertEquals("Неверно количество параметров, разделенных символом |, количество должно быть 4, вы ввели: 3.", e.getMessage());
        }


    }
    @Test
    public void testProcessWithParameteres(){
        //given
        String string = "Connected|project|posgr|121121";
        //when
        command.process(string);
        //then
        verify(view).write("Connected!");
    }

}

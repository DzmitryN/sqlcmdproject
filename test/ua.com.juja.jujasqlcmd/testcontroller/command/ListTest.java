package testcontroller.command;


import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.List;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;
;
import java.util.LinkedHashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ListTest {

    private View view;
    private DatabaseManager manager;
    private Command command;

    @Before
    public void setup(){
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new List(view, manager);
    }

    @Test
    public void PrintListData(){

        Set<String> data = new LinkedHashSet<String>();
        data.add("user");
        data.add("test");
        when(manager.getTableNames()).thenReturn(data);
        command.process("List");
        String expected = "[user, test]";
        verify(view).write(expected);
    }


    @Test
    public void testCanProcessListWithParameteresString(){

        //when
        boolean canProcess = command.canProcess("List");
        //then
        assertTrue(canProcess);

    }

    @Test
    public void testCantProcessListWithoutParameteresString(){

        //when
        boolean canProcess = command.canProcess("");
        //then
        assertFalse(canProcess);

    }
}

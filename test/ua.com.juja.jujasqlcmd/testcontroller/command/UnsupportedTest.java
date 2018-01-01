package testcontroller.command;


import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Unsupported;
import ua.com.juja.jujasqlcmd.View.View;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UnsupportedTest {

    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        command = new Unsupported(view);
    }

    @Test
    public void testCanProcessWithAnyParameteresString(){

        //when
        boolean canProcess = command.canProcess("***");
        //then
        assertTrue(canProcess);

    }

    @Test
    public void testCantProcessWithAnyParameteres(){

        command.process("---");

        String expected = "Введена несуществующая команда: ---";
        verify(view).write(expected);
    }
}


package controller.command;

import org.junit.Test;

import static org.mockito.Mockito.*;


import org.mockito.Mockito;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Exit;
import ua.com.juja.jujasqlcmd.Controller.Command.ExitException;
import ua.com.juja.jujasqlcmd.View.View;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Dima1 on 30.12.2017.
 */
public class ExitWithMockitoTest {

    private View view = mock(View.class);

    @Test
    public void testCanProcessExitString(){

        //given
        Command command = new Exit(view);
        //when
        boolean canProcess = command.canProcess("Exit");
        //then
        assertTrue(canProcess);

    }
    @Test
    public void testCantProcessExitString(){

        //given
        Command command = new Exit(view);
        //when
        boolean canProcess = command.canProcess("poytr");
        //then
        assertFalse(canProcess);

    }
    @Test
    public void testCanProcessExitCommand_throwsExitException(){

        //given
        Command command = new Exit(view);
        //when
        try {
            command.process("Exit");
            fail("Expected ExitException");
        }catch (ExitException e){
            //doNothing
        }
        //then
        Mockito.verify(view).write("Всего хорошего!");

        //assertEquals("Всего хорошего!\n", view.getContent());
        //throws ExitException

    }
}

package testcontroller.command;


import org.junit.Before;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.isConnected;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IsConnectedTest {

    private View view;
    private DatabaseManager manager;
    private Command command;

    @Before
    public void setup(){

        view = mock(View.class);
        manager = mock(DatabaseManager.class);
        command = new isConnected(view, manager);
    }

    @Test

    public void testCanProcess(){
        //when
        boolean canProcess = command.canProcess("----");
        //then
        assertTrue(canProcess);
    }

    @Test
    public void testPritnOutProcessResult(){

        command.process("***");
        String expected = "Вы не можете пользоваться командой '***', вам необходимо установить подключение к базе" +
                " с помощью команды Connect|database|userName|password";
        verify(view).write(expected);
    }
}

package testcontroller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Help;
import ua.com.juja.jujasqlcmd.View.View;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class HelpTest {

    private View view;
    Command command;

    @Before
    public void setup(){
        view = mock(View.class);
        command = new Help(view);
    }

    @Test
    public void testCanHelpProcess(){
        //when
        boolean canProcess = command.canProcess("Help");
        //then
        assertTrue(canProcess);
    }
    @Test
    public void testCantHelpProcess(){
        //when
        boolean canProcess = command.canProcess("Hel");
        //then
        assertFalse(canProcess);
    }
    @Test
    public void testHelpProcess(){
        //when
        command.process("Help");
        //then
        String expected = "[Существующие команды: ," +
                " \tHelp, \t\t для помощи.," +
                " \tConnect|database|userName|password," +
                " \t\t для подключения к существующей базе данных.," +
                " \tList, \t\t для получения списка всех таблиц базы.," +
                " \tClear|tableName, \t\t для очистки всей таблицы.," +
                " \tCreate|tableName|column1|value1|column2|value2....columnN|valueN," +
                " \t\t для создания записи в таблицы., \tFind|tableName, " +
                "\t\t для получения содержимого таблицы 'tableName', " +
                "\tExit, " +
                "\t\t для выхода из программы.]";


        expectedOnPrint(expected);

    }

    private void expectedOnPrint(String expected) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, Mockito.atLeastOnce()).write(captor.capture());
        assertEquals(expected, captor.getAllValues().toString());
    }

}

package testcontroller.command;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Create;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class CreateTest {

    private View view;
    private DatabaseManager manager;
    private Command command;

    @Before
    public void setup(){
        view = mock(View.class);
        manager = mock(DatabaseManager.class);
        command = new Create(view, manager);
    }

    @Test
    public void canProcessCreateWithParameters(){
        boolean canProcess = command.canProcess("Create|");
        assertTrue(canProcess);
    }

    @Test
    public void canProcessCreateWithWrongParameters(){
        boolean canProcess = command.canProcess("Crea|");
        assertFalse(canProcess);
    }

    @Test
    public void cantProcessWithError_NotEvenParameteres(){

        try {
            command.process("Create|itor|trtr");
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Количество параметров должно быть четным! Формат запроса должен быть " +
                    "'Create|tableName|column1|value1|column2|value2....columnN|valueN', а ты ввел 'Create|itor|trtr'", "Количество параметров должно быть четным! Формат запроса должен быть " +
                    "'Create|tableName|column1|value1|column2|value2....columnN|valueN', а ты ввел 'Create|itor|trtr'");
        }
    }

    @Test
    public void processCreatewithParameteres(){
                String string = "Create|Users|id|12|name|Vladik";


                DataSet dataset = new DataSet();
                dataset.put("id", "12");
                dataset.put("name", "Vladik");

                command.process(string);

        String expected = "[Запись {name=[id, name], value=[12, Vladik]} в таблице Users была успешно создана!]";
        expectedOnPrint(expected);

    }

    private void expectedOnPrint(String expected) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()/*times(3)*/).write(captor.capture());
        assertEquals(expected, captor.getAllValues().toString());
    }

}

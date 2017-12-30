package controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import ua.com.juja.jujasqlcmd.Controller.Command.Command;
import ua.com.juja.jujasqlcmd.Controller.Command.Find;
import ua.com.juja.jujasqlcmd.View.View;
import ua.com.juja.jujasqlcmd.model.DataSet;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Dima1 on 31.12.2017.
 */
public class FindTest {

    private DatabaseManager manager;
    private View view;

   @Before
   public void setup(){
       manager = mock(DatabaseManager.class);
       view = mock(View.class);
   }


    @Test
    public void test(){
        //given
        Command command = new Find(view, manager);
        //when
        when(manager.getTableColumns("user")).thenReturn(new String []{"id", "name", "password"});

        DataSet user1 = new DataSet();
        user1.put("id", 80);
        user1.put("name", "Richard");
        user1.put("password", "333333");

        DataSet user2 = new DataSet();
        user2.put("id", 70);
        user2.put("name", "Ricko");
        user2.put("password", "000000");

        DataSet[] data = new DataSet[]{user1, user2};
        when(manager.getTableData("user")).thenReturn(data);

        command.process("Find|user");
        //then

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()/*times(3)*/).write(captor.capture());
        assertEquals("[|id|name|password|, |80|Richard|333333|, |70|Ricko|000000|]", captor.getAllValues().toString());

    }
}

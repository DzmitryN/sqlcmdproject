package controller.command;

import ua.com.juja.jujasqlcmd.View.View;

/**
 * Created by Dima1 on 30.12.2017.
 */
public class FakeViewforTest implements View {

   private String  messages = "";

    @Override
    public void write(String message) {
            messages += message  +"\n";
    }

    @Override
    public String read() {
        return null;
    }

    public String getContent() {
        return messages;
    }
}

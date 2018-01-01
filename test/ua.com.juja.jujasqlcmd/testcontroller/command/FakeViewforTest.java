package testcontroller.command;

import ua.com.juja.jujasqlcmd.View.View;


public class FakeViewforTest implements View {

   private String  messages = "";
    private String input = null;

    @Override
    public void write(String message) {
            messages += message  +"\n";
    }

    @Override
    public String read() {
        if(input==null){
            throw new IllegalStateException("Необходима инициализация метода read()");
        }
        String result = this.input;
        this.input=null;
        return result;
    }

    public void addRead(String input){
        this.input = input;
    }

    public String getContent() {
        return messages;
    }
}

package ua.com.juja.jujasqlcmd.View;

import java.io.IOException;


public interface View {
    public void write(String message);
    public String read();
}

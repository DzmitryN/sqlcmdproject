package ua.com.juja.jujasqlcmd.View;

import java.io.IOException;

/**
 * Created by Dima1 on 08.12.2017.
 */
public interface View {
    public void write(String message);
    public String read();
}

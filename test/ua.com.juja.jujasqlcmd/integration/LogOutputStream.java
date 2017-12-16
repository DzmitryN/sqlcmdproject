
package integration;
/*
import java.io.IOException;
import java.io.OutputStream;


*/
/**
 * Created by Dima1 on 15.12.2017.
 *//*

public class LogOutputStream extends OutputStream{

    private String log;

    @Override
    public void write(int b) throws IOException {
        log+=String.valueOf((char)b);
    }

    public String getData() {
        return log;
    }
}
*/
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by indigo on 28.08.2015.
 */
public class LogOutputStream extends OutputStream {

    private String log;

    @Override
    public void write(int b) throws IOException {
        byte[] bytes = new byte[] { (byte)(b & 0xFF00 >> 8), (byte)(b & 0x00FF) };
        log += new String(bytes, "UTF-8");
    }

    public String getData() {
        return log;
    }
}
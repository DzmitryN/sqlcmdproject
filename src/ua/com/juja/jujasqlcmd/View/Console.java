package ua.com.juja.jujasqlcmd.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Dima1 on 08.12.2017.
 */
public class Console implements View {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        try { //Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        //return scanner.nextLine();
        return reader.readLine();
        } catch(IOException e)
        {
          e.getLocalizedMessage().toString();
        }
        return "";
    }
}

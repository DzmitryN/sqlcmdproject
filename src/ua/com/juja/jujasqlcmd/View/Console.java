package ua.com.juja.jujasqlcmd.View;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
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
        /*try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
       /* while(true)
        try {
            if(reader.readLine()!=null) {
                reader.readLine();
            }
            reader.close();
            return reader.readLine();

        } catch (IOException e) {
            return Integer.toString(0);
        }*/

    }

}


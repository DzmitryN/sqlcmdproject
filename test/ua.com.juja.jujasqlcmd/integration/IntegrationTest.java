/*
package integration;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.com.juja.jujasqlcmd.Controller.Main;

import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static LogOutputStream out;

    @BeforeClass
    public static void setup () throws IOException {
        in = new ConfigurableInputStream();
        out = new LogOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testExit(){
        //given
        in.add("Help");
        in.add("Exit");

        //when
        Main.main(new String [0]);

        //then
        assertEquals("", out.getData());
    }
}
*/
package integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.jujasqlcmd.Controller.Main;
import ua.com.juja.jujasqlcmd.model.DatabaseManager;
import ua.com.juja.jujasqlcmd.model.JDBCDatabaseManager;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by indigo on 28.08.2015.
 */
public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;
    private DatabaseManager databaseManager;

    @Before
    public void setup() {
        databaseManager = new JDBCDatabaseManager();
        out = new ByteArrayOutputStream();
        in = new ConfigurableInputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testHelp() {
        // given
        in.add("Help");
        in.add("Exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Существующие команды: \r\n" +
                "\tHelp\r\n" +
                "\t\t для помощи.\r\n" +
                "\tConnect|database|userName|password\r\n" +
                "\t\t для подключения к существующей базе данных.\r\n" +
                "\tList\r\n" +
                "\t\t для получения списка всех таблиц базы.\r\n" +
                "\tClear|tableName\r\n" +
                "\t\t для очистки всей таблицы.\r\n" +
                "\tCreate|tableName|column1|value1|column2|value2....columnN|valueN\r\n" +
                "\t\t для создания записи в таблицы.\r\n" +
                "\tFind|tableName\r\n" +
                "\t\t для получения содержимого таблицы 'tableName'\r\n" +
                "\tExit\r\n" +
                "\t\t для выхода из программы.\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
    @Test
    public void testListWithoutConnect() {
        // given
        in.add("List");
        in.add("Exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Вы не можете пользоваться командой 'List', вам необходимо установить подключение к базе с помощью команды Connect|database|userName|password\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test

    public void testFindWithoutConnect(){
        //given
        in.add("Find|");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Вы не можете пользоваться командой 'Find|', вам необходимо установить подключение к базе с помощью команды Connect|database|userName|password\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }
    @Test
    public void testUnsupported() {
        //given
        in.add("Unsupported");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Вы не можете пользоваться командой 'Unsupported', вам необходимо установить подключение к базе с помощью команды Connect|database|userName|password\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("Unsupported");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Введена несуществующая команда: Unsupported\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testListAfterConnect(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("List");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "[user, test]\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testFindAfterConnect(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("Find|user");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "|id|name|password|\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testConnectAfterConnect(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("List");
        in.add("Connect|test|posgres1|123456");
        in.add("List");
        in.add("Exit");

        //when
        Main.main(new String[0]);

        //then

        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "[user, test]\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "[qwer]\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testConnectWithError(){
        //given
        in.add("Connect|jujaproject");
        in.add("Exit");
        //when
        Main.main(new String [0]);

        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Подключение невозможно по причине: Неверно количество параметров, разделенных символом |, количество должно быть 4, вы ввели: 2. \r\n" +
                "Повторите попытку.\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testFindAfterConnect_withData(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("Clear|user");
        in.add("Create|user|id|13|name|Stiven|password|*****");
        in.add("Create|user|id|14|name|Eva|password|+++++");
        in.add("Find|user");
        in.add("Clear|user");
        in.add("Exit");
        //when
        Main.main(new String[0]);

        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Таблица user была успешно очищена!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "\n" +
                "Запись {name=[id, name, password], value=[13, Stiven, *****]} в таблице user была успешно создана!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "\n" +
                "Запись {name=[id, name, password], value=[14, Eva, +++++]} в таблице user была успешно создана!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "|id|name|password|\r\n" +
                "|13|Stiven|*****|\r\n" +
                "|14|Eva|+++++|\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Таблица user была успешно очищена!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }

    @Test
    public void testClearWithError(){
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("Clear|jklj|jklj|JKLJ");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Подключение невозможно по причине: Формат команды 'Clear|tableName', а было введено Clear|jklj|jklj|JKLJ \r\n" +
                "Повторите попытку.\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());


    }

    @Test
    public void testCreateWithError() {
        //given
        in.add("Connect|jujaproject|posgres1|123456");
        in.add("Create|user|Eword|");
        in.add("Exit");
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Привет, дорогой пользователь!\r\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате connect|database|userName|password.\r\n" +
                "Connected!\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Подключение невозможно по причине: Количество параметров должно быть четным! Формат запроса должен быть" +
                " 'Create|tableName|column1|value1|column2|value2....columnN|valueN', а ты ввел 'Create|user|Eword|' \r\n" +
                "Повторите попытку.\r\n" +
                "Введи команду(или Help для помощи): \r\n" +
                "Всего хорошего!\r\n", getData());
    }
}

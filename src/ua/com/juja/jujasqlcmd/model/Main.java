package ua.com.juja.jujasqlcmd.model;

import java.sql.*;
import java.util.Arrays;


public class Main {
    public static void main(String[] argv) throws ClassNotFoundException, SQLException {


       Class.forName("org.postgresql.Driver");

       Connection connection  = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/jujaproject", "posgres1",
               "123456");

      //INSERT
     // String sql = "INSERT INTO public.user (name, password)" + "VALUES('string', '13');";
      Statement stmt = connection.createStatement();
      //stmt.executeUpdate(sql);

        //SELECT  EASY
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.user/* WHERE id>2*/");
        while (rs.next())
        {
            System.out.println("id: " + rs.getString("id")+ " name: " + rs.getString("name")
                    + " password: " + rs.getString("password"));

        }
        rs.close();
        stmt.close();
        //DELETE

       // stmt = connection.createStatement();
       // stmt.executeUpdate("DELETE FROM public.user WHERE id>5 AND id<7" );

        //stmt.close();

        //UPDATE

        PreparedStatement ps = connection.prepareStatement("UPDATE public.user SET password = ? WHERE id>0");
        ps.setString(1, "13");
        ps.executeUpdate();
        stmt.close();



        stmt = connection.createStatement();
        rs = stmt.executeQuery("/*SELECT * FROM public.user WHERE id>2*/SELECT table_name FROM information_schema.tables\n"
                +  "        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");

        String tables [] = new String[100];
        int index = 0;

        while (rs.next())
        {
            tables[index++] = rs.getString("table_name");
            System.out.println("------");
        }

        tables = Arrays.copyOf(tables, index, String[].class);
        rs.close();
        stmt.close();
        connection.close();
    }
}

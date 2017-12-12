package ua.com.juja.jujasqlcmd.model;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by Dima1 on 22.11.2017.
 */
public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;

    //SELECT TABLE NAME получение списка данных из таблицы
    @Override
    public DataSet[] getTableData(String tableName){
        try {
            int size = getSize(tableName);


            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public." + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();

            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()) {
                DataSet dataset = new DataSet();
                result[index++] = dataset;
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    dataset.put(rsmd.getColumnName(i), rs.getObject(i));
                }
            }

            rs.close();
            stmt.close();
            return result;
        }
        catch(SQLException e){
            e.printStackTrace();
            return new DataSet[0];
        }
    }

     private int getSize(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rscount = stmt.executeQuery("SELECT COUNT (*) FROM public." + tableName );
        rscount.next();
        int size = rscount.getInt(1);
        rscount.close();
        return size;
    }


    // SELECT LIST TABLES получаем имена таблиц базы
    @Override
    public String[] getTableNames(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("/*SELECT * FROM public.user WHERE id>2*/" +
                    "SELECT table_name FROM information_schema.tables\n"
                    + "        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");
            String tables[] = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            return tables;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
//----------------------------------------------------------------------------------------------------------------
    //CONNECT TO DATABASE соединение с базой
    @Override
    public void connect(String database, String userName, String password) {


            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Please add jdbc jar to project." + e);

            }
            try {
                String truedatabase = "jujaproject";
                String trueUser = "posgres1";
                String truepassword = "123456";
                if(!truedatabase.equals(database)){
                    throw new SQLException("Неверное имя базы данных.");
                } else if (!trueUser.equals(userName)) {

                    throw new SQLException("Неверное имя пользователя.");
                }
                else if(!truepassword.equals(password)){
                    throw new SQLException("Пароль неверен.");
                }

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + database, userName,
                    password);
        } catch (SQLException e) {
                connection = null;
                throw new RuntimeException(String.format("Can`t make connection for database: %s, user: %s.", database, userName), e);
        }

    }

    //DELETE(CLEAR DATABASE)чистка базы-----------------------------------------------------------------------------------------------------
    @Override
    public void clear(String tableName) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM public." + tableName);

            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
//INSERT TO DATABASE создание записи----------------------------------------------------------------------------------------------------------------
    @Override
    public void create(String tableName, DataSet input) {
        try{
            String tableNames = getFormatted(input, "%s,");

            String  values = getNamesFormatted(input, "'%s',");

            Statement ps = connection.createStatement();
            ps.executeUpdate("INSERT INTO public." + tableName + "("+ tableNames + ")" +
                    "VALUES(" + values +")");
            ps.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private String getNamesFormatted(DataSet input, String format) {
        String values = "";
        for(Object value : input.getValues()){

            values+=String.format(format, value);
        }
        values=values.substring(0, values.length()-1);
        return values;
    }

    //UPDATE DATA TABLE апдейт записи
   @Override
   public void update(String tableName, int id, DataSet newValue) {
        try{
            String tableNames = getFormatted(newValue, "%s=?,");

            PreparedStatement ps = connection.prepareStatement("UPDATE public." + tableName+" SET "+ tableNames
                    + " WHERE id=?");

            int index = 1;
            for(Object value : newValue.getValues()){
                ps.setObject(index++, value);
            }

            ps.setInt(index, id);
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public String[] getTableColumns(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            /*ResultSet rs = stmt.executeQuery("*//*SELECT * FROM public.user WHERE id>2*//*" +
                    "SELECT table_name FROM information_schema.tables\n"
                    + "        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");*/
            ResultSet rs = stmt.executeQuery( "SELECT *  FROM information_schema.columns" +
                    " WHERE table_schema = 'public' AND table_name = '" + tableName +"'");
            String tables[] = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("column_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            return tables;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private String getFormatted(DataSet newValue, String format) {
        String string = "";
        for(String name : newValue.getNames()){
             string +=String.format(format, name);
         }
        string=string.substring(0, string.length()-1);
        return string;
    }
}

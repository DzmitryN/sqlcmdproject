package ua.com.juja.jujasqlcmd.model;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;

    //SELECT TABLE NAME получение списка данных из таблицы
    @Override
    public DataSet[] getTableData(String tableName){


        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM public." + tableName))
        {
            int size = getSize(tableName);
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

            return result;
        }
        catch(SQLException e){
            e.printStackTrace();
            return new DataSet[0];
        }
    }

     private int getSize(String tableName) throws SQLException {

        try (Statement stmt = connection.createStatement();
        ResultSet rscount= stmt.executeQuery("SELECT COUNT (*) FROM public." + tableName))
        {
        rscount.next();
        int size = rscount.getInt(1);
        return size;

        }catch(SQLException e){

            System.out.println(e.getMessage() + "\n"+(String.format("Введенное имя '%s' не является именем базы данных."+
                    " ", tableName)));

            return 0;
            }
    }


    // SELECT LIST TABLES получаем имена таблиц базы
    @Override
    public Set<String> getTableNames(){
        Set<String> tables = new LinkedHashSet<>();
        try ( Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("/*SELECT * FROM public.user WHERE id>2*/" +
                      "SELECT table_name FROM information_schema.tables\n"
                      + "        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'"))
        {

            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }

            return tables;
        }
        catch (SQLException e){
            e.printStackTrace();
            return tables;
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
               /* String truedatabase = "jujaproject";
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
*/
               if(connection!=null){
                   connection.close();
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

        try (Statement stmt = connection.createStatement()){

            stmt.executeUpdate("DELETE FROM public." + tableName);

            }catch(SQLException e){
            e.printStackTrace();
        }

    }
//INSERT TO DATABASE создание записи----------------------------------------------------------------------------------------------------------------
    @Override
    public void create(String tableName, DataSet input) {

        String tableNames = getFormatted(input, "%s,");
        String  values = getNamesFormatted(input, "'%s',");
            try(Statement ps = connection.createStatement()){

                ps.executeUpdate("INSERT INTO public." + tableName + "("+ tableNames + ")" +
                    "VALUES(" + values +")");

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

            String tableNames = getFormatted(newValue, "%s=?,");
            String sql = "UPDATE public." + tableName+" SET "+ tableNames
                    + " WHERE id=?";
            try(PreparedStatement ps = connection.prepareStatement(sql)){

            int index = 1;
            for(Object value : newValue.getValues()){
                ps.setObject(index++, value);
            }

            ps.setInt(index, id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> tables = new LinkedHashSet<>();
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT *  FROM information_schema.columns" +
                " WHERE table_schema = 'public' AND table_name = '" + tableName +"'"))
        {
            while (rs.next()) {
                tables.add(rs.getString("column_name"));
            }

            return tables;
        }
        catch (SQLException e){
            e.printStackTrace();
            return tables;
        }
    }

    @Override
    public boolean isConnected(String command) {
        return connection!=null;
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

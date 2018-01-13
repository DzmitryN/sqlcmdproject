package ua.com.juja.jujasqlcmd.model;


public interface DataSetImpl {
    void put(String name, Object value);

    String [] getNames();

    Object[] getValues();

    Object get(String name);

    void updateFrom(DataSetImpl newValue);
}

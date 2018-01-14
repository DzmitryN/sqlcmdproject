package ua.com.juja.jujasqlcmd.model;


import java.util.List;
import java.util.Set;

public interface DataSetImpl {
    void put(String name, Object value);

    Set<String> getNames();

    List<Object> getValues();

    Object get(String name);

    void updateFrom(DataSetImpl newValue);
}

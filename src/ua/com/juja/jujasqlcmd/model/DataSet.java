package ua.com.juja.jujasqlcmd.model;

import java.util.*;


public class DataSet implements DataSetImpl {

    private Map<String, Object> data = new LinkedHashMap<String, Object>();

    @Override
    public void put(String name, Object value) {

        data.put(name, value);
    }

    @Override
    public Set<String> getNames(){

        return data.keySet();
    }
    @Override
    public List<Object> getValues(){

        return new LinkedList<Object>(data.values());
    }

    @Override
    public Object get(String name) {

        return data.get(name);
    }

    @Override
    public void updateFrom(DataSetImpl newValue) {
        Set<String> columns = newValue.getNames();
        for(String name : columns) {
            Object data = newValue.get(name);
            put(name, data);

        }
    }

    @Override
    public int getSize(String tableName) {
        return data.size();
    }

    @Override
    public String toString() {
        System.out.print("\n");
        return /*"DataSet " +*/

                "{"+"name=" + getNames().toString() +", "+
                "value=" + getValues().toString()+"}";

    }
}

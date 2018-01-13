package ua.com.juja.jujasqlcmd.model;

import java.util.Arrays;


public class DataSet implements DataSetImpl {

public static class Data{

        private String name;
        private Object value;

        public Data(String name, Object value){

            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }
        public Object getValue() {
            return value;
        }
        }

    public Data[] data = new Data[100];//TODO remove magic number 100
    public int freeIndex = 0;

    @Override
    public void put(String name, Object value) {

        for (int i = 0; i < freeIndex; i++) {
            if(data[i].getName().equals(name)){
                data[i].value = value;
                return;
            }
        }
             data[freeIndex++] = new Data(name, value);
    }


    @Override
    public String [] getNames(){
        String [] result = new String[freeIndex];
        for(int i = 0; i< freeIndex; i++){
            result[i]=data[i].getName();
        }
        return result;
    }

    @Override
    public Object[] getValues(){
        Object[] object = new Object[freeIndex];
        for(int i = 0; i< freeIndex; i++){
            object[i]=data[i].getValue();
        }
        return object;
    }

    @Override
    public Object get(String name) {
        for (int i = 0; i < freeIndex; i++) {
            if(data[i].getName().equals(name)){
                return data[i].getValue();
            }
        }
        return null;
    }

    @Override
    public void updateFrom(DataSetImpl newValue) {
        String [] columns = newValue.getNames();
        for(String name : columns) {
            Object data = newValue.get(name);
            this.put(name, data);

        }
    }

    @Override
    public String toString() {
        System.out.print("\n");
        return /*"DataSet " +*/

                "{"+"name=" + Arrays.toString(getNames()) +", "+
                "value=" + Arrays.toString(getValues())+"}";

    }
}

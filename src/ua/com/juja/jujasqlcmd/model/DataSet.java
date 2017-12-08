package ua.com.juja.jujasqlcmd.model;

import java.util.Arrays;


public class DataSet {

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

    public void put( String name, Object value) {

        for (int i = 0; i < freeIndex; i++) {
            if(data[i].getName().equals(name)){
                data[i].value = value;
                return;
            }
        }
             data[freeIndex++] = new Data(name, value);
    }


    public String [] getNames(){
        String [] result = new String[freeIndex];
        for(int i = 0; i< freeIndex; i++){
            result[i]=data[i].getName();
        }
        return result;
    }

    public Object[] getValues(){
        Object[] object = new Object[freeIndex];
        for(int i = 0; i< freeIndex; i++){
            object[i]=data[i].getValue();
        }
        return object;
    }

    public Object get(String name) {
        for (int i = 0; i < freeIndex; i++) {
            if(data[i].getName().equals(name)){
                return data[i].getValue();
            }
        }
        return null;
    }

    public void updateFrom(DataSet newValue) {
        for (int i = 0; i < newValue.freeIndex ; i++) {
            Data data = newValue.data[i];
            this.put(data.name, data.value);

        }
    }

    @Override
    public String toString() {
        System.out.print("\n");
        return "DataSet " +

                "name=" + Arrays.toString(getNames()) +" "+
                "value=" + Arrays.toString(getValues());

    }
}

package com.RotorMachine;

import java.util.ArrayList;

/**
 * Created by rui.zheng on 01/04/2015
 */
public class KeyValueMap {
    private static Integer mapKeyToValue(Character key){return Integer.valueOf(key);}
    private static Character mapValueToKey(Integer i){return (char)(i.intValue());}
    public static ArrayList<Integer> mapStringToValues(String message) throws Exception{
        ArrayList<Integer> values;
        try {
            values=new ArrayList<Integer>();
            for(Integer i=0;i<message.length();i++){
                values.add(mapKeyToValue(message.charAt(i)));
            }
        }catch (Exception e){
            System.err.println("Failed to map String:"+e.getMessage());
            throw e;
        }
        return values;
    }

    public static String mapValuesToString(ArrayList<Integer> values) throws Exception{
        String s;
        try {
            s="";
            for(Integer i:values) {
                s += mapValueToKey(i);
            }
        }catch (Exception e){
            System.err.println("Failed to map values:"+e.getMessage());
            throw e;
        }
        return s;
    }
}

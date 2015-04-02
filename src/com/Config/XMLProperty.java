package com.Config;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;


public class XMLProperty {
    private  static  final ArrayList<Integer> range= new ArrayList<Integer>(){{
        for(Integer i=0;i<128;i++){
            add(i);
        }
    }};
    private static final Integer N=5;
    public static void generateNewSchema(Integer r,Integer secretKey) throws Exception{
        Properties prop=new Properties();
        ArrayList<ArrayList<Integer>> permutationList=new ArrayList<ArrayList<Integer>>();
        Integer index=secretKey%N;
        for (Integer i=0;i<r;i++){
            ArrayList<Integer> permutation=new ArrayList<Integer>(range);
            Collections.shuffle(permutation);
            permutationList.add(permutation);
        }
        prop.setProperty("r", r.toString());
        prop.setProperty("Permutations", permutationList.toString());
        FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"/resources/Encryption_Schema_"+index.toString()+".xml");
        prop.storeToXML(file, "Schema_" + secretKey.toString());
//        prop.list(System.out);
        file.close();
//        System.out.print();
    }

    public static HashMap<String,String> loadEncryptionSchema(Integer secretKey) throws Exception{
        HashMap<String,String> schema;
        try {
            final Properties prop=new Properties();
            Integer index=secretKey%N;
            FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/resources/Encryption_Schema_"+index.toString()+".xml");
            prop.loadFromXML(file);
            file.close();

            schema=new HashMap<String, String>(){{
                for(String key:prop.stringPropertyNames()){
                    put(key,prop.getProperty(key));
                }
            }};
            prop.list(System.out);
            System.out.println(schema.toString());
        }catch (Exception e){
            System.err.println("Fail to load Propertied:"+e.getMessage());
            throw e;
        }
        return schema;
    }

    public static void main(String args[]) throws Exception{
        for (Integer i=0;i<N;i++){
            loadEncryptionSchema(i);
        }
    }
}

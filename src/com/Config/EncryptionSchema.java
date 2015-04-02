package com.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class EncryptionSchema {


    private Integer r;

    private Integer N=5;
    public final Integer NUM_PIN=128;
    public ArrayList<ArrayList<Integer>> permutationList;

    public java.lang.Integer getR() {
        return r;
    }

    public void setR(java.lang.Integer r) {
        this.r = r;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    private static EncryptionSchema loadEncryptionSchemaFromProperty(Properties prop) throws Exception{
        EncryptionSchema s;
        try {
            s=new EncryptionSchema();
            s.setR(Integer.parseInt(prop.getProperty("r")));
            s.setN(Integer.parseInt(prop.getProperty("N")));
//        List<List<Integer>> permutationList= Arrays.asList(prop.getProperty("Permutations"));
            for(Integer i=0;i<s.getR();i++){
                String rawPermutation=prop.getProperty("Permutation_" + i.toString());
                String[] tempPermutation=rawPermutation.substring(1,rawPermutation.length()-1).split("[\\s,]+" );
                final List<String> temp= Arrays.asList(tempPermutation);
                ArrayList<Integer> permutation=new ArrayList<Integer>(){{
                    for(String value:temp) add(Integer.valueOf(value));
                }};
                if (s.permutationList==null){
                    s.permutationList=new ArrayList<ArrayList<Integer>>();
                }
                s.permutationList.add(permutation);
            }
        }catch (Exception e){
            System.err.println("Fail to parse XML to Schema: "+e.getMessage());
            throw e;
        }
        return s;
    }

    public static EncryptionSchema loadEncryptionSchemabySecretKey(Integer SK) throws Exception{
        return loadEncryptionSchemaFromProperty(XMLProperty.loadEncryptionSchema(SK));
    }

}

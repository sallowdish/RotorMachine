package com.Config;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class EncryptionSchema {


    private Integer r;
    public final Integer N=5;
    public final Integer NUM_PIN=128;
    ArrayList<ArrayList<Integer>> permutationList;

    public java.lang.Integer getR() {
        return r;
    }

    public void setR(java.lang.Integer r) {
        this.r = r;
    }

    public static EncryptionSchema loadEncryptionSchemaFromProperty(Properties prop){
        EncryptionSchema s=new EncryptionSchema();
        s.setR(Integer.parseInt(prop.getProperty("r")));
//        List<List<Integer>> permutationList= Arrays.asList(prop.getProperty("Permutations"));
        for(Integer i=0;i<s.getR();i++){
            ArrayList<Integer> permutation;
        }
        return s;
    }

}

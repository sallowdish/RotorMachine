package com.RotorMachine;

import com.Config.EncryptionSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnigmaMachine {


    private Integer secretKey;
    ArrayList<Rotor> rotors;
    EncryptionSchema schema;
//    private Integer r;

//    public EnigmaMachine(EncryptionSchema s){this.schema=s;}

    public EnigmaMachine(Integer SK) throws Exception{
        secretKey=SK;
        schema=EncryptionSchema.loadEncryptionSchemabySecretKey(SK);
        rotors=new ArrayList<Rotor>();
        intializeMachine(SK);
    }

    private void intializeMachine(Integer SK) throws Exception{
        //Load conf

        //Allocate rotors
        Integer r=schema.getR();
        for (Integer i=0;i<r;i++){
            final Integer index=i;
            Rotor rotor=new Rotor(new HashMap<String, List<Integer>>(){{
                put("output", schema.permutationList.get(index));}}
            );
            rotors.add(rotor);
        }
        //Link rotors together
        for (Integer i=rotors.size()-1;i>=1;i--){
            rotors.get(i).previousRotor=rotors.get(i-1);
        }

        //Set last Roter to be the fastest one
        rotors.get(rotors.size()-1).isInitialRotor=true;
    }



    public String encrypt(String message) throws Exception{
        ArrayList<Integer> input=KeyValueMap.mapStringToValues(message);
        ArrayList<Integer> output=new ArrayList<Integer>();
        for (Integer i=0;i<input.size();i++){
            Integer temp=input.get(i);
            for(Rotor rotor:rotors){
                temp=rotor.encrypt(temp);
            }
            output.add(temp);
        }
        return KeyValueMap.mapValuesToString(output);
    }

    public String decrypt(String ciphertext) throws Exception{
        ArrayList<Integer> input=KeyValueMap.mapStringToValues(ciphertext);
        ArrayList<Integer> output=new ArrayList<Integer>();
        for (Integer i=0;i<input.size();i++){
            Integer temp=input.get(i);
            for(Integer j=rotors.size()-1;j>=0;j--){
                Rotor reverseRotor=rotors.get(j);
                temp=reverseRotor.decrypt(temp);
            }
            output.add(temp);
        }
        return KeyValueMap.mapValuesToString(output);
    }

    public Integer getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Integer secretKey) {
        this.secretKey = secretKey;
    }

}

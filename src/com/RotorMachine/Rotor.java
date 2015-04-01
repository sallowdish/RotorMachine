package com.RotorMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rotor {
//    ArrayList<Integer> inputPanel=null;
    ArrayList<Integer> outputPanel=null;

    private int hitCounter=0;
    final int NUM_PIN=57; //subset of asiic table from 'A' to 'z'
    Rotor previousRotor=null;

    //Indicating if rotor rotates for each input or trigger by other rotor
    boolean isInitialRotor=false;


    public void setHitCounter(int hitCounter) {
        this.hitCounter = hitCounter;
    }
    public int getHitCounter() {
        return hitCounter;
    }

    //Initialize Rotor with configuration
    public Rotor(HashMap<String,List<Integer>> m) throws Exception{
        try {
//            inputPanel=new ArrayList<Integer>(m.get("input"));
            outputPanel=new ArrayList<Integer>(m.get("output"));
        }catch (Exception e)
        {
            System.err.println("Invalid rotor configuration:" + e.getMessage());
            throw e;
        }
    }

    public Integer encrypt(Integer in) throws Exception{
        try {
            Integer out=outputPanel.get(in);
            if (isInitialRotor){hit();}
            return out;
        }catch (Exception e){
            System.err.println("Fail to encrypt input:" + e.getMessage());
            throw e;
        }
    }

    public Integer decrypt(Integer in) throws Exception{
        try {
            Integer out=outputPanel.indexOf(in);
            if (isInitialRotor){hit();}
            return out;
        }catch (Exception e){
            System.err.println("Fail to decrypt input:" + e.getMessage());
            throw e;
        }
    }

    protected void hit(){
        //increase hitCounter
        hitCounter++;

        //shift outputPanel to emulate rotating of rotors
        outputPanel.add(outputPanel.get(0));
        outputPanel.remove(0);
        //pass carriers
        if(isFullRevolution()){
            hitCounter=0;
            hitNextRotor();
        }
    }
    private boolean isFullRevolution(){
        return hitCounter==NUM_PIN;
    }
    private void hitNextRotor(){
        if (previousRotor!=null){previousRotor.hit();}
    }
}

package com.RotorMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rui.zheng on 31/03/2015.
 */
public class Rotor {
    ArrayList<Integer> inputPanel=null;
    ArrayList<Integer> outputPanel=null;

    private int hitCounter=0;
    final int NUM_PIN=30;
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
    public Rotor(HashMap<String,List<Integer>> m){
        try {
            inputPanel=new ArrayList<>(m.get("input"));
            outputPanel=new ArrayList<>(m.get("output"));
        }catch (Exception e)
        {
            System.err.println("Invalid rotor configuration:" + e.getMessage());
            throw e;
        }
    }

    public Integer translate(Integer in){
        try {
            Integer out=outputPanel.get(inputPanel.indexOf(in));
            if (isInitialRotor){hit();}
            return out;
        }catch (Exception e){
            System.err.println("Fail to translate input:" + e.getMessage());
            throw e;
        }
    }

    protected void hit(){
        //increase hitCounter
        hitCounter++;

        //shift outputPanel to emulate rotating of rotors
//        outputPanel.add(outputPanel.get(0));
//        outputPanel.remove(0);
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

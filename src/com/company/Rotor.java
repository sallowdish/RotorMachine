package com.company;
import java.util.ArrayList;

/**
 * Created by rui.zheng on 31/03/2015.
 */
public class Rotor {
    ArrayList<Integer> inputPanel=null;
    ArrayList<Integer> outputPanel=null;
    int hitCounter=0;
    final int NUM_PIN=30;
    Rotor previousRotor;

    protected void hit(){
        //increase hitCounter
        hitCounter++;

        //shift outputPanel to emulate rotating of rotors
        outputPanel.add(outputPanel.get(0));
        outputPanel.remove(0);
        //pass carriers
        if(isFullRevolution()){ hitNextRotor();}
    }
    private boolean isFullRevolution(){
        return hitCounter==30;
    }
    private void hitNextRotor(){
        while (previousRotor!=null){previousRotor.hit();}
    }
}

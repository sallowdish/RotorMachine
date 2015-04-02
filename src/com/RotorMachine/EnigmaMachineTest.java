package com.RotorMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class EnigmaMachineTest {
    private ArrayList<EnigmaMachine> machines;

    @Before
    public void setUp() throws Exception {
        for (Integer i=0;i<5;i++){
            machines=new ArrayList<EnigmaMachine>();
            EnigmaMachine m=new EnigmaMachine(i);
            assertNotNull(m);
            machines.add(m);
        }
    }

    @Test
    public void testEncrypt() throws Exception {

    }

    @Test
    public void testDecrypt() throws Exception {

    }
}
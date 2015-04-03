package com.RotorMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class EnigmaMachineTest {
    private EnigmaMachine encoder,decoder;

    @Before
    public void setUp() throws Exception {
        Random rand = new Random();
        Integer randInt=Math.abs(rand.nextInt());
        encoder=new EnigmaMachine(randInt);
        decoder=new EnigmaMachine(randInt);
        assertNotNull(encoder);
        assertNotNull(decoder);
    }

//    @Test
//    public void testEnigmaConstructor() throws Exception{
//        ArrayList<EnigmaMachine> machines;
//        for (Integer i=0;i<5;i++){
//            machines=new ArrayList<EnigmaMachine>();
//            EnigmaMachine m=new EnigmaMachine(i);
//            assertNotNull(m);
//            machines.add(m);
//        }
//    }

    @Test
    public void testEncryptandDecrypt() throws Exception {
        String s="Hello World.";
        String encodedString=encoder.encrypt(s);
        String decodedString=decoder.decrypt(encodedString);
        assertEquals(decodedString,s);
    }


}
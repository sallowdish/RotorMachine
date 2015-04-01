package com.RotorMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rui.zheng on 31/03/2015
 */
public class RotorUnitTest {
    private HashMap<String,List<Integer>> config;
    private Rotor encoder,decoder;
    @Before
    public void setUp() throws Exception {
//        List<Integer> inputPanel= Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
        List<Integer> outputPanel= Arrays.asList(43, 35, 30, 5, 39, 23, 1, 16, 15, 8, 42, 52, 17, 38, 14, 24, 37, 36, 40, 0, 22, 20, 2, 50, 21, 56, 3, 26, 13, 6, 12, 32, 33, 31, 9, 19, 25, 48, 44, 53, 55, 18, 4, 27, 47, 54, 45, 49, 41, 28, 51, 29, 10, 11, 46, 7, 34);
        config=new HashMap<String,List<Integer>>();
//        config.put("input",inputPanel);
        config.put("output",outputPanel);
        encoder =new Rotor(config);
        decoder=new Rotor(config);
        Assert.assertNotNull(encoder);
        Assert.assertNotNull(decoder);
    }

    @Test
    public void testEncrypt() throws Exception {
        for(Integer i=0;i< encoder.NUM_PIN;i++){
            assertEquals(config.get("output").get(i), encoder.encrypt(i),0);
        }
    }

    @Test
    public void testDecrypt() throws Exception {
        for(Integer i=0;i< encoder.NUM_PIN;i++){
            assertEquals(i, encoder.decrypt(config.get("output").get(i)),0);
        }
    }

    @Test
    public void testHit() throws Exception {
        for(Integer i=0;i< encoder.NUM_PIN;i++){
            assertEquals(i, encoder.getHitCounter(),0);
            encoder.hit();
        }
    }
    @Test
    public void testHitNextRotor() throws  Exception {
        Rotor r2=new Rotor(config);
        encoder.previousRotor=r2;
        assertEquals(0,r2.getHitCounter());
        for(Integer i=0;i< encoder.NUM_PIN+1;i++){
//            System.out.println("Hit*" + i);
            encoder.hit();
        }
        assertEquals(1,r2.getHitCounter());
    }

//    @Test
//    public void testEncryptAndHit() throws  Exception {
//        //Encryption
//        String message="Hello World",ciphertext="";
//        for(Integer i=0;i<message.length();i++){
//            Character c=message.charAt(i);
//            Integer input=KeyValueMap.mapKeyToValue(c);
//            Integer output=encoder.encrypt(input);
//            ciphertext+=output.toString();
//        }
//        //Decryption
//        Rotor decoder=new Rotor(config);
//        String decodedMsg="";
//        for(Integer i=0;i<ciphertext.length();i++){
//            Character c=ciphertext.charAt(i);
//            Integer input=KeyValueMap.mapKeyToValue(c);
//            Integer output=encoder.decrypt(input);
//            decodedMsg+=output.toString();
//        }
//        assertEquals(message,decodedMsg);
//    }
    @Test
    public void testIntegerArrayEncryption() throws  Exception {
        //Encryption
        ArrayList<Integer> message=new ArrayList<Integer>(){{
            add(5);
            add(4);
            add(3);
            add(45);
            add(15);
        }};
        ArrayList<Integer> ciphertext=new ArrayList<Integer>();
        for(Integer in:message){
            Integer output= encoder.encrypt(in);
            ciphertext.add(output);
        }
//        System.out.println(ciphertext.toString());
        //Decryption

        ArrayList<Integer> decodedMsg=new ArrayList<Integer>();
        for(Integer in: ciphertext){
            Integer output= decoder.decrypt(in);
            decodedMsg.add(output);
        }
        for(Integer x:message){
            assertEquals(x, decodedMsg.get(message.indexOf(x)));
        }
    }

    @Test
    public void textEncryptandHit() throws Exception{
        encoder.isInitialRotor=true;
        decoder.isInitialRotor=true;
        testIntegerArrayEncryption();
    }
    @After
    public void cleanUpRotor() throws Exception {
        encoder.setHitCounter(0);
    }
}
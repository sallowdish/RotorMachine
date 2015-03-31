package com.RotorMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rui.zheng on 31/03/2015
 */
public class RotorUnitTest {
    private HashMap<String,List<Integer>> config;
    private Rotor r;
    @Before
    public void setUp() throws Exception {
        List<Integer> inputPanel= Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        List<Integer> outputPanel= Arrays.asList(29, 25, 27, 6, 12, 5, 10, 24, 17, 11, 18, 26, 13, 2, 14, 20, 21, 3, 9, 28, 16, 15, 22, 7, 23, 8, 1, 4, 30, 19);
        config=new HashMap<>();
        config.put("input",inputPanel);
        config.put("output",outputPanel);
        r=new Rotor(config);
        Assert.assertNotNull(r);
    }

    @Test
    public void testTranslate() throws Exception {
        for(Integer i=0;i<r.NUM_PIN;i++){
            assertEquals(config.get("output").get(i),r.translate(config.get("input").get(i)),0);
        }
    }

    @Test
    public void testHit() throws Exception {
        for(Integer i=0;i<r.NUM_PIN;i++){
            assertEquals(i,r.getHitCounter(),0);
            r.hit();
        }
    }
    @Test
    public void testHitNextRotor() throws  Exception {
        Rotor r2=new Rotor(config);
        r.previousRotor=r2;
        assertEquals(0,r2.getHitCounter());
        for(Integer i=0;i<r.NUM_PIN+1;i++){
            System.out.println("Hit*" + i);
            r.hit();
        }
        assertEquals(1,r2.getHitCounter());
    }
    @After
    public void cleanUpRotor() throws Exception {
        r.setHitCounter(0);
    }
}
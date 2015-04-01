package com.RotorMachine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rui.zheng on 01/04/2015
 */
public class KeyValueMapUnitTest {

    @Test
    public void testMapStringToValues() throws Exception {
        String s="Hello World.";
        assertEquals(s,KeyValueMap.mapValuesToString(KeyValueMap.mapStringToValues(s)));
    }
}
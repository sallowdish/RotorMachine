package com.RotorMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rui.zheng on 01/04/2015
 */
public class KeyValueMap {
    private static final HashMap<Character,Integer> keyValueMap=new HashMap<Character,Integer>(){{
        put('A',0);
        put('B',1);
        put('C',2);
        put('D',3);
        put('E',4);
        put('F',5);
        put('G',6);
        put('H',7);
        put('I',8);
        put('J',9);
        put('K',10);
        put('L',11);
        put('M',12);
        put('N',13);
        put('O',14);
        put('P',15);
        put('Q',16);
        put('R',17);
        put('S',18);
        put('T',19);
        put('U',20);
        put('V',21);
        put('W',22);
        put('X',23);
        put('Y',24);
        put('Z',25);
        put('[',26);
        put('\\',27);
        put(']',28);
        put('^',29);
        put('_',30);
        put('`',31);
        put('a',32);
        put('b',33);
        put('c',34);
        put('d',35);
        put('e',36);
        put('f',37);
        put('g',38);
        put('h',39);
        put('i',40);
        put('j',41);
        put('k',42);
        put('l',43);
        put('m',44);
        put('n',45);
        put('o',46);
        put('p',47);
        put('q',48);
        put('r',49);
        put('s',50);
        put('t',51);
        put('u',52);
        put('v',53);
        put('w',54);
        put('x',55);
        put('y',56);
    }};


    public static Integer mapKeyToValue(Character key){return keyValueMap.get(key);}
    public static Character mapValueToKey(Integer i){
        for(Map.Entry<Character,Integer> entry: keyValueMap.entrySet()){
            if (i.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
}

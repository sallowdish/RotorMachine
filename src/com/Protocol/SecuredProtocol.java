package com.Protocol;

import com.RotorMachine.EnigmaMachine;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class SecuredProtocol {
    private static final int SERVER_WAITING_SYNC = 0;
    private static final int CLIENT_WAITING_SYNC = 1;
    private static final int SERVER_READY = 2;
    private static final int CLIENT_READY = 3;
    public final String DELIMITER="#wetqew843&";

    private static final int NUMJOKES = 5;

    private int state = SERVER_WAITING_SYNC;
    private final int n = 5,q=23;

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    private Integer x1;
    private Integer x2;

    public Integer getY1() {
        return y1;
    }

    public Integer getY2() {
        return y2;
    }

    private Integer y1;
    private Integer y2;
    private Integer secretKey;

    public void initClient(){
        x1=Math.abs(new Random().nextInt(20))+5;
        y1=(int)Math.pow(n, x1)%q;
        state=CLIENT_WAITING_SYNC;
    }

    public void initServer(){
        x2= Math.abs(new Random().nextInt(20))+5;
        state=SERVER_WAITING_SYNC;
    }
    public String processInput(String theInput) throws Exception{
        String theOutput = null;

        if (state == SERVER_WAITING_SYNC) {
            String[] msg=null;
            if (theInput!=null) {
                System.out.println("Received: "+theInput);
                msg = theInput.split(DELIMITER);
            }
            if (msg==null || !msg[0].equals("SYNC") || msg.length!=2){
                theOutput="Error. Wait for sync up secretKey";
            }
            else{
                try {
                    y1=Integer.parseInt(msg[1]);
                    y2=(int)(Math.pow(n,x2)%q);
                    theOutput="SYNC"+DELIMITER+y2.toString();
                    secretKey=(int)(Math.pow(y1,x2)%q);
                    state = SERVER_READY;
                }catch (Exception e){
                    System.err.println("Sync up secret key failed: "+e.getMessage());
                    throw e;
                }
            }
        } else if (state == SERVER_READY) {
            String[] msg=null;
            if (theInput!=null) {
                msg = theInput.split(DELIMITER);
            }
            if (msg==null || !msg[0].equals("DATA")||msg.length!=2){
                theOutput="Error. Wait for encrypted message";
            }
            else{
                EnigmaMachine m=new EnigmaMachine(secretKey);
                System.out.println("Receive Message: "+msg[1]);
                String message=m.decrypt(msg[1]);
                System.out.println("Decrypted: "+message);
                String reversedMsg= new StringBuilder(message).reverse().toString();
                theOutput="DATA"+DELIMITER+new EnigmaMachine(secretKey).encrypt(reversedMsg);
            }
        } else if(state == CLIENT_WAITING_SYNC){
            String[] msg=null;
            if (theInput!=null) {
                System.out.println("Received: "+theInput);
                msg = theInput.split(DELIMITER);
            }
            if (msg==null || !msg[0].equals("SYNC") || msg.length!=2){
                theOutput="SYNC"+DELIMITER+x1.toString();
            }
            else{
                try {
                    y2=Integer.parseInt(msg[1]);
                    secretKey=(int)(Math.pow(y2,x1)%q);
                    state = SERVER_READY;
                    state=CLIENT_READY;
                    theOutput="CLIENT_READY";
                }catch (Exception e){
                    System.err.println("Sync up secret key failed: "+e.getMessage());
                    throw e;
                }
            }
        } else if(state==CLIENT_READY){
            String[] msg=null;
            if (theInput!=null) {
                msg = theInput.split(DELIMITER);
            }
            if (msg==null || (!msg[0].equals("DATA")&&!msg[0].equals("INIT"))||msg.length!=2){
                String err="Error. Wait for encrypted message";
                System.err.println(err);
            }else if(msg[0].equals("INIT")){
                Scanner in = new Scanner(System.in);
                System.out.println("Input next message(end with 'Enter'):");
                theOutput="DATA"+DELIMITER+new EnigmaMachine(secretKey).encrypt(in.nextLine());
            }
            else{
                EnigmaMachine m=new EnigmaMachine(secretKey);
                System.out.println("Receive Message: "+msg[1]);
                String message=m.decrypt(msg[1]);
                System.out.println("Decrypted: "+message);
                String reversedMsg= new StringBuilder(message).reverse().toString();
                Scanner in = new Scanner(System.in);
                System.out.println("Input next message(end with 'Enter'):");
                theOutput="DATA"+DELIMITER+new EnigmaMachine(secretKey).encrypt(in.nextLine());
            }
        }
        else{
            theOutput="Error. Unknown state.";
        }
        return theOutput;
    }
}

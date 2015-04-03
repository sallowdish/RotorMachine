package com.Client;

import com.Protocol.SecuredProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class SecuredClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java SecuredClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            SecuredProtocol sp=new SecuredProtocol();
            sp.initClient();

            System.out.println("Initializing com.Client...");
            System.out.println("Sync Secret Key with Server. Sending Xa="+sp.getX1());
            out.println("SYNC"+sp.DELIMITER+sp.getY1().toString());
            while ((fromServer = in.readLine()) != null) {
//                System.out.println("From Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;
                fromUser=sp.processInput(fromServer);
                if (fromUser != null) {
                    if(fromUser.equals("CLIENT_READY")) {
                        fromUser = sp.processInput("INIT" + sp.DELIMITER + "@");
                    }
                    out.println(fromUser);
                }
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unknown Error: "+e.getMessage());
            System.exit(1);
        }
    }
}

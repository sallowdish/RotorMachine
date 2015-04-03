package com.Server;

import com.Protocol.SecuredProtocol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by rui.zheng on 02/04/2015
 */
public class SecuredServer {
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Usage: java SecuredServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        PrintWriter out;
        BufferedReader in;
        String inputLine, outputLine;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                SecuredProtocol sp = new SecuredProtocol();
                sp.initServer();
//                outputLine = sp.processInput(null);
//                out.println(outputLine);

                while ((inputLine = in.readLine()) != null) {
                    outputLine = sp.processInput(inputLine);
                    out.println(outputLine);
                    if (outputLine.equals("Bye."))
                        break;
            }
        } catch (Exception e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            throw e;
        }

            // Initiate conversation with client


    }
}

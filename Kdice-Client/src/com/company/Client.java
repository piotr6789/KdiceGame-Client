package com.company;

import Logic.RandomGenerator;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)
    {
        try
        {
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket clientSocket = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                //LOGIN
                System.out.println(inputStream.readUTF());
                String toSend = RandomGenerator.generateRandomString();
                outputStream.writeUTF("LOGIN " + toSend);

                //START
                System.out.println(inputStream.readUTF());
            }

            // closing resources
//            scanner.close();
//            inputStream.close();
//            outputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

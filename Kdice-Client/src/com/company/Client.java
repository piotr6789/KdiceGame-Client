package com.company;

import Logic.GameLogic;
import Logic.RandomGenerator;
import Models.FieldModel;
import Models.PlayerModel;

import java.io.*;
import java.net.*;

public class Client {
    private static int counter = 0;
    private static  FieldModel[][] _board = new FieldModel[5][5];

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

            PlayerModel player = new PlayerModel(inputStream, outputStream, clientSocket);

            // login and generating board
            while (counter == 0)
            {
                //LOGIN
                System.out.println(player.getInputStream().readUTF());
                String toSend = RandomGenerator.generateRandomString();
                player.setLogin(toSend);
                player.getOutputStream().writeUTF("LOGIN " + toSend);
                String ID = player.getInputStream().readUTF();
                player.setId(GameLogic.setID(ID));
                System.out.println(ID);

                //BOARD
                String board = " ";
                int start = 2;
                int end = 17;
                if(counter == 0) {
                    for (int i = 0; i < 25; i++) {
                        board = board + " " + player.getInputStream().readUTF();
                        System.out.println(board.substring(start,end));
                        start = start + 16;
                        end = end + 16;
                    }
                    counter++;
                }
                _board = GameLogic.genereteBoard(board);
            }

            try{
                String move = player.getInputStream().readUTF();
                if(move.equals("TWOJ RUCH")){
                    System.out.println(move);
                    player.getOutputStream().writeUTF(GameLogic.generateAttack(_board, player));
                }
            }
            catch(Exception e){

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

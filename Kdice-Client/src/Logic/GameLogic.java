package Logic;

import Models.FieldModel;
import Models.PlayerModel;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class GameLogic
{

    public static FieldModel[][] genereteBoard(String board)
    {
        FieldModel[][] _board = new FieldModel[5][5];
        for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 5; j++) {
                _board[i][j] = new FieldModel();
             }
        }
        String replaceString = board.replaceAll("\\s","");
        String finalString = replaceString.replaceAll("[^0-9.]", "");

        _board[0][0].set_ownerId(Character.getNumericValue(finalString.charAt(2)));
        _board[0][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(3)));
        _board[0][1].set_ownerId(Character.getNumericValue(finalString.charAt(6)));
        _board[0][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(7)));
        _board[0][2].set_ownerId(Character.getNumericValue(finalString.charAt(10)));
        _board[0][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(11)));
        _board[0][3].set_ownerId(Character.getNumericValue(finalString.charAt(14)));
        _board[0][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(15)));
        _board[0][4].set_ownerId(Character.getNumericValue(finalString.charAt(18)));
        _board[0][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(19)));
        _board[1][0].set_ownerId(Character.getNumericValue(finalString.charAt(22)));
        _board[1][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(23)));
        _board[1][1].set_ownerId(Character.getNumericValue(finalString.charAt(26)));
        _board[1][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(27)));
        _board[1][2].set_ownerId(Character.getNumericValue(finalString.charAt(30)));
        _board[1][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(31)));
        _board[1][3].set_ownerId(Character.getNumericValue(finalString.charAt(34)));
        _board[1][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(35)));
        _board[1][4].set_ownerId(Character.getNumericValue(finalString.charAt(38)));
        _board[1][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(39)));
        _board[2][0].set_ownerId(Character.getNumericValue(finalString.charAt(42)));
        _board[2][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(43)));
        _board[2][1].set_ownerId(Character.getNumericValue(finalString.charAt(46)));
        _board[2][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(47)));
        _board[2][2].set_ownerId(Character.getNumericValue(finalString.charAt(50)));
        _board[2][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(51)));
        _board[2][3].set_ownerId(Character.getNumericValue(finalString.charAt(54)));
        _board[2][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(55)));
        _board[2][4].set_ownerId(Character.getNumericValue(finalString.charAt(58)));
        _board[2][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(59)));
        _board[3][0].set_ownerId(Character.getNumericValue(finalString.charAt(62)));
        _board[3][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(63)));
        _board[3][1].set_ownerId(Character.getNumericValue(finalString.charAt(66)));
        _board[3][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(67)));
        _board[3][2].set_ownerId(Character.getNumericValue(finalString.charAt(70)));
        _board[3][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(71)));
        _board[3][3].set_ownerId(Character.getNumericValue(finalString.charAt(74)));
        _board[3][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(75)));
        _board[3][4].set_ownerId(Character.getNumericValue(finalString.charAt(78)));
        _board[3][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(79)));
        _board[4][0].set_ownerId(Character.getNumericValue(finalString.charAt(82)));
        _board[4][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(83)));
        _board[4][1].set_ownerId(Character.getNumericValue(finalString.charAt(86)));
        _board[4][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(87)));
        _board[4][2].set_ownerId(Character.getNumericValue(finalString.charAt(90)));
        _board[4][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(91)));
        _board[4][3].set_ownerId(Character.getNumericValue(finalString.charAt(94)));
        _board[4][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(95)));
        _board[4][4].set_ownerId(Character.getNumericValue(finalString.charAt(98)));
        _board[4][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(99)));

        return _board;
    }
    public static void printBoard(DataOutputStream outputStream, FieldModel[][] board) throws IOException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                outputStream.writeUTF("PLANSZA " + i + " " + j + " " + board[i][j].get_ownerId() + " " + board[i][j].get_cubesNumber());
            }
        }
    }

    public static int setID(String ID)
    {
        String newString = ID.replaceAll("\\s","");
        int finalID = Character.getNumericValue(newString.charAt(5));

        return finalID;
    }

    public static String generateAttack(FieldModel[][] board, PlayerModel player) {
        getFields(board, player);
        String attackCommand = "ATAK ";
        int numberOfFields = player.getMyField().size();
        for (int i = 0; i < numberOfFields; i++) {
            if (player.getMyField().get(i).x == 0) {
                if (board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_cubesNumber()
                        < board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x + 1) + " " + player.getMyField().get(i).y;
                }
            } else if (player.getMyField().get(i).x == 4) {
                if (board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_cubesNumber()
                        < board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x - 1) + " " + player.getMyField().get(i).y;
                }
            }
        }
        return "PASS";
    }

    public static void getFields(FieldModel[][] board, PlayerModel player)
    {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(board[i][j].get_ownerId() == player.getId()){
                    player.getMyField().add(new Point(i, j));
                }
            }
        }
    }
}

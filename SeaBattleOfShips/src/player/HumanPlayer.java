package player;
import java.awt.*;
import java.util.Scanner;

//import Player;
import game.Board;
import result.Result;

public class HumanPlayer implements Player {

    /*
    static final int FILED_LENGTH = 16;
    private String name;
    private char[][] ownBoard;
    private char[][] opponentBoard;

    public HumanPlayer(String name) {
        this.name = name;
        this.ownBoard = new char[FILED_LENGTH][FILED_LENGTH];
        this.opponentBoard = new char[FILED_LENGTH][FILED_LENGTH];
    }

    public void placeShips() {
        // Ваш код для расстановки кораблей здесь
    }

    public void makeMove(HumanPlayer opponent) {
        // Ваш код для совершения хода здесь
    }*/

    private int totalLivesLeft = 17;
    private int id;
    private Board board;
    private Scanner scanner;

    public HumanPlayer(int id) {
        this.id = id;
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void placeShips() {
        System.out.printf("%n================== Player %d - Time to place out your ships ==================%n", id);
        board.placeShipsOnBoard();
    }

    @Override
    public void fireAt(Player opponent) {
        System.out.printf("%n Alright Player %d - Enter coordinates for your attack: ", id);

        boolean isPointValid = false;
        while(!isPointValid) {
            try {
                Point point = new Point(scanner.nextInt(), scanner.nextInt());
                int x = (int)point.getX() - 1;
                int y = (int)point.getY() - 1;

                Result result = ((HumanPlayer)opponent)
                        .getBoard()
                        .getField(x, y)
                        .shootAt();

                if(result == Result.PARTIAL_HIT ||  result == Result.DESTROYED) {
                    totalLivesLeft--;
                }

                isPointValid = true;
            } catch(IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public int getTotalLivesLeft() {
        return totalLivesLeft;
    }
}

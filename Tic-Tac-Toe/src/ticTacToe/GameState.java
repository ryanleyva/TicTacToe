package ticTacToe;

import java.awt.*;
import java.util.ArrayList;

public class GameState {
    private Space[][] board;
    private int availableSpaces;
    private Point move;
    private ArrayList<GameState> nextBoards = new ArrayList<GameState>();

    public GameState(Space[][] board, int availableSpaces, Point move) {
        this.move = move;
        this.board = board;
        this.availableSpaces = availableSpaces;
    }

    public Space[][] getBoard() {
        return board;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public Point getMove() {
        return move;
    }

    public void setMove(Point move) {
        this.move = move;
    }

    public ArrayList<GameState> getNextBoards(boolean isX) {
        if (nextBoards.isEmpty())
        {
            genNextBoards(isX);
        }
        return nextBoards;
    }

    public void showBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        int i, j, n = board.length;
        for (i = 0; i < n; i++) {
            stringBuilder.append("[ ");
            for (j = 0; j < n; j++) {
                if (board[i][j] == Space.X) {
                    stringBuilder.append("X ");
                }
                else if (board[i][j] == Space.O) {
                    stringBuilder.append("O ");
                }
                else {
                    stringBuilder.append("E ");
                }
            }
            stringBuilder.append(" ]");
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder.toString());
    }


    private ArrayList<GameState> genNextBoards(boolean isX) {
        Space space;
        if (isX) {
            space = Space.X;
        }
        else {
            space = Space.O;
        }

        int i, j, n = board.length;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (board[i][j] == Space.EMPTY) {
                    Space[][] newBoard = deepCopy();
                    newBoard[i][j] = space;
                    nextBoards.add(new GameState(newBoard, availableSpaces + 1, new Point(i,j)));
                }
            }
        }
        return nextBoards;
    }

    private Space[][] deepCopy() {
        int n = board.length;
        Space[][] newBoard = new Space[n][n];
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }


}

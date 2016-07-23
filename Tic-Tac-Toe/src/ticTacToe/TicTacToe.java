package ticTacToe;

public class TicTacToe {

    public int minMax(GameState gameState, int depth, boolean isMax) {
        if (depth == 0 || isLeaf(gameState)) {
            return evalulateLeaf(gameState);
        }
        else {
            if (isMax) {
                int max = Integer.MIN_VALUE;
                for (GameState nextGame : gameState.getNextBoards(false)) {
                    int bestVal = minMax(nextGame, depth-1 , false);
                    max = Math.max(max, bestVal);
                }
                return max;
            }
            else {
                int min = Integer.MIN_VALUE;
                for (GameState nextGame : gameState.getNextBoards(true)) {
                    int bestVal = minMax(nextGame, depth-1 , true);
                    min = Math.min(min, bestVal);
                }
                return min;
            }
        }
    }

    public int evalulateLeaf(GameState gameState) {
        int count = 0;
        Space[][] board = gameState.getBoard();
        // across check
        for (int i = 0; i < board.length; i ++) {
            Space[] horizontal = board[i];
            count += match(horizontal);
        }
        //vertical check
        for (int i = 0; i < board.length; i ++) {
            Space[] vertical = {board[0][i], board[1][i], board[2][i]};
            count += match(vertical);
        }
        //diagonal check
        Space[] d1 = {board[0][0], board[1][1], board[2][2]};
        count += match(d1);

        Space[] d2 = {board[0][2], board[1][1], board[2][0]};
        count += match(d2);

        return count;
    }

    private int match(Space[] subBoard) {
        if (subBoard[0] == Space.O) {
            if (subBoard[0] == subBoard[1] && subBoard[1] == subBoard[2]) {
                return 1;
            }
        }
        else if (subBoard[0] == Space.X) {
            if (subBoard[0] == subBoard[1] && subBoard[1] == subBoard[2]) {
                return -1;
            }
        }
        return 0;
    }

    private boolean isLeaf(GameState gameState) {
        if (gameState.getAvailableSpaces() == 0) {
            return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        Space[][] board = { {Space.X, Space.X, Space.X},
                            {Space.O, Space.O, Space.O},
                            {Space.O, Space.O, Space.O}};

        TicTacToe ticTacToe = new TicTacToe();
        int count = ticTacToe.evalulateLeaf(new GameState(board, 0));
        System.out.println(count);
    }
}

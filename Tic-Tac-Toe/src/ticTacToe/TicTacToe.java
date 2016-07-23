package ticTacToe;

public class TicTacToe {

    public Move minMax(GameState gameState, int tiers, int depth, boolean isMax) {
        if (tiers == 0 || isLeaf(gameState)) { //|| gameWon(gameState)) {
            int score = evalulateLeaf(gameState);
            return new Move(gameState.getMove(), score);
        }
        else {
            if (isMax) {
                Move max = new Move(null, Integer.MIN_VALUE);
                for (GameState nextGame : gameState.getNextBoards(false)) {
                    Move bestMove = minMax(nextGame, tiers-1 , depth+1, false);
                    bestMove.coord = nextGame.getMove();
                    max = maxMove(bestMove, max);
                }
                return max;
            }
            else {
                Move min = new Move(null, Integer.MAX_VALUE);
                for (GameState nextGame : gameState.getNextBoards(true)) {
                    Move bestMove = minMax(nextGame, tiers-1 , depth+1, true);
                    bestMove.coord = nextGame.getMove();
                    min = minMove(min, bestMove);
                }
                return min;
            }
        }
    }

    private Move maxMove(Move m1, Move m2) {
        if (m1.score > m2.score) {
            return m1;
        }
        else return m2;
    }

    private Move minMove(Move m1, Move m2) {
        if (m1.score < m2.score) {
            return m1;
        }
        else return m2;
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

    private boolean gameWon(GameState gameState) {
        int score = evalulateLeaf(gameState);
        if (score != 0) {
            return true;
        }
        return false;
    }

    private int match(Space[] subBoard) {
        if (subBoard[0] == Space.O) {
            if (subBoard[0] == subBoard[1] && subBoard[1] == subBoard[2]) {
                return 10;
            }
        }
        else if (subBoard[0] == Space.X) {
            if (subBoard[0] == subBoard[1] && subBoard[1] == subBoard[2]) {
                return -10;
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
        Space[][] board = { {Space.EMPTY, Space.EMPTY, Space.O},
                            {Space.O, Space.X, Space.EMPTY},
                            {Space.EMPTY, Space.EMPTY, Space.X}};

        GameState gameState = new GameState(board, 9, null);
        //gameState.showBoard();
        TicTacToe ticTacToe = new TicTacToe();

       /* for (GameState gs : gameState.getNextBoards(true)) {
            System.out.println(gs.getMove());
            System.out.println(ticTacToe.evalulateLeaf(gs));
            gs.showBoard();
      }*/

        Move move = ticTacToe.minMax(gameState, 5, 0, true);
        System.out.println(move.score + "  |  " + move.coord.toString());

        //int count = ticTacToe.evalulateLeaf(new GameState(board, 0,null));
        //System.out.println(count);
    }
}

package ProjectClasses;

import java.util.HashSet;

public class TicTacToeBoard {

	static final int TicTacToe_Width = 3;
	static String Blank = "-";
	static String Human = "X";
	static String AI = "O";
    String [][] Board; 
	String playersTurn = Blank;
	String Gamewinner = Blank;
	HashSet<Integer> AllAvailable;
	int TotalMoves;
	boolean GameFinish;
	
	TicTacToeBoard(){
		this.Board = new String[TicTacToe_Width][TicTacToe_Width];
		AllAvailable = new HashSet<>();
		ReArrangeTicTacToe();
	}
	
    void ReArrangeTicTacToe () {
        TotalMoves = 0;
        GameFinish = false;
        playersTurn = Blank;
        Gamewinner = Blank;
        AllAvailable.clear();
        
        for (int row = 0; row < TicTacToe_Width; row++) {
            for (int col = 0; col < TicTacToe_Width; col++) {
            	Board[row][col] = Blank;
            }
        }
        
        for (int i = 0; i < TicTacToe_Width*TicTacToe_Width; i++) {
            AllAvailable.add(i);
        }
    }
    
    public boolean move (int index) {
    	
    	if (GameFinish) {
           return false;
         }
    	
        int x = index/ TicTacToe_Width;
        int y = index% TicTacToe_Width;

        if (Board[x][y] == Blank) {
            Board[x][y] = playersTurn;
        } else {
            return false;
        }

        TotalMoves++;
        int singlevalue = x * TicTacToeBoard.TicTacToe_Width + y;
        AllAvailable.remove(singlevalue);
        
        if (TotalMoves == TicTacToe_Width * TicTacToe_Width) {
            Gamewinner = Blank;
            GameFinish = true;
        }
        
        AnyRowSame(x);
        AnyColumnSame(y);
        TopLeftDiagonalSame(x,y);
        TopRightDiagonalSame(x, y);
        
        if(playersTurn == Human) 
        	playersTurn = AI;
        else
        	playersTurn = Human;
        
        return true;
    }
    
    public void AnyRowSame (int row) {
        for (int i = 1; i < TicTacToe_Width; i++) {
            if (Board[row][i] != Board[row][i-1]) {
                break;
            }
            if (i == TicTacToe_Width -1) {
                Gamewinner = playersTurn;
                GameFinish = true;
            }
        }
    }
    
    public void AnyColumnSame (int column) {
        for (int i = 1; i < TicTacToe_Width; i++) {
            if (Board[i][column] != Board[i-1][column]) {
                break;
            }
            if (i == TicTacToe_Width -1) {
                Gamewinner = playersTurn;
                GameFinish = true;
            }
        }
    }
    
    public void TopLeftDiagonalSame (int x, int y) {
        if (x == y) {
            for (int i = 1; i < TicTacToe_Width; i++) {
                if (Board[i][i] != Board[i-1][i-1]) {
                    break;
                }
                if (i == TicTacToe_Width -1) {
                    Gamewinner = playersTurn;
                    GameFinish = true;
                }
            }
        }
    }
    
    
    public void TopRightDiagonalSame (int x, int y) {
        if (TicTacToe_Width -1-y == x) {
            for (int i = 1; i < TicTacToe_Width; i++) {
                if (Board[TicTacToe_Width -1-i][i] != Board[TicTacToe_Width -i][i-1]) {
                    break;
                }
                if (i == TicTacToe_Width -1) {
                    Gamewinner = playersTurn;
                    GameFinish = true;
                }
            }
        }
    }
    
    public TicTacToeBoard  CopyStatus() {
        TicTacToeBoard board  = new TicTacToeBoard();

        for (int i = 0; i < board.Board.length; i++) 
           for (int j = 0; j < Board.length; j++) 
        	   board.Board[i][j] = this.Board[i][j];

        board.playersTurn   = this.playersTurn;
        board.Gamewinner    = this.Gamewinner;
        board.AllAvailable  = new HashSet<>();
        board.AllAvailable.addAll(this.AllAvailable);
        board.TotalMoves    = this.TotalMoves;
        board.GameFinish    = this.GameFinish;
        
        return board;
    }

    public boolean IsGameFinish () {
        return GameFinish;
    }
    
    public String getTurn () {
        return playersTurn;
    }
    
    public String getWinner () {
        if (!GameFinish) {
            System.out.println("TicTacToe is not over yet.");
        }
        return Gamewinner;
    }
    
    public HashSet<Integer> getAvailableMoves () {
        return AllAvailable;
    }

}

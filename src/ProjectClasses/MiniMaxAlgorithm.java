package ProjectClasses;

public class MiniMaxAlgorithm {
	   private static double maxPlayer = Double.POSITIVE_INFINITY;
	   static int counter = 0;
	   private MiniMaxAlgorithm() {}
	  	   
	   private static int MaxTurn (String player, TicTacToeBoard board, int ExsitPlyerMove) {
	        double ScoreofBest = Double.NEGATIVE_INFINITY;
	        int bestIndexMove = -1;

	        for (Integer index : board.getAvailableMoves()) {
	        	
	            TicTacToeBoard modifiedBoard = board.CopyStatus();
	            modifiedBoard.move(index);
	           
	            int score = MiniMaxFunction(player, modifiedBoard, ExsitPlyerMove);

	            if (score >= ScoreofBest) {
	                ScoreofBest = score;
	                bestIndexMove = index;
	            }

	        }
	        board.move(bestIndexMove);
	        return (int)ScoreofBest;
	    }
	   
	   public static int MiniMaxFunction (String player, TicTacToeBoard board, int ExsitPlyerMove) {
	        if (ExsitPlyerMove++ == maxPlayer || board.IsGameFinish()) {
	        	
	            return Utility(player, board);
	        }
	        
	        if (board.getTurn() == player) {
	            return MaxTurn(player, board, ExsitPlyerMove);
	        } else {
	            return MinTurn(player, board, ExsitPlyerMove);
	        }

	    }

	   
	   private static int MinTurn (String player, TicTacToeBoard board, int ExsitPlyerMove) {
	        double ScoreofBest = Double.POSITIVE_INFINITY;
	        int bestIndexMove = -1;

	        for (Integer index : board.getAvailableMoves()) {

	            TicTacToeBoard modifiedBoard = board.CopyStatus();
	            modifiedBoard.move(index);
	            
	            int score = MiniMaxFunction(player, modifiedBoard, ExsitPlyerMove);

	            if (score <= ScoreofBest) {
	                ScoreofBest = score;
	                bestIndexMove = index;
	            }

	        }
	        //System.out.println(++counter);
	        board.move(bestIndexMove);
	        return (int)ScoreofBest;
	    }
	   
	   private static int Utility(String player, TicTacToeBoard board) {
	        if (player == TicTacToeBoard.Blank) {
	            System.out.println("Player must be X or O.");
	        }
	        counter++;
	        String bot = (player == TicTacToeBoard.Human) ? TicTacToeBoard.AI : TicTacToeBoard.Human;

	        if (board.IsGameFinish() && board.getWinner() == player) {
	            return 10;
	        } else if (board.IsGameFinish() && board.getWinner() == bot) {
	            return -10;
	        } else {
	            return 0;
	        }
	    }

}

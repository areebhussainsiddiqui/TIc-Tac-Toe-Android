package ProjectClasses;

import java.util.Scanner;



public class MianClass {
	
	public TicTacToeBoard ticknCross;
	public int ChooseAlogorithm = 0;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		MianClass Game = new MianClass();
        Game.Start();
	}
	
	MianClass(){
		ticknCross = new TicTacToeBoard();
	}
	
	private void Start() {
		String p1 ="New Game Started"; 
		System.out.println(p1);
		ChooseAlogorithm = sc.nextInt();
		String p2 = "Who play first ?";
		String p3 = " Enter O for BOT   OR X for YOU !";
		System.out.println("\n"+ p2 + "\n" + p3);
		
		String start = sc.next();
		
		if(!(start.equals("X") || start.equals("x") || start.equals("O") || start.equals("o") || start.equals("0"))) {
			System.out.println("You have Enter Invalid Choice Try Again!");
			Start();
		}
		else
			start = (start.equals("x") || start.equals("X")) ? "X" : "O"; 

		this.ticknCross.playersTurn = start;
        while (true) {
        	GAMELOG();
            turnMove();

            if (ticknCross.IsGameFinish()) {
                printWinner();

                if (!PlayAgain()) {
                    break;
                }
            }
        }
    }
	
	private void printWinner () {
        String winner = ticknCross.getWinner();

        GAMELOG();

        if (winner == TicTacToeBoard.Blank) {
            System.out.println(" Draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

	private boolean PlayAgain () {
		  String response;

			  System.out.print("PLAY AGAIN? (Y/N): ");
	            response = sc.next();
	            if (response.equalsIgnoreCase("y")) {
	            	for (int i = 0; i < 40; i++) {
						System.out.println();
					}
	            	ticknCross.ReArrangeTicTacToe();
	                String p1 ="\nNew Game Started"; 
	        		System.out.println(p1);
	        		ChooseAlogorithm = sc.nextInt();
	        		String p2 = "Who play first ?";
	        		String p3 = " Enter O for BOT   OR X for YOU !";System.out.println(p1+"\n"+ p2 + "\n" + p3);
	        		
	        		String start = sc.next();
	        		
	        		if(!(start.equals("X") || start.equals("x") || start.equals("O") || start.equals("o") || start.equals("0"))) {
	        			System.out.println("Wrong selection");
	        			Start();
	        		}
	        		else
	        			start = (start.equals("x") || start.equals("X")) ? "X" : "O"; 

	        		this.ticknCross.playersTurn = start;
	                return true;
	            } 
	            else if (response.equalsIgnoreCase("n")) {
	                return false;
	            }
	            System.out.println("Wrong output.");
		  return true;
		  
	    }

	private void GAMELOG () {
        System.out.println("MOVES");
        for (int i = 0; i < this.ticknCross.Board.length; i++) {
			for (int j = 0; j < this.ticknCross.Board.length; j++) {
				System.out.print(this.ticknCross.Board[i][j] + " ");
			}
			System.out.println();
		}
       if(!ticknCross.IsGameFinish())
        System.out.println("\n" + ticknCross.getTurn() + "'s turn.");
       System.out.println();
    }
	
	 private void turnMove () {
	        if (this.ticknCross.getTurn() == TicTacToeBoard.Human) {
	        	
		        System.out.print("enter the box number 0 TO 8  ");  
		  		System.out.println(" \n  move: ");
	
		  	    int move = sc.nextInt();
	
		  	    if (move < 0 || move >= TicTacToeBoard.TicTacToe_Width * TicTacToeBoard.TicTacToe_Width) {
		  	            System.out.println("\nInvalid move.");
		  	            System.out.println("\nThe index of the move must be between 0 and "
		  	                    + (TicTacToeBoard.TicTacToe_Width * TicTacToeBoard.TicTacToe_Width - 1) + ", inclusive.");
		  	        } 
		  	    else if (!ticknCross.move(move)) {
		  	            System.out.println("\n wrong move.");
		  	            System.out.println("\n your thinking places still empty");
		  	       }
		        } 
		        else {
		        	if(ChooseAlogorithm == 1) {
		            MiniMaxAlgorithm.MiniMaxFunction(ticknCross.getTurn(), ticknCross, 0);
		        	}
		        	}
	        }
	 
	  	  
	  }	

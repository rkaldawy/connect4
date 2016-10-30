import java.util.Scanner;

/*
 this version:
 encapsulates data
 optimizes helper use
 properly checks for the incorrect input
 tracks and shows where wins occur
 DISPLAYS TO A GUI
 HAS AN AI OPTION
 */

public class Main {
	
	public static Integer pInput(int p) throws NumberFormatException{
		Scanner keyboard = new Scanner(System.in);
		System.out.println();
		System.out.println("Player " + p + ": which column will you play in?");
		String c = keyboard.next();
		Integer intC = Integer.parseInt(c);
			if (1 <= intC && intC <= 7)
				return intC;
			else
				throw new NumberFormatException();
	}
	
	public static Integer pPrint(int p){
		Integer c;
		try{
			c = pInput(p);
			return c;
		}
		catch (NumberFormatException e){
			System.out.print("Not a valid entry.");
			return pPrint(p);
		}
	}
	
	//the while loop needs to be looped
	public static void main(String[] args) {
		TestAI newAI = new TestAI();
		Board newGame = new Board();
		newGame.printBoard();
		Scanner keyboard = new Scanner(System.in);
		Token O = new Token ("O");
		Token X = new Token ("X");
		boolean alwaysPlay = false;
		while (alwaysPlay == false){
			Integer c = pPrint(1);
			newGame.addToken(c, X);
			newGame.printBoard();
			try{
			WinResult gameEnd = newGame.checkWinner();
			System.out.println();
			System.out.print("Player 1 has won ");
			gameEnd.printResult();
			break;
			}
			catch (NoWinException e){}
			
			
			Integer d = newAI.randomNumber();
			//Integer d = pPrint(2);
			newGame.addToken(d, O);
			newGame.printBoard();
			try{
				WinResult gameEnd = newGame.checkWinner();
				System.out.println();
				System.out.print("Player 2 has won ");
				gameEnd.printResult();
				break;
				}
				catch (NoWinException e){}
		}
	}
}
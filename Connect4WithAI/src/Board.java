import java.util.HashMap;
import java.util.LinkedList;

public class Board {
	private HashMap<Integer, BoardRow> board;
	
	Board(HashMap<Integer, BoardRow> board){
		this.board = board;
	}
	
	/*Board(BoardRow A, BoardRow B, BoardRow C, BoardRow D, BoardRow E, BoardRow F){
		// row 1 is the highest row, so A is the highest row
		this.board = new HashMap<Integer, BoardRow>();
		board.put(1, A);
		board.put(2, B);
		board.put(3, C);
		board.put(4, D);
		board.put(5, E);
		board.put(6, F);
	}*/
	
	Board(){
		this.board = new HashMap<Integer, BoardRow>();
		board.put(1, new BoardRow());
		board.put(2, new BoardRow());
		board.put(3, new BoardRow());
		board.put(4, new BoardRow());
		board.put(5, new BoardRow());
		board.put(6, new BoardRow());
	}
	
	public void printBoard(){
		int n = 1;
		System.out.println();
		System.out.println("       1   2   3   4   5   6   7  ");
		System.out.println("     =============================");
		while (n <= 6){
			BoardRow r = this.board.get(n);
			System.out.print("     | ");
			r.printRow();
			System.out.println(7-n);
			System.out.println("     =============================");
			n++;
		}
	}
	
	public void addToken(int c, Token pTok){
		int col = c-1;
		int n = 1;
		while (n<=6){
			BoardRow r = this.board.get(n);
			if(n==1 && (!r.isCertainElt(col, " "))){
				break;
			}
			else if (n != 1){
				BoardRow prevR = this.board.get(n-1);
				if (n==6 && r.isCertainElt(col, " ")){
					r.replaceToken(col,  pTok);
					this.board.put(n, r);
					break;
				}
				else if (!r.isCertainElt(col, " ")){
					prevR.replaceToken(col,  pTok);
					this.board.put(n-1, prevR);
					break;
				}}
			n++;
			}}
	
	public WinResult checkWinner() throws NoWinException{
		int n = 1;
		while(n<=6){
			BoardRow r = this.board.get(n);
			try{
				return r.checkHoritz(n);
			}
			catch(NoWinException e){}
			try{
				return this.checkVert(r, n);
			}
			catch(NoWinException e){}
			try{
				return this.checkDiag(r, n);
			}
			catch(NoWinException e){}
			n++;
		}
		throw new NoWinException();
	}
	
	public boolean checkVertStack(int n, int i, String s){
		return this.board.get(n+1).isCertainElt(i, s) && this.board.get(n+2).isCertainElt(i, s) && this.board.get(n+3).isCertainElt(i, s);
	}
	
	public boolean checkRightDiag(int n, int i, String s){
		return this.board.get(n-1).isCertainElt(i+1, s) && this.board.get(n-2).isCertainElt(i+2, s) && this.board.get(n-3).isCertainElt(i+3, s);
	}
	
	public boolean checkLeftDiag(int n, int i, String s){
		return this.board.get(n-1).isCertainElt(i-1, s) && this.board.get(n-2).isCertainElt(i-2, s) && this.board.get(n-3).isCertainElt(i-3, s);
	}
	
	public WinResult checkVert(BoardRow r, int n) throws NoWinException{
		LinkedList<Integer> xVert = r.possibleXVert();
		LinkedList<Integer> oVert = r.possibleOVert();
		
		for (Integer i : xVert){
			if (n+3 <=6 && this.checkVertStack(n, i, "X")){
					return new WinResult(n, i, "vertical play");
					}}
		for (Integer i : oVert){
			if (n+3 <=6 && this.checkVertStack(n, i, "O")){
					return new WinResult(n, i, "vertical play");
					}}
		throw new NoWinException();
	}
	
	public WinResult checkDiag(BoardRow r, int n) throws NoWinException{
		LinkedList<Integer> xVert = r.possibleXVert();
		LinkedList<Integer> oVert = r.possibleOVert();
		
		for (Integer i : xVert){
			if (n-3 >= 0 && i+3 <=6 && this.checkRightDiag(n, i, "X")){
					return new WinResult(n, i, "rightward diagonal play");
					}
			else if (n-3 >= 0 && i-3 >= 0 && this.checkLeftDiag(n, i, "X")){
					return new WinResult(n, i, "leftward diagonal play");
					}}
		for (Integer i : oVert){
			if (n-3 >=0 && i+3 <= 6 && this.checkRightDiag(n, i, "O")){
					return new WinResult(n, i, "rightward diagonal play");
					}
			else if (n+3 <=6 && i-3 >= 0 && this.checkLeftDiag(n, i, "O")){
					return new WinResult(n, i, "leftward diagonal play");
					}}
		throw new NoWinException();
	}}
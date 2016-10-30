import java.util.LinkedList;

public class BoardRow {
	 private LinkedList<Token> row;
	
	BoardRow(LinkedList<Token> row){
		this.row = row;
	}
	
	BoardRow(){
		this.row = new LinkedList<Token>();
		row.add(new Token(" "));
		row.add(new Token(" "));
		row.add(new Token(" "));
		row.add(new Token(" "));
		row.add(new Token(" "));
		row.add(new Token(" "));
		row.add(new Token(" "));
	}
	
	public boolean checkHoritzStack(int i, String s){
		return this.isCertainElt(i,  s) && this.isCertainElt(i+1, s) && this.isCertainElt(i+2, s) && this.isCertainElt(i+3, s);
	}
	
	public WinResult checkHoritz(int n) throws NoWinException{
		int i;
		for (i = 0; i < this.row.size(); i++){
			if (i+3 < this.row.size()){
				if (this.checkHoritzStack(i, "O")) {
					return new WinResult(n, i, "horizontal play");
				}
				else if (this.checkHoritzStack(i, "X")){
					return new WinResult(n, i, "horizontal play");
			}}}
		throw new NoWinException();
	}
	
	public LinkedList<Integer> possibleXVert(){
		LinkedList<Integer> intList = new LinkedList<Integer>();
		int i;
		for (i = 0; i < this.row.size(); i++){
			if (this.isCertainElt(i, "X")){
				intList.add(i);
			}
		}
		return intList;
	}
	
	public LinkedList<Integer> possibleOVert(){
		LinkedList<Integer> intList = new LinkedList<Integer>();
		int i;
		for (i = 0; i < this.row.size(); i++){
			if (this.isCertainElt(i, "O")){
				intList.add(i);
			}
		}
		return intList;
	}
	
	public void printRow(){
		for (Token t: this.row){
			System.out.print(t.getType() + " | ");
		}
	}
	
	public boolean isCertainElt(int col, String s){
		return this.row.get(col).getType().equals(s);
	}
	
	public void replaceToken(int col, Token pTok){
		this.row.remove(col);
		this.row.add(col, pTok);
	}
}

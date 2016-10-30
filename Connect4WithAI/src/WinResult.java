
public class WinResult {
	Integer rowNum;
	Integer colNum;
	String winType;
	
	WinResult(Integer rowNum, Integer colNum, String winType){
		this.rowNum = 7 - rowNum;
		this.colNum = colNum + 1;
		this.winType = winType;
	}
	
	public void printResult(){
		System.out.println("with a " + winType + " starting at position: Row " + this.rowNum + ", Column " + this.colNum + ".");
	}
	
}

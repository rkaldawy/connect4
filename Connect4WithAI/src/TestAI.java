import java.util.Random;

public class TestAI {
	
	public TestAI(){}
	
	public Integer randomNumber(){
		Random generator = new Random();
		int i = generator.nextInt(6) + 1;
		return i;
	}

}

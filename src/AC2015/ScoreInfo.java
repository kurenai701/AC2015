package AC2015;

import java.io.Serializable;




// Cette classe ScoreInfo permettra de modeliser de fa�on plus comlexe

public class ScoreInfo implements Serializable {
	
	

	private static final long serialVersionUID = 43L;
		
	int score;
	int test;
	
	public ScoreInfo(){}	
		
	public ScoreInfo(int score) {
		super();
		this.score = score;
	}
	
	
	
}

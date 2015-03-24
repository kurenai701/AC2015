package AC2015;

import java.util.ArrayList;
import java.util.List;


/*
 *  OutputModel : classe Output
 *  @author : Clemence MEGE
 */
  
public class OutputModel {
		
	
	// private
	ProblemModel pb;
		
	public OutputModel(){}
		
	public OutputModel(ProblemModel pb) {
		super();
		this.pb = pb;		
	
	}
	
	
	public int GetScore()
	{
		return -42;
	}
	
	public ScoreInfo GetScoreModel()
	{
		return new ScoreInfo(-314);
	}
	
	
}

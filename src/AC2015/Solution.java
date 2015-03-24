package AC2015;

import java.io.Serializable;


/*
 *  OutputModel : classe Output
 *  @author : Clemence MEGE
 */
  
public class Solution implements Serializable {
	
	// private for test
	int testSolInt;
	String testSolString;
	
	// private
	Problem pb;
		
	public Solution(){}
		
	public Solution(Problem pb) {
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
	
	
	public void SaveSolutionAsRaw(String fileName)
	{			
		CommonStatic.FU.SerializeObjectToFile(this, CommonStatic.ACFinalFilesFolderPath+fileName);
	}	
}

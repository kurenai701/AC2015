package AC2015;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
 *  OutputModel : classe Output
 *  @author : Clemence MEGE
 */
  
public class Solution implements Serializable {
	
	
	private static final long serialVersionUID = 42L;
	
	// for test
	int testSolInt;
	String testSolString;
	List<ClemClass> testSolListClemClass = new ArrayList<ClemClass>();
	TreeSet testTreeSet = new TreeSet();
	
	// problem
	Problem pb;
		
	public Solution(){}
		
	public Solution(Problem pb) {
		super();
		this.pb = pb;			
	}
		
	public int GetScore()
	{
		ScoreInfo scoringInfo = this.GetScoreModel();
		return scoringInfo.score;
	}
	
	public ScoreInfo GetScoreModel()
	{
		ScoreInfo scoringInfo = new ScoreInfo(-314159);
		return scoringInfo;
	}
	
	
	public void SaveSolutionAsRaw(String fileName)
	{			
		Common.FU.SerializeObjectToFile(this, Common.ACFileFolderPath+fileName);
	}
	
	public void SaveSolutionAsRawToFullPath(String fullFilePath)
	{
		Common.FU.SerializeObjectToFile(this, fullFilePath);
	}
	
}

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
	
	ArrayList<Slice> slices;
	
		
	public int getU() {
		if (slices != null)
		return  slices.size();
		else
			return -42;
	}

	
	Solution( Slice s)
	{
		slices = new ArrayList<Slice>();
		slices.add(s);
	}
	
	
	
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
		
	
	
	public ScoreInfo GetScoreModel()
	{
		int scoreCalc = 0;
		for(final Slice sl: slices) 
		{
			scoreCalc = (scoreCalc + sl.getSliceArea());
		}				
		
		ScoreInfo scoringInfo = new ScoreInfo(scoreCalc);
		return scoringInfo;
	}
	
	public int GetScore()
	{
		ScoreInfo scoringInfo = this.GetScoreModel();
		return scoringInfo.score;
	}
	
	public int PrintScore()
	{ 	
		int scoreInt = GetScore();
		
		System.out.println("/////////////////////////");
		System.out.println("EvalScore : " + scoreInt);
		System.out.println("/////////////////////////");
		
		return scoreInt;
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

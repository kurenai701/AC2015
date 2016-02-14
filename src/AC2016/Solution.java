package AC2016;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
 * This class stores a solution. It is serializable for storage to file.
 *  OutputModel : classe Output
 *  @author : Clemence MEGE
 */
  
public class Solution implements Serializable {
	
	
	private static final long serialVersionUID = 42L;
	
	// for concours : list of ballons and movements

	int curScore; // current score
	
	
	
	
	
	// problem
	transient Problem pb;
				
	public Solution(Problem pb) {
		super();
		this.pb = pb;	
		this.curScore = 0;
	}
		
	
	
	/**
	 * Recomputes score from model
	 * @return score of current solution
	 */
	public ScoreInfo GetScoreModel()
	{
		
		ScoreInfo scoringInfo = new ScoreInfo(0);
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

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
	
	// for concours : list of ballons and movements
	Ballon ballons[];
	int curScore; // current score
	
	
	
	
	
	// problem
	transient Problem pb;
		
//	public Solution(int Nballons){
//		this.ballons = new Ballon[Nballons];
//		this.curScore = 0;
//	}
		
	public Solution(Problem pb) {
		super();
		this.pb = pb;	
		this.ballons = new Ballon[pb.B];
		for(int ii=0;ii<pb.B;ii++)
		{
			ballons[ii] = new Ballon(ii,pb.StartPos);
		}
		this.curScore = 0;
	}
		
	
	
	public ScoreInfo GetScoreModel()
	{
		
		//Time step
		int score = 0;

		for(int tt=0; tt<=pb.T;tt++)
		{
			int covered[] = new int[pb.L];// init to 0
			boolean stillAlive = false;
			
			for( int nb=0;nb<pb.B;nb++)
			{
				 Ballon curB = ballons[nb];
				
				if( tt < curB.posList.size())
				{
					
					Pos curPos = curB.posList.get(tt);
					if(curPos.z>0)
					{
						
					
					for(int Ncible : curPos.coverList)
					{
						// For all cible covered by pos
						
						if(covered[Ncible]==0)
						{
							score++;//NEw cible reached
						}
						covered[Ncible]++;
					}
					
					}
					stillAlive=true;
				}//if				
				if(!stillAlive)
				{
				break;//Fast evaluate end
				}
			}//for Ballon
		}//For tt
		

		ScoreInfo scoringInfo = new ScoreInfo(score);
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

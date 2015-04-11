package AC2015;

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
		
	
	
	/**
	 * Recomputes score from model
	 * @return score of current solution
	 */
	public ScoreInfo GetScoreModel()
	{
		
		//Time step
		int score = 0;

		for(int tt=0; tt<=pb.T;tt++)
		{
			int covered[] = new int[pb.L];// init to 0
		//	boolean stillAlive = false;
			
			for( int nb=0;nb<pb.B;nb++)
			{
				 Ballon curB = ballons[nb];
				
				if( tt < curB.posList.size())
				{
					if(Common.DEBUG ==1)
					{
						System.out.print("at T:"+tt+"Ballon #"+curB.Num+"scored : ");
					}
				
					
					Pos curPos = curB.posList.get(tt);
					curPos = pb.AllPosMat[curPos.x][curPos.y][curPos.z];
					
					//Check moves
					if(tt+1<=pb.T)
					{
						Pos nextPos = curB.posList.get(tt+1);
						nextPos = pb.AllPosMat[nextPos.x][nextPos.y][nextPos.z];
						
						boolean validated = false;
						if(curPos.moves != null)
						{
							for(Move m : curPos.moves)
							{
								if(m.aChange == curB.aChanges.get(tt))
								{
									if(m.nextPos.numOpt != nextPos.numOpt)
									{
										
										Sys.pln("BAD NEXT pos : expected " + nextPos + " Found : "+m.nextPos);
										
										Sys.pln("Correcting");
										curB.posList.remove(tt+1);
										curB.posList.add(tt+1,m.nextPos);
										 
									}
									validated = true;
								}
								if(m.nextPos.numOpt == nextPos.numOpt)
								{
									curB.aChanges.remove(tt);
									curB.aChanges.add(tt,m.aChange);
				
								}
								
							}
						}
						if(!validated)
						{
							if(curPos.numOpt!=0)
							{
								Sys.pln("Move not validated! Ballon#"+curB.Num+" Pos:"+curPos);
							}
						}
						
					}
					
					
					
					if(curPos.z>0)
					{
						
					
					for(int Ncible : curPos.coverList)
					{
						// For all cible covered by pos
						
						if(covered[Ncible]==0)
						{
							if(Common.DEBUG ==1)
							{
								System.out.print(" " +Ncible);
							}
									
							score++;//NEw cible reached
						}
						covered[Ncible]++;
					}
					
					}
					if(Common.DEBUG ==1)
					{
						System.out.println("");
					}
	//				stillAlive=true;
				}//if	
				
//				if(!stillAlive)
//				{
//				break;//Fast evaluate end
//				}
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

package AC2015;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
 *  ProblemModel : For Modeling the HashCode Problem
 *  
 */
public class Problem implements Serializable {

	
	int testint;
	String testString;	
	List<String> testList;
	
	public Problem(){}
//	
//	public Problem(int test, List<String> listtest, String strTest) {
//		super();
//		this.testint = test;
//		this.testList = listtest;
//		this.testString = strTest;
//	}
//	
	
	public int R;
	public int C;	
	public int A;
	
	public int L;
	public int V;
	public int B;
	public int T;
	
	public Pos StartPos;
	public Pos[] TargetPos;
	
	
	public Mvt[][][] WorldWindMvt;
	
	transient public List<Pos> AllPos;
	transient public Pos AllPosMat[][][];// To acces all pos as matrix
	
	
	
	public Problem(int R, int C, int A, int L, int V, int B, int T) {
		super();
		
	this.R = R;
	this.C = C;
	this.A = A;

	this.L = L;
	this.V = V;
	this.B = B;
	this.T = T;
	

	TargetPos = new Pos[L];
	WorldWindMvt = new Mvt[R][C][A+1];	
	
	AllPos = new ArrayList<Pos>();
	
	 
	
	}
	
	
	
	
	
	
}

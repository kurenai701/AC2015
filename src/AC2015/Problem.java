package AC2015;

import java.io.Serializable;
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
	
	int R;
	int C;	
	int A;
	
	int L;
	int V;
	int B;
	int T;
	
	Pos StartPos;
	Pos[] TargetPos;
	
	Mvt[][][] WorldWindMvt;
	
	
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
	WorldWindMvt = new Mvt[R][C][A];	 
	
	}
	
	
	
	
	
	
	
}

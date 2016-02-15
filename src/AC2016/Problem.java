package AC2016;

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
	public int D;
	public int T;
	public int DL;
	
	public int P;
	public int W;
	
	public int O;
	
		
	public WorldSimulation WorldSim;
	
	
	
	
	
}

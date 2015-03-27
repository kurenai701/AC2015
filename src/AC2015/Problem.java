package AC2015;

import java.util.ArrayList;
import java.util.List;


/*
 *  ProblemModel : For Modeling the HashCode Problem
 *  
 */
public class Problem {


	int testint;
	String testString;
	List<String> testList;
	
	
	
	
	int R; // number of rows
	int C; // number of columns
	int H; // minimum number of ham cells in an ultimate slice
	int S; // maximum total number of cells of an ultimate slide
	
	
	char[][] Pizza; 
	int[][] sumAdd;
    		

	public Problem(){}
	
	/*
	public Problem(int test, List<String> listtest, String strTest) {
		super();
		this.testint = test;
		this.testList = listtest;
		this.testString = strTest;
	}
	*/
	
	public Problem(int R, int C, int H, int S)
	{
		this.R = R;
		this.C = C;
		this.H = H;
		this.S = S;
		
		this.Pizza = new char[R][C];
	}
	
	
	
}

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
	
	public int R; // rows
	public int C; // columns
	public int D; // drones
	public int T; // turn
	public int DL; // drone load
	
	public int P; // number of product types
	public int W; // number of warehouses
	
	public int O; // number of orders
	
		
	public WorldSimulation WorldSim;
	
	
	
	
	
}

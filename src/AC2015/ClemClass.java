package AC2015;

import java.io.Serializable;
import java.util.List;

public class ClemClass implements Serializable{

	private static final long serialVersionUID = 14L;
	
	int intClem;	
	int intClem2;
	String stringClem;
	
	public ClemClass()
	{
		;
	}	

	public ClemClass(int intClem, int intClem2, String stringClem)
	{
		this.intClem = intClem;
		this.intClem2 = intClem2;
		this.stringClem = stringClem;
	}
	
}

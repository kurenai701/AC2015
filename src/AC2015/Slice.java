package AC2015;

public class Slice {

	int rowS;
	int rowE;
	int colS;
	int colE;
	int area;
	public Slice(int rowS, int rowE, int colS, int colE,int area) {
		super();
		this.rowS = rowS;
		this.rowE = rowE;
		this.colS = colS;
		this.colE = colE;
		this.area = area;
	}
	

	
	public int getSliceArea() {
		return ((rowE - rowS)+1) * ((colE - colS)+1);
	}
	
}

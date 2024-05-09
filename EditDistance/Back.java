package EditDistance;

public class Back {
    //class for pointers used in pointer matrix
	private int i;
	private int j;
	public Back(int i, int j){
		this.i=i;
		this.j=j;
	}
	public int getI(){
		return i;
	}
	public int getJ(){
		return j;
	}
	public void setI(int new_i){
		i=new_i;
	}
	public void setJ(int new_j){
		j=new_j;
	}
}

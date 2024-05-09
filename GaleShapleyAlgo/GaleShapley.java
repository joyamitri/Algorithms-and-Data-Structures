package GaleShapleyAlgo;

public class GaleShapley {
    private int[][] mpref;
	private int[][] fpref;
	private int[] mmatching;
	private int[] wmatching;
	private int[] mindex;
	private Queue q;
	
	public GaleShapley(int[][] mp, int[][] fp, int[] mm, int[] wm, int[] mi,Queue qu){
		mpref=mp;
		fpref=fp;
		mmatching=mm;
		wmatching=wm;
		mindex=mi;
		q=qu;
	}
	public void GS(){
		//Overall, these two while loops will take a maximum of N^2 since together they will visit all of the men preference 2d array
		//which is N*N.
		while(!q.isEmpty()){
			int j=q.dequeue();
			while(mmatching[j]==-99){
				int i=mindex[j];
				int target=mpref[j][i];
				if(wmatching[target]==-99){
					wmatching[target]=j;
					mmatching[j]=target;}
				else{
					int current=wmatching[target];
					int new_m=j;
					//Instead of going to the women preference and looping over the N elements elements, using the structure used,
					//we can find the order of preference in O(1)
					if(fpref[target][current]>fpref[target][new_m]){
						wmatching[target]=new_m;
						mmatching[new_m]=target;
						mmatching[current]=-99;
						q.enqueue(current);
					}
				}
				mindex[j]=++i;
			}
		}
		//If you are using letters instead of numbers for the men and women, then the way to decrease the search for the preference
		//would be to use hashing which you will see later on during the course.
	}
	public int[][] getMpref() {
		return mpref;
	}
	public void setMpref(int[][] mpref) {
		this.mpref = mpref;
	}
	public int[][] getFpref() {
		return fpref;
	}
	public void setFpref(int[][] fpref) {
		this.fpref = fpref;
	}
	public int[] getMmatching() {
		return mmatching;
	}
	public void setMmatching(int[] mmatching) {
		this.mmatching = mmatching;
	}
	public int[] getWmatching() {
		return wmatching;
	}
	public void setWmatching(int[] wmatching) {
		this.wmatching = wmatching;
	}
	public void print(){
		for(int i=0;i<mmatching.length;i++){
			System.out.println(i+" "+mmatching[i]);
		}
	}
	public void wprint(){
		for(int i=0;i<wmatching.length;i++){
			System.out.println(i+" "+wmatching[i]);
		}
	}
	public static void main(String[] args){
		/*
		mpref is the standard men preference table that we saw in the lab with each cell referring to a woman since we are using 
		numbers, as well as indices which will be used in wpref. This is done to achieve the O(N^2).
		   p1 p2 p3 p4
		m0 0  1  3  2
		m1 2  3  0  1
		m2 2  1  0  3
		m3 0  2  3  1
		*/
		int[][] mpref={{0,1,3,2},{2,3,0,1},{2,1,0,3},{0,2,3,1}};
		/*Instead of having the preferences by order as we saw in the lab, we will have the rows as the women, which is the
		standard, but the column will be the men going from 0 to 3 in this case since we have 4 men. Since we are using numbers
		to represent them, then these will be the indices. What is stored in each cell of the wpref 2d array is the importance of 
		each man for each woman with 0 being the highest.
		   m0 m1 m2 m3
		w0 0  2  3  1
		w1 2  1  0  3
		w2 3  2  1  0
		w3 1  2  3  0
		In this case 0 prefers m0 the most, followed by m3, then m1 and finally m2.
		*/
		int[][] wpref={{0,2,3,1},{2,1,0,3},{3,2,1,0},{1,2,3,0}};
		/*
		 mm and wm have as elements the matched spouse, -99 if no match yet.
		 */
		int[] mm={-99,-99,-99,-99};
		int[] wm={-99,-99,-99,-99};
		//mi is the current preference for the man that is being tested.
		int[] mi={0,0,0,0};
		Queue q =new Queue();
		//Queue needed to keep track of which single man we are.
		for(int i=0;i<4;i++){
			q.enqueue(i);
		}
		GaleShapley gs=new GaleShapley(mpref,wpref,mm,wm,mi,q);
		gs.GS();
		gs.print();
	}
}

/*
a. If we are always starting from the same group then for the same instance we will get the same stable marriage.
b. Yes, we will always get a stable marriage even if different.
c. The optimal solution is O(N^2).
*/

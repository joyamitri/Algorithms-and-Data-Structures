package EditDistance;

public class EditDist {
    /* Explanation:
	 * 
	 * Subproblem: X(i,j) is the minimum edit distance between word1[ 0 => i]  and word2 [ 0 => j]     i.e. prefixes of word1 and 2
	 * 
	 * Operations: pick between replacing, deleting , inserting
	 * 
	 * Relation: DP[i][j] = DP[i-1][j-1]  if word1[i] == word2[j]
	 * 			
	 * 			 else : DP[i][j] = 1 + min(inserting, deleting, replacing)
	 * 					Inserting => DP[i][j-1]
	 * 					Deleting => DP[i-1][j]
	 * 					Replacing => DP[i-1][j-1]
	 * 					=>DP[i][j] = 1 + min(DP[i][j-1], DP[i-1][j], DP[i-1][j-1])
	 * 
	 * Topological order => Top to bottom, left to right
	 * 
	 * Base case => DP[0][j] = j with j going from 0 to word2.length
	 * 			 => DP[i][0] = i with i going from 0 to word1.length
	 * 			 This is done because we are adding one extra row and one extra column to symbolize an empty character which is useful if there is nothing
	 * 			 in common between the two words, meaning that all of it will need to be changed.
	 * 
	 * Solution will be found at the bottom right of the matrix, meaning at DP[word1.length][word2.length]
	 * 
	 * Time complexity: O(n^2)
	 */
	
	
	public static int min(int a, int b, int c)
    {
        if (a <= b && a <= c)
            return a;
        if (b <= a && b <= c)
            return b;
        else
            return c;
    }
	public static String min_pos(int a, int b, int c)
    {
        if (a <= b && a <= c)
            return "L";
        if (b <= a && b <= c)
            return "U";
        else
            return "D";
    }
	public static void editDist(String s1, String s2){
		s1=s1.toLowerCase();
		s2=s2.toLowerCase();
		int dp[][]= new int[s1.length() + 1][s2.length() + 1];
		Back[][] backtrack = new Back[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
	            for (int j = 0; j <= s2.length(); j++) {
	            	if (i == 0){
	                    dp[i][j] = j;
	                    backtrack[i][j]=new Back(i,j-1);
	            	}
	            	else if (j == 0){
	                    dp[i][j] = i;
	                    backtrack[i][j]=new Back(i-1,j);
	            	}
	            	else if (s1.charAt(i - 1) == s2.charAt(j - 1)){
	            		dp[i][j] = dp[i - 1][j - 1];
	                    backtrack[i][j]=new Back(i-1,j-1);
	            	} 
	            	else{
	                     dp[i][j] = 1 + min(dp[i][j - 1], // Insert
	                                      dp[i - 1][j], // Remove
	                                      dp[i - 1][j - 1]);//Replace
	                     String minimum=min_pos(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
	                     if(minimum.equals("L")){
	                    	 backtrack[i][j]=new Back(i,j-1);
	                     }else if(minimum.equals("U")){
	                    	 backtrack[i][j]=new Back(i-1,j);
	                     }else{
	                    	 backtrack[i][j]=new Back(i-1,j-1);
	                     }
	            	 }
	            }
		 }
		System.out.println("Dynamic Programming Table");
		for(int i=0;i<=s1.length();i++){
			for(int j=0;j<=s2.length();j++){
				System.out.print(dp[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("The edit distance is: "+dp[s1.length()][s2.length()]);
		int i=s1.length();
		int j=s2.length();
		String changes="";
		while(dp[i][j]!=0){
			changes=backstep(backtrack,i,j,s1,s2)+", "+changes;
			int a=i;
			int b=j;
			i=backtrack[a][b].getI();
			j=backtrack[a][b].getJ();
		}
		System.out.println("The needed changes per character are: " + changes);
	}
	public static String backstep(Back[][] back,int i,int j,String s1,String s2){
		int back_i=back[i][j].getI();
		int back_j=back[i][j].getJ();
		String backstep="";
		if(back_i==i-1&&back_j==j-1){
			if(s1.charAt(i-1)==s2.charAt(j-1)){
				backstep="No Change";
			}
			else
				backstep="Replace";
		}else if(back_i==i&&back_j==j-1){
			backstep="Insert";
		}else if(back_i==i-1&&back_j==j){
			backstep="Delete";
		}
		return backstep;
	}
	public static void main(String[] args){
		String s1="Ellen";
		String s2="Hello";
		editDist(s1,s2);
	}
}

package PriorityQueue;

import java.util.ArrayList;
import java.util.Vector;

public class Heap {
    private PriorityQueue pq;
	
	public Heap() {
		pq = new PriorityQueue();
	}
	
	public void createHeap() {
		// O(nlog(n)), n is number of jobs present in the file (since we are overriding on buildMinHeap)

		String absolutePath = "jobs.txt";

		Vector<String> lines = FileManager.getInstance().readFromFile(absolutePath);
		try {
			Job[] jobs = new Job[lines.size()];
			int i = 0;
			for(String line: lines) {
				String[] split_line= line.split(" ");
				String name = split_line[0];
				long length = Long.parseLong(split_line[1]);
				jobs[i++] = new Job(name, length);
			}
			
			pq.buildMinHeap(jobs);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Wrong format in file, please fix it  before proceeding . . .");
		}
	}
	public void delete() {
		String name = InputManager.getInstance().getString("Enter name: ");
		if(pq.remove(name)) {
			System.out.println("Succefully removed Job with owner: " + name);
		}else {
			System.out.println("Unable to find Job with owner: " + name);
		}
	}
	public void insert() {
		String name = InputManager.getInstance().getString("Enter name of the owner: ");
		long length = InputManager.getInstance().getValidIntInRange("Enter length of the job: ", 0, Long.MAX_VALUE);
		Job j = new Job(name, length);
		pq.insert(j);
		System.out.println("Succesfully added job: " + j.toString());
	}
	public void changePriority() {
		String name = InputManager.getInstance().getString("Enter name of the owner: ");
		if(pq.changePriority(name)) {
			System.out.println("Succefully changed priority of job whos owner is: " + name);
		}else {
			System.out.println("Unable to find Job with owner: " + name);
		}
		
	}
	public void maximum() {
		// O(n)
		Job max = pq.getMaximum();
		if(max == null) {
			System.out.println("No jobs available . . .\n");
			return;
		}
		System.out.println("Job with highest length is:\t"+max.toString()+"\n\n");
	}
	public void minimum() {
		//O(1)
		Job min = pq.getMinimum();
		if(min == null) {
			System.out.println("No jobs available . . .\n\n");
			return;
		}
		System.out.println("Job with lowest length is:\t"+min.toString()+"\n");		
	}
	public void showD() {
		Job[] jobs = pq.sortDecreasing();
		System.out.println("[ ");
		for(Job job: jobs) {
			System.out.println("\t"+job.toString() + ", ");
		}
		System.out.println("]\n");
	}
	public void showI() {
		Job[] jobs = pq.sortIncreasing();
		System.out.println("[ ");
		for(Job job: jobs) {
			System.out.println("\t"+job.toString() + ", ");
		}
		System.out.println("]\n");
	}
	public String repeat(int n,String s){
		String newS="";
		for(int i=0; i<n;i++){
			newS=newS+s;
		}
		return newS;
	}
	public void drawTree() {
		
		// O(n^2 * log(n))
		// since first loop is n
		// second loop with its inner loop take  O(height * n/2) = O(nlog(n))  (n/2 because last level will have n/2 nodes i.e. the worst)
		// third loop is n/2 
		
		// in total O(n^2Log(n) + n^2) = O(n^2Log(n))
		
		if(pq.getHeapSize() == 0) {
			System.out.println("No Jobs available . . . \n");
			return;
		}
		
		ArrayList<ArrayList<String>> tree = pq.getDrawableTree();

		int height = tree.size();
		int first_spaces = (int) (Math.pow(2, height + 1) - 1);
		int top_levels = 2 * (height);
		System.out.println(repeat(Math.max(0,first_spaces - (tree.get(0).get(0).length())/2)," ") + tree.get(0).get(0));
		first_spaces-=1;
		int old_inner_spaces = 0;
		for (int i = 1; i < tree.size(); i++) {
			int inner_spaces = 1;
			for (int j = 0; j < top_levels; j++) {
				for (int j2 = 0; j2 < tree.get(i - 1).size(); j2++) {
					if(tree.get(i - 1).get(j2).equals("")) continue;
					if(j2==0) {
						System.out.print(repeat(Math.max(first_spaces, 0)," "));
					}
					System.out.print("/" + repeat(Math.max(0,inner_spaces)," ") + "\\");
					int repeats=old_inner_spaces - 2;
					if(old_inner_spaces - 2 <= 0){
						repeats=1;
					}
					System.out.print(repeat(repeats," "));
				}
				old_inner_spaces-=2;
				System.out.println();
				first_spaces--;
				inner_spaces+=2;
			}
			top_levels = (top_levels + 2 - 1)/2;
			
			for (int j = 0; j < tree.get(i).size(); j++) {
				if(j == 0) {
					System.out.print(repeat(Math.max(0, first_spaces + 1 - (tree.get(i).get(j).length()))," "));
				}
				System.out.print(tree.get(i).get(j));
				int maximized=j;
				if(j + 1 < tree.get(i).size()){
					maximized=j+1;
				}
				System.out.print(repeat(Math.max(1, inner_spaces  - (tree.get(i).get(maximized).length()))," "));
			}
			old_inner_spaces = inner_spaces - 2;
			System.out.println();
		}
			   
	}
	public void menu() {
		System.out.println(
				"1.	Create Heap\n"+
				"2.	Delete\n"+
				"3.	Insert\n"+
				"4.	Change Priority\n"+
				"5.	Maximum\n"+
				"6.	Minimum\n"+
				"7.	ShowD\n"+
				"8.	ShowI\n"+
				"9.	Draw Tree\n"+
				"______________________");
		int choice = InputManager.getInstance().getValidIntInRange("Enter your choice: ", 1, 9);
		switch (choice) {
			case 1: 
				createHeap();
				break;
			case 2:
				delete();
				break;
			case 3:
				insert();
				break;
			case 4:
				changePriority();
				break;
			case 5:
				maximum();
				break;
			case 6:
				minimum();
				break;
			case 7:
				showD();
				break;
			case 8:
				showI();
				break;
			case 9:
				drawTree();
			default:
			
		}
		System.out.println("\n\n");
		menu();
	}
	public static void main(String[] args) {
		Heap q1 = new Heap();
		q1.menu();
	}
}

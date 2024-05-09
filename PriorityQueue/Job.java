package PriorityQueue;

public class Job implements Comparable<Job>{
	private String name;
	private long length;
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public Job(String n, long l) {
		name = n;
		length = l;
	}
	public String toString() {
		return name + " " + length;
	}
	public String specialString() {
		return name.charAt(0)+"."+length;
	}
	public String getName() {
		return name;
	}
	public int compareTo(Job o) {
		if(this.length < o.length) return -1;
		if(this.length == o.length) return 0;
		return 1;
	}
}

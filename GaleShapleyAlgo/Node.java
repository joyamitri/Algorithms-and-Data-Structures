package GaleShapleyAlgo;

public class Node {
    private int info;
	private Node next;
	public Node(int i){
		info=i;
		next=null;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int i) {
		info = i;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node n) {
		next = n;
	}
	public String toString(){
		return "Info: "+info;
	}
}

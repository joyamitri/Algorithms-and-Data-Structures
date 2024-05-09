package GaleShapleyAlgo;

public class Queue {
    private Node header;
	private Node tail;
	public Queue(){
		header=null;
		tail=null;
	}
	public Node getHeader() {
		return header;
	}
	public void setHeader(Node h) {
		header = h;
	}
	public void enqueue(int a){
		Node n = new Node(a);
		if(header==null){
			header=n;
			tail=n;
		}else{
			tail.setNext(n);
			tail=n;
		}
	}
	public int dequeue(){
		int i=-99;
		if(header==null){
			return i;
		}
		else{				
			i=header.getInfo();
			if(header.getNext()==null){
				header=null;
				tail=null;
			}else{
				Node n=header.getNext();
				header.setNext(null);
				header=n;
			}
		}
		return i;
	}
	public boolean isEmpty(){
		if(header==null){
			return true;
		}else{
			return false;
		}
	}
}

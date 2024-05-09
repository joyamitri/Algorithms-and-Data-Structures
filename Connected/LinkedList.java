package Connected;

public class LinkedList {
    private Node header;

    public LinkedList() {
        header=null;
    }
    public LinkedList(int h) {
        header=new Node(h);
    }

    public Node getHeader() {
        return header;
    }

    public void setHeader(Node header) {
        this.header = header;
    }
    public void add(int v) {
        if(header==null) {
            header=new Node(v);
        }
        else {
            Node current=header;
            while(current.getNext()!=null) {
                current=current.getNext();
            }
            current.setNext(new Node(v));
        }
        
    }
    public boolean delete(int v) {
        if(header==null) {
            System.out.println("The list is already empty");
            return false;
        }
        if(header.getValue()==v) {
            header=header.getNext();
            return true;
        }
        else {
            Node current=header;
            Node previous;
            while(current.getNext()!=null) {
                previous=current;
                current=current.getNext();
                if(current.getValue()==v) {//delete it
                    previous.setNext(current.getNext());
                    return true;
                }
            }
            //if it exits the loop, it means that v is not found
            System.out.println("item v does not exist");
            return false;
        }
    }
    public boolean find(int v) {
        if(header==null) {
            return false;
        }
        if(header.getValue()==v) {
            return true;
        }
        else {
            Node current=header;
            Node previous;
            while(current.getNext()!=null) {
                previous=current;
                current=current.getNext();
                if(current.getValue()==v) {
                    return true;
                }
            }
            //if it exits the loop, it means that v is not found
            return false;
        }
    }
    public void printList() {
        if(header==null) {
            System.out.println("Empty list");
            return;
        }
        Node current=header;
        System.out.print("// -> ");
        while(current.getNext()!=null) {
            System.out.print(current.getValue()+" -> ");
            current=current.getNext();
        }
        System.out.println(current.getValue()+".");
    }
    @Override
    public String toString() {
        String s="";
        if(header==null) {
            return s+"\n\t";
        }
        Node current=header;
        s=s+"// -> ";
        while(current.getNext()!=null) {
            s=s+current.getValue()+" -> ";
            current=current.getNext();
        }
        s=s+current.getValue()+".\n\t";
        return s;
    }
	public static void main(String[] args) {
		LinkedList ll=new LinkedList();
		ll.add(4);
		ll.add(8);
		ll.add(3);
		ll.printList();
		ll.delete(7);
		ll.add(14);
		ll.add(77);
		ll.printList();
		ll.delete(4);
		ll.printList();
	}
}

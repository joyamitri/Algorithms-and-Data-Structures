package Connected;

public class Stack {
    private int[] stack;
    private int count;

    public Stack() {
        stack=new int[10];
        count=0;
    }
    public int[] getStack() {
        return stack;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }
    public void check() {
        if(count+1>stack.length) {
            int [] temp=new int[stack.length+10];
            for(int i=0;i<stack.length;i++) {
                temp[i]=stack[i];
            }
            stack=temp;
        }
    }
    public void push(int i) {
        check();
        stack[count]=i;
        count++;
    }
    public int pop() {
        int a=stack[count-1];
        stack[count-1]=0;
        count--;
        return a;
    }
    public int peak() {
        return stack[count-1];
    }
    public boolean empty() {
        if(count==0)
            return true;
        return false;
    }
    public int getCount() {
        return count;
    }
    @Override
    public String toString() {
        String s="Stack=[ ";
        for(int i=0;i<count;i++)
            s=s+stack[i]+" ";
        return s+"]\n";
    }
}

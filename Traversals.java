/* 
    Brian Artz & Don Ulfig
    CSC321 - Homework 4
*/
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.lang.Math;

/* Given Node Class
*/ 

class Node<T>{
    T data;
    Node<T> left;
    Node<T> right;
    public Node(T data){
        this.data = data;
        left = null;
        right = null;
    }

}


 /* Non-Recursive Inorder Traversal */
public class BiTree<T> {
	int ctr=0;
	int n=0;
    Node root;
    ArrayList<T> list = new ArrayList<T>();
   int index=0;
    
     Node buildTree(int in[], int pre[], int start, int end) 
    {
    	 
        if (start > end) 
            return null;
  
        //Gets current node 
        Node tNode = new Node(pre[index++]);
  
        // If this node has no children then return 
        if (start == end)
            return tNode;
  
        // Else find the index of this node in Inorder traversal 
        int inOrder = search(in, start, end, (Integer) tNode.data);
  
        //Constructs the left and right subtrees
        tNode.left = buildTree(in, pre, start, inOrder - 1);
        tNode.right = buildTree(in, pre, inOrder + 1, end);
  
        return tNode;
    }
     public void setRoot(Node r)
     {
    	 root=r;
     }
  
     
    // Searches to find value in arr and returns the index at which it occurs
    int search(int arr[], int strt, int end, int value) 
    {
        int i;
        for (i = strt; i <= end; i++) 
        {
            if (arr[i]==value)
                return i;
        }
        return i;
    }

    void inorder(){
        if(root == null){
        	
        	return;
        }
        //create Stack w/ root
        Stack<Node> stack = new Stack<Node>();
        Node node = root;

        //start with left node
        while(node != null){
            stack.push(node);
            node = node.left;
            
        }

        //inorder traversal
        while(stack.size() > 0){

            //pop top node
            node = stack.pop();
            if((Integer)node.data!=-1)
            	System.out.print(node.data + " ");
            if (node.right != null){
                node = node.right;

                //visit leftmost node next
                while (node!= null){
                    stack.push(node);
                    node = node.left;
                }
            }
        }		
    	
    }

    void preorder(){
        preorder(root);
    }

    void preorder(Node node){
        if (node == null){
            return;
        }

        //create empty stack w/ root
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        //pop it, print it, push right then left so left is processed first
        while(stack.empty() == false) {
            //pop top item and print it
            Node myNode = stack.peek();
            if((Integer)myNode.data!=-1)
            {	System.out.print(myNode.data + " ");
            	stack.pop();
            }
            else stack.pop();
            //push right and left of popped node
            if(myNode.right != null) {
                stack.push(myNode.right);
            }

            if(myNode.left != null) {
                stack.push(myNode.left);
            }
        }
    }
    public void printPost()
    {
    	ArrayList<T> x=postorder(root);
    	for(int i=0;i<x.size();i++)
    	{
    		System.out.print(x.get(i)+" ");
    	}
    }
   public ArrayList<T> postorder(Node node){
        Stack<Node> stack = new Stack<Node>();

        if(node == null){
            return list;
        }
        stack.push(node);
        Node prev = null;
        while(!stack.isEmpty()){

            Node current = stack.peek();
            //traverse tree. if a leaf is found, process it and pop stack.
            if(prev == null || prev.left == current || prev.right == current){

                if(current.left != null)
                    stack.push(current.left);
                else if (current.right != null)
                    stack.push(current.right);
                else{
                	if((Integer)stack.peek().data!=-1)
                		{stack.pop();
                    	list.add((T) current.data);
                		}
                	else stack.pop();
                	}
            }

            else if (current.left == prev){
                if (current.right != null)
                    stack.push(current.right);
                else{
                	if((Integer)stack.peek().data!=-1)
                    	{stack.pop();
                    	list.add((T) current.data);}
                	else stack.pop();
                }
            }

            else if (current.right == prev){
            	if((Integer)stack.peek().data!=-1)
            	{	stack.pop();
            		list.add((T) current.data);
            	}
            	else stack.pop();
            }

            prev = current;
        }

        return list;
    }

    void levelOrder()
    {
        int h = height(root);
        int i;
        for(i=1; i<=h; i++)
            printLevel(root, i);
            System.out.println();
    }

    int height(Node root)
    {
    	int leftHeight=0;
    	int rightHeight=0;
        if(root == null)
            return 0;
        else{
            //get height of each subtree
        	if((Integer)root.data!=-1)
        	{
        	 leftHeight = height(root.left);
             rightHeight = height(root.right);
        	}
            if(leftHeight > rightHeight)
                return(leftHeight +1); //+1 for root
            else return(rightHeight+1);
        }
    }

    void printLevel(Node root, int level)
    {
        if (root == null)
            return;
        if(level == 1&&(Integer)root.data!=-1)
        {   
        	System.out.println("Level "+ n + ": " + root.data);
        	ctr++;
        }
        //recursively traverses through the tree to print the level
        else if (level > 1){
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
        }
        //"base case"
        if(ctr==1)
        	n++;
       //pattern to get the correct level
        if(ctr==(2^(n+1)-1))
        	n++;
    }
    public static  void main(String args[]){
        //user inputs pre-order + inorder sequences
        //program constructs Inorder, Preorder, Postorder
        //program traverses Btree levels by Line
        //exit
    System.out.println("Enter the inorder sequence one value at a time: ");
    Scanner sc= new Scanner(System.in);
    int [] in= new int[30];
    int [] pre= new int[30];
    int temp=0;
    int temp2=0;
    BiTree tree=new BiTree();
    in[0]=sc.nextInt();
    for(int i=1; i<in.length;i++)
    {
    	System.out.println("Enter the next number or type -1 to exit: ");
    	temp=sc.nextInt();
    	if(temp==-1)
    	{
    		
    		break;
    	}
    	in[i]=temp;
    }
    System.out.println("Enter the preorder sequence one value at a time: ");
    pre[0]=sc.nextInt();
    for(int i=1; i<pre.length;i++)
    {
    	System.out.println("Enter the next number or type -1 to exit: ");
    	temp=sc.nextInt();
    	if(temp==-1)
    		{
    			temp2=i;
    			break;
    		
    		}
    	pre[i]=temp;
    }
    for(int i=temp2;i<pre.length;i++)
    {
    	in[i]=-1;
    	pre[i]=-1;
    }
    sc.close();
    Node root= tree.buildTree(in,pre,0,in.length-1);
    tree.setRoot(root);
    System.out.println("Inorder: \n");
    tree.inorder();
    System.out.println("\nPreorder: \n");
    tree.preorder();
    System.out.println("\nPostorder: \n");
    tree.printPost();
    System.out.println("\nLevel Order: \n");
    tree.levelOrder();
    
}


  }

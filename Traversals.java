/* 
    Brian Artz & Don Ulfig
    CSC321 - Homework 4
*/

import java.util.Stack;
import java.util.ArrayList;

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
class BinaryTree {

    Node root;
    ArrayList<T> list = new ArrayList<T>();

    void inorder(){
        if(root == NULL){
            return;
        }
        //create Stack w/ root
        Stack<Node> stack = new Stack<Node>();
        Node node = root;

        //start with left node
        while(node != NULL){
            stack.push(node);
            node = node.left;
        }

        //inorder traversal
        while(stack.size() > 0){

            //pop top node
            node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != NULL){
                node = node.right;

                //visit leftmost node next
                while (node!= NULL){
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
        Stack<Node> stack = new Stack<Node>;
        stack.push(root);

        //pop it, print it, push right then left so left is processed first
        while(stack.empty() == FALSE) {
            //pop top item and print it
            Node myNode = stack.peek();
            System.out.print(myNode.data + " ");
            stack.pop();

            //push right and left of popped node
            if(myNode.right != NULL) {
                stack.push(myNode.right);
            }

            if(myNode.left != NULL) {
                stack.push(myNode.left);
            }
        }
    }

    ArrayList<T> postorder(Node node){
        Stack<Node> stack = new Stack<Node>;

        if(node == NULL){
            return list;
        }
        stack.push(node);
        Node prev = NULL;
        while(!stack.isEmpty()){

            Node current = stack.peek()
            //traverse tree. if a leaf is found, process it and pop stack.
            if(prev == NULL || prev.left == current || prev.right == current){

                if(current.left != NULL)
                    stack.push(current.left);
                else if (current.right != NULL)
                    stack.push(current.right);
                else{
                    stack.pop();
                    list.add(current.data);
                }
            }

            else if (current.left == prev){
                if (current.right != NULL)
                    stack.push(current.right);
                else{
                    stack.pop();
                    list.add(current.data);
                }
            }

            else if (current.right == prev){
                stack.pop();
                list.add(current.data);
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
        if(root == NULL)
            return 0;
        else{
            //get height of each subtree
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if(leftHeight > rightHeight)
                return(leftHeight +1); //+1 for root
            else return(rightHeight+1);
        }
    }

    void printLevel(Node root, int level)
    {
        if (root == NULL)
            return;
        if(level == 1)
            System.out.println("Level 1: " + root.data);
        else if (level > 1){
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
        }
    }

    public static void main(String args[]){
            //user inputs pre-order + inorder sequences
            //program constructs Inorder, Preorder, Postorder
            //program traverses Btree levels by Line
            //exit
            return;
    }
}
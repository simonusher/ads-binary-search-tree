package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Igor on 05.05.2017.
 */
public class BST {
    private Comparator comparator;
    public Node root;
    private ArrayList<String> helper = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public BST(){
        comparator = (o1, o2) -> {
            int p = (int)o1;
            int q = (int)o2;
            int x = 0;
            if (p > q) x = 1;
            else if (p < q) x = -1;
            return x;
        };
    }

    public BST(Comparator c) {
        comparator = c;
        root = null;
    }

    public void insert(Object o){
        insert(o, root);
    }

    private void insert(Object o, Node n){
        if(n == null) root = new Node(o);
        else if(comparator.compare(o, n.value) == 0) throw new DuplicateException();

        else if(comparator.compare(o, n.value) < 0) {
            if(n.left == null){
                n.left = new Node(o);
            }
            else {
                insert(o, n.left);
            }
        }
        else if(comparator.compare(o, n.value) > 0) {
            if(n.right == null){
                n.right = new Node(o);
            }
            else {
                insert(o, n.right);
            }
        }
    }

    public void printInOrder(){
        inOrderWalk(root);
    }

    public void inOrderWalk(Node n){
        if(n == null) return;
        else{
            inOrderWalk(n.left);
            System.out.print(n.value + " ");
            inOrderWalk(n.right);
        }
    }

    public void printPreOrder(){
        preOrderWalk(root);
    }

    private void preOrderWalk(Node n){
        if(n == null) return;
        else{
            System.out.print(n.value + " ");
            preOrderWalk(n.left);
            preOrderWalk(n.right);
        }
    }

    public void printPostOrder(){
        postOrderWalk(root);
    }

    private void postOrderWalk(Node n){
        if(n == null) return;
        else {
            postOrderWalk(n.left);
            postOrderWalk(n.right);
            System.out.print(n.value + " ");
        }
    }

    public Node find(Object o){
        Node n = search(o, root);
        if(n != null) System.out.println(n.value);
        else System.out.println("Not found.");
        return n;
    }

    public void max(){
        Node n = findMax(root);
        if(n == null) System.out.println("The tree is empty.");
        else System.out.println("Max: " + n.value);
    }

    private Node findMax(Node n){
        if(n.right == null) return n;
        else return findMax(n.right);
    }

    public void min(){
        Node n = findMin(root);
        if(n == null) System.out.println("The tree is empty.");
        else System.out.println("Min: " + n.value);
    }

    private Node findMin(Node n){
        if(n.left == null) return n;
        else return findMax(n.left);
    }

    private Node search(Object o, Node n){
        if(n == null || o.equals(n.value)) return n;
        else if(comparator.compare(o, n.value) < 0) return search(o, n.left);
        else return search(o, n.right);
    }

    public void printNumberOfNodes(){
        System.out.println("Number of nodes: " + numberOfNodes(root));
    }

    private int numberOfNodes(Node n){
        int x = 0;
        if(n == null) return x;
        else if(n.left != null && n.right != null){
            return x + 1 + numberOfNodes(n.left) + numberOfNodes(n.right);
        }
        else if(n.left == null) return  x + 1 + numberOfNodes(n.right);
        else return  x + 1 + numberOfNodes(n.left);
    }

    public void printLeafCount(){
        System.out.println("Leaf count: " + leafCount(root));
    }

    private int leafCount(Node n){
        int x = 0;
        if(n == null) return 0;
        else if(n.left == null && n.right == null) return 1;
        else{
            if(n.left != null && n.right != null) return x + leafCount(n.left) + leafCount(n.right);
            else if(n.left == null) return x + leafCount(n.right);
            else return x + leafCount(n.left);
        }
    }

    public void printInnerNodesNumber(){
        System.out.println("Inner nodes: " + (numberOfNodes(root) - leafCount(root)));
    }

    public void printOuterNodesNumber(){
        System.out.println("Outer nodes: " + outerNodesNumber(root));
    }

    private int outerNodesNumber(Node n){
        if(n.left == null && n.right == null) return 2;
        else if (n.right!= null && n.left != null) return outerNodesNumber(n.left) + outerNodesNumber(n.right);
        else if(n.left != null) return outerNodesNumber(n.left) + 1;
        else return outerNodesNumber(n.right) + 1;
    }

    public void printHeight(){
        System.out.println("Height: " + height(root));
    }

    public int height(Node n){
        if(n == null) return -1;
        else if(n.left != null && n.right != null){
            if(height(n.left) > height(n.right)) return height(n.left) + 1;
            else return height(n.right) + 1;
        }
        else if (n.left == null) return height(n.right) + 1;
        else return height(n.left) + 1;
    }

    private String arrayListToString(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        while(list.size() != 0){
            sb.append(list.get(0));
            list.remove(0);
        }
        return sb.toString();
    }

    public void printByLevels(){
        this.helper = new ArrayList<>();
        printByLevels(root, 0);
        System.out.println("Keys by levels: " + arrayListToString(helper));
    }

    private void printByLevels(Node n, int x){
        if(n.value != null){
            if(helper.size() == x) helper.add(x, n.value + " ");
            else helper.set(x, helper.get(x) + n.value + " ");
        }
        if(n.left != null) printByLevels(n.left, x + 1);
        if(n.right != null) printByLevels(n.right, x + 1);
    }

    private class Node{
        private Node left;
        private Node right;
        private Object value;

        Node(Object o){
            this.left = null;
            this.right = null;
            this.value = o;
        }
    }

    public void delete(Object x){
        root = delete(x,root);
    }

    private Node delete(Object x, Node t) {
        if(t == null) throw new ItemNotFoundException();
        else { int cmp = comparator.compare(x,t.value);
            if(cmp < 0) t.left = delete(x,t.left);
            else if(cmp > 0) t.right=delete(x,t.right);
            else if(t.left != null &&t.right != null)
                t.right = detachMin(t.right,t);
            else t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private Node detachMin(Node t, Node del) {
        if(t.left != null) t.left = detachMin(t.left,del);
        else{
            del.value = t.value; t = t.right;
        }
        return t;
    }

    public void printSuccessor(Object o){
        System.out.println(findSuccessor(o, root, null));
    }

    private Object findSuccessor(Object o, Node n, Object successor) {
        if (n == null) throw new ItemNotFoundException();
        int cmp = comparator.compare(o, n.value);
        if(cmp < 0 && n.left != null){
            if(comparator.compare(o, n.left.value) < 0){
                return findSuccessor(o, n.left, n.left.value);
            }
            else return findSuccessor(o, n.left, successor);
        }
        else if(cmp > 0 && n.right != null) return findSuccessor(o, n.right, successor);

        else{
            if(n.right != null) return findMin(n.right).value;
            else return successor;
        }
    }

    public void printPredecessor(Object o){
        System.out.println(findPredecessor(o, root, null));
    }

    private Object findPredecessor(Object o, Node n, Object predecessor){
        if (n == null) throw new ItemNotFoundException();
        int cmp = comparator.compare(o, n.value);
        if(cmp > 0 && n.right != null){
            if(comparator.compare(o, n.right.value) > 0){
                return findPredecessor(o, n.right, n.right.value);
            }
            else return findPredecessor(o, n.right, predecessor);
        }
        else if(cmp < 0 && n.left != null) return findPredecessor(o, n.left, predecessor);

        else{
            if(n.left != null) return findMax(n.left).value;
            else return predecessor;
        }
    }

    public void clear(){
        root = null;
    }

    public void printMenu(){
        System.out.println("1.Insert key");
        System.out.println("2.Print tree");
        System.out.println("3.Find key");
        System.out.println("4.Print minimum");
        System.out.println("5.Print maximum");
        System.out.println("6.Print height");
        System.out.println("7.Print inner nodes number");
        System.out.println("8.Print outer nodes number");
        System.out.println("9.Print leaf count");
        System.out.println("10.Print by levels");
        System.out.println("11.Print successor");
        System.out.println("12.Print predecessor");
        System.out.println("13.Delete key");
        System.out.println("14.Clear tree");
    }

    public void treeOnline(int x){
        int temp;
        switch (x){
            case 1:
                System.out.println("Enter the key to insert: ");
                temp = input.nextInt();
                insert(temp);
                break;
            case 2:
                System.out.println("1.inOrder");
                System.out.println("2.preOrder");
                System.out.print("3.postOrder: ");
                temp = input.nextInt();
                switch (temp){
                    case 1:
                        printInOrder();
                        break;
                    case 2:
                        printPreOrder();
                        break;
                    case 3:
                        printPostOrder();
                        break;
                    default:
                        System.out.println("Podales niewlasciwa liczbe.");
                        break;
                }
                System.out.println();
                break;
            case 3:
                System.out.println("Enter the key to find: ");
                temp = input.nextInt();
                find(temp);
                break;
            case 4:
                min();
                break;
            case 5:
                max();
                break;
            case 6:
                printHeight();
                break;
            case 7:
                printInnerNodesNumber();
                break;
            case 8:
                printOuterNodesNumber();
                break;
            case 9:
                printLeafCount();
                break;
            case 10:
                printByLevels();
                break;
            case 11:
                System.out.println("Enter the key to find successor of: ");
                temp = input.nextInt();
                printSuccessor(temp);
                break;
            case 12:
                System.out.println("Enter the key to find predecessor of: ");
                temp = input.nextInt();
                printPredecessor(temp);
                break;
            case 13:
                System.out.println("Enter the key to delete: ");
                temp = input.nextInt();
                delete(temp);
                break;
            case 14:
                clear();
                break;
            default:
                System.out.println("Podales niewlasciwa liczbe.");
        }
    }
}

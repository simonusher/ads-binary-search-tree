package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BST bst = new BST();
//        bst.insert(2);
//        bst.insert(5);
//        bst.insert(4);
//        bst.insert(11);
//        bst.insert(13);
//        bst.insert(1);
//        bst.insert(12);
//        bst.insert(20);
//        bst.insert(14);
//        bst.insert(10);
//        bst.insert(21);
//        bst.insert(1);

//        bst.printInOrder();
//        System.out.println();
//
//        bst.printPreOrder();
//        System.out.println();
//
//        bst.printPostOrder();
//        System.out.println();
//
//        bst.find(10);
//        bst.find(30);
//
//        bst.max();
//        bst.min();
//
//        bst.printHeight();
//        bst.printNumberOfNodes();
//        bst.printLeafCount();
//        bst.printInnerNodesNumber();
//        bst.printOuterNodesNumber();
//        bst.printByLevels();
//        bst.delete(2);
//        bst.printInOrder();
//        System.out.println();
//        bst.printHeight();
//        bst.printSuccessor(2);
//        bst.printPredecessor(11);
        int temp = 0;
        Scanner scanner = new Scanner(System.in);
        while(temp != -1){
            bst.printMenu();
            try{
                temp = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Wprowadz liczbe!");
                temp = 0;
                break;
            }
            bst.treeOnline(temp);
        }
    }
}

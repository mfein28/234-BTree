//Matt Fein
//Project 1
//Algorithms

import java.util.ArrayList;
import java.util.Arrays;

public class Node {

    public static final int K = 2;	// This is the degree of this B-Tree. K = 2 => a 2-3-4 tree.
    private boolean isRoot;
    private int keyCount, childCount;
    private int[] keyValues;
    private Node[] children;
    public boolean isLeaf;

    public Node(boolean isRoot, int keyCount, int[] keyValues, boolean isLeaf) {
        this.isRoot = isRoot;
        this.keyCount = keyCount;
        this.keyValues = new int[2 * K - 1];	// Don't forget to create the array before trying to add elements.
        this.children = new Node[2 * K];		// Don't forget to create the array before trying to add elements.
        for (int i = 0; i < keyCount; i++) {
            this.keyValues[i] = keyValues[i];
        }
        this.isLeaf = isLeaf;
        childCount = 0;
    }
    //Constructor for creating a node with single value

    public Node(boolean isRoot, int Value, boolean isLeaf) {
        this.isRoot = isRoot;
        this.keyCount = 1;
        this.keyValues = new int[2 * K - 1];	// Don't forget to create the array before trying to add elements.
        this.children = new Node[2 * K];		// Don't forget to create the array before trying to add elements.
        this.keyValues[0] = Value;
        this.isLeaf = isLeaf;
        childCount = 0;
    }

    public boolean getIsRoot() {
        return isRoot;
    }

//This function figures out where the new element belongs in the node
//Only needs to be called when correct node is located in tree class
    public void sortNode(int Value) {
        if (keyCount == 3) {
            System.out.print("Key count 3: Cannot Insert");
        }
        else if(keyCount == 0){
            keyValues[0] = Value;
            keyCount = 1;
        }
        else if (keyCount == 1) {
            if (Value >= keyValues[0]) {
                keyValues[1] = Value;

            } else if (Value < keyValues[0]) {
                int store = keyValues[0];
                keyValues[0] = Value;
                keyValues[1] = store;
            }
            keyCount = 2;
        }
        else if (keyCount == 2) {
            if (Value > keyValues[0] && Value < keyValues[1]) {
                int saveVal = keyValues[1];
                keyValues[1] = Value;
                keyValues[2] = saveVal;
                keyCount = 3;
            }
            else if (Value < keyValues[0] && Value < keyValues[1]) {
                int saveVal1 = keyValues[0];
                int saveVal2 = keyValues[1];
                keyValues[0] = Value;
                keyValues[1] = saveVal1;
                keyValues[2] = saveVal2;
                keyCount = 3;
            }
            else if (Value > keyValues[0] && Value > keyValues[1]) {
                keyValues[2] = Value;
                keyCount = 3;
            }
            else {
            }
        }
    }

    //Adds a value to the parent with corresponding left and right children, this will delete
    //the child corresponding to the value added. The method connects the left and right child.
    //Value needs to be added using sortNode() before this is called.
    public void addValueWithTwoChild(Node leftChild, Node rightChild, int value) {
        //i is the index where Value is found
        int i = 0;
        while (i < keyCount && value != keyValues[i]) {
            i++;
        }
        
        //i is the index to the element we are looking for
        Node[] childrenCopy = new Node[2 * K];
        for (int j = 0; j < childCount; j++) {
            childrenCopy[j] = children[j];
        }
        children[i] = leftChild;
        children[i + 1] = rightChild;
        if (childCount == 0)
        {
        	// This node had no children previously.
        	childCount = 2;
        }
        else {
        	childCount++;
        }
        // Copy children left of leftChild
        for (int x = 0; x < i; x++) {
            children[x] = childrenCopy[x];
        }
        
        for (int y = i + 1; y < keyCount; y++) {
            //+1 is there b/c it leaves space for two new children, but also deleting child
            children[y + 1] = childrenCopy[y];
        }
    }

    // addChild(child) adds 'child' reference to the children array.
    // Only required to hardwire an example tree.
    //Children must be added in order
    public void addChild(Node child) {

        //Needs to be ordered
        //Values need to be copied
        children[childCount] = child;
        childCount++;
    }

    public void setOneKey() {
        keyCount = 1;
    }
    
    public void setKeyCount(int count){
        keyCount = count;
    }

    public int getChildCount() {
        return childCount;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public int[] getKeyValues() {
        return keyValues;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public Node[] getChildren() {
        return children;
    }

    public boolean isFull() {
        if (keyCount == 3) {
            return true;
        } else {
            return false;
        }
    }
    
    public void incrementKeyCount(){
        keyCount++;
    }

    public String toString() {
        String toBeReturned = "";
        for (int i = 0; i < keyCount; i++) {
            toBeReturned += keyValues[i] + " ";
        }
        return toBeReturned;
    }

    // Used to clear the whole tree.
    // This is the root node that is being cleared.
    public void clearNode() {
        keyCount = 0;
        children = new Node[2*K];	
        childCount = 0; 
        keyValues = new int[2 * K - 1];
        isLeaf = true;
        isRoot = true;
    }
    
    // Print the node information:
    public void printNode() {
    }
 
}

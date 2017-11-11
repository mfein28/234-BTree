//Matt Fein
//Project 1
//Algorithms

import java.util.ArrayList;

public class Tree {

	public static final int K = 2; // This is the degree of this B-Tree. K = 2 => a 2-3-4 tree.
	Node root;
	int Value;
	boolean trueForSearch;

	public Tree() {

		// Construct the given example tree with 8 nodes.

		int[] rootArray = {8};
		root = new Node(true, 1, rootArray, false);

		int[] node2Array = {2, 4};
		Node node2 = new Node(false, 2, node2Array, false);

		int[] node3Array = {11};
		Node node3 = new Node(false, 1, node3Array, false);

		int[] node4Array = {1};
		Node node4 = new Node(false, 1, node4Array, true);

		int[] node5Array = {3};
		Node node5 = new Node(false, 1, node5Array, true);

		int[] node6Array = {5, 6, 7};
		Node node6 = new Node(false, 3, node6Array, true);

		int[] node7Array = {9, 10};
		Node node7 = new Node(false, 2, node7Array, true);

		int[] node8Array = {12};
		Node node8 = new Node(false, 1, node8Array, true);

		// Connect nodes in tree
		root.addChild(node2);
		root.addChild(node3);

		node2.addChild(node4);
		node2.addChild(node5);
		node2.addChild(node6);

		node3.addChild(node7);
		node3.addChild(node8);
	}

	public Tree(int Value){
		this.Value = Value;
		root = new Node(true, Value, false);

		//Change 12 to value you would like to check
	}

	//Function called from main to being addValue function
	public void defAddValue(int value) {
		treeAddValue(value, root, null);
		root.printNode();
	}

	//Traverse to bottom of tree on the way if encounter node with 3 keys split node. 
	//When leaf node is found, insert the value
	private void treeAddValue(int value, Node node, Node parent) {

	
		node.printNode();
		//if(node.getIsRoot() == true && node.getChildCount() == 0){
			//node.sortNode(value);
			//node.incrementKeyCount(); // This is not already done in sortNode().
			// The condition commented out below should never be true anyway.
			//if(node.getKeyCount() == 3){
			// node.setKeyCount(1);
			//}
			//return;
		//}
		int[] nodeVals = node.getKeyValues();
		int numKeys = node.getKeyCount();

		Node[] grandChildren = node.getChildren();

		//Split node if full
		if (numKeys == 3) {
			//Node needs to be split
			int parentInsert = nodeVals[1];
			//Create left child of parent

			//Put nodeVals[1] into parent
			//This may mean creating a new method in node class called addValue
			//Use addValue to add nodeVals[1] to parent

			int newLeftValue = nodeVals[0];
			int newRightValue = nodeVals[2];
			Node newRightNode;
			Node newLeftNode;
			if (node.getIsLeaf() == true) {
				newRightNode = new Node(false, newRightValue, true);
				newLeftNode = new Node(false, newLeftValue, true);
			} else {
				newRightNode = new Node(false, newRightValue, false);
				newLeftNode = new Node(false, newLeftValue, false);
				//Connect grand children 
				newLeftNode.addChild(grandChildren[0]);
				newLeftNode.addChild(grandChildren[1]);

				newRightNode.addChild(grandChildren[2]);
				newRightNode.addChild(grandChildren[3]);
			}
			// Now put middle value in parent:
			if (parent != null) {
				parent.sortNode(parentInsert);
				parent.addValueWithTwoChild(newLeftNode, newRightNode, parentInsert);
				//parent.addChild(newLeftNode);	// Connect left child
				//parent.addChild(newRightNode);	// Connect right child

			} else {
				//Splitting Root
				root.clearNode(); 		// Create a new root node
				root.isLeaf = false;	// Set to false because we are creating a new node out of the root 
										// and because the tree is growing higher.
				root.sortNode(parentInsert);
				root.addValueWithTwoChild(newLeftNode, newRightNode, parentInsert);
				//root.addChild(newLeftNode);		// Connect left child
				//root.addChild(newRightNode);	// Connect right child
			}
			//Prepare node for recursive case.
			if (value <= parentInsert) {
				node = newLeftNode;

			} else {
				node = newRightNode;

			}

		}
		//Base Case
		//Bottom of tree has found, therefore the leaf is not full b/c it would have been split above.
		if (node.getIsLeaf() == true) {
			node.sortNode(value);
		} //Recursive Case
		else {

			int i = 0;
			while (i < numKeys && value >= nodeVals[i]) {
				
				i++;
			}
			Node correctChildNode = grandChildren[i];
			treeAddValue(value, correctChildNode, node);
		}
	}

	public void defSearch(int searchValue) {
		searchTree(root, searchValue);
	}

	public void clearTree() {
		root.clearNode();
	}

	public Boolean searchTree(Node node, Integer searchValue) {
		int i = 0;
		int[] searchArray = node.getKeyValues();
		int numKeys = node.getKeyCount();
		String searchString = searchValue.toString();


		// Checks if search value is in this node, and returns true if it is.
		while (i < numKeys) {

			if (searchArray[i] == searchValue) {
				System.out.print( searchString + " was found!\n\n\n");
				return true;
			}
			i++;
		}// end loop

		// Wasn't found in this node.
		// Base case:
		// Check if this is a leaf node. If it is, return false.
		if (node.getIsLeaf() == true) {
			System.out.println( searchString + " was not found\n\n\n");
			return false;
		} else {
			Node[] children = node.getChildren();
			// Recursive case:
			// Look for the child which sits between the key below which we have
			// and the key above which we have. Then call 'searchTree()' on that child.
			if (numKeys == 1) {
				//Go right if search val bigger
				if (searchValue > searchArray[0]) {
					Node nodeB = children[1];
					return searchTree(nodeB, searchValue);
					//Go left is search val is smaller
				} else {
					Node nodeA = children[0];
					return searchTree(nodeA, searchValue);
				}
			}

			if (numKeys == 2) {
				//With 2 numKeys there are 3 children so searchVal can fall in 3 possible places
				if (searchValue < searchArray[0] && searchValue < searchArray[1]) {
					Node nodeChild = children[0];
					return searchTree(nodeChild, searchValue);
				} else if (searchValue > searchArray[0] && searchValue < searchArray[1]) {
					Node nodeChild = children[1];
					return searchTree(nodeChild, searchValue);
				} else if (searchValue > searchArray[0] && searchValue > searchArray[1]) {
					Node nodeChild = children[2];
					return searchTree(nodeChild, searchValue);
				}

			}
			if (numKeys == 3) {
				//With 3 numKeys there are 4 children so searchVal can fall in 4 possible places
				if (searchValue < searchArray[0] && searchValue < searchArray[1]) {
					Node nodeChild = children[0];
					return searchTree(nodeChild, searchValue);
				} else if (searchValue > searchArray[0] && searchValue < searchArray[1]) {
					Node nodeChild = children[1];
					return searchTree(nodeChild, searchValue);
				} else if (searchValue > searchArray[0] && searchValue > searchArray[1] && searchValue < searchArray[2]) {
					Node nodeChild = children[2];
					return searchTree(nodeChild, searchValue);
				} else if(searchValue > searchArray[0] && searchValue > searchArray[1] && searchValue > searchArray[2]) {
					Node nodeChild = children[3];
					return searchTree(nodeChild, searchValue);
				}
			}
		}
		return false;
	}


	public void printTree() {
		int startOfLineIndent = 36;
		int interNodeIndent = 72;
		boolean isLeafLevel = false;
		if(root.getChildCount() == 0){
			System.out.print(root);
			return;
		}
		ArrayList<Node> thisRow = new ArrayList<Node>();	// A queue for this row.
		ArrayList<Node> nextRow = new ArrayList<Node>();	// A queue for next row.
		thisRow.add(root);
		// While we haven't reached the bottom row,	
		while (isLeafLevel == false) {
			// Print this row.
			// Print spaces at start of line:
			for (int i = 0; i < startOfLineIndent; i++) {
				System.out.print(" ");
			}

			// While thisRow is not empty
			while (thisRow.isEmpty() == false) {
				// Pop an element
				Node thisNode = thisRow.remove(0);
				// Put the children in the nextRow queue
				int childCount = thisNode.getChildCount();
				Node[] children = thisNode.getChildren();
				for (int i = 0; i < childCount; i++) {
					nextRow.add(children[i]);
				}

				// Print this element's values
				//Make toString in Node class
				System.out.print(thisNode);
				// Print spaces in between nodes nodes
				for (int i = 0; i < interNodeIndent; i++) {
					System.out.print(" ");
				}

				// if this element is a leaf node
				if (thisNode.getIsLeaf() == true) {
					isLeafLevel = true;
				}
			}// End while
			ArrayList<Node> temp = thisRow;
			thisRow = nextRow;
			nextRow = temp;
			startOfLineIndent /= 3;
			interNodeIndent /= 3;
			System.out.print("\n\n");
		}

 
	}
}

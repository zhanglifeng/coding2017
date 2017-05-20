package com.coding.basic.tree;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public BinaryTreeNode<T> findMinNode(BinaryTreeNode<T> node) {
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	public T findMin(){
		return findMinNode(root).getData();
	}
	public T findMax(){
		BinaryTreeNode<T> maxNode = root;
		T max = maxNode.getData();
		while(maxNode.getRight() != null) {
			maxNode = maxNode.getRight();
		}
		return maxNode.getData();
	}
	private int getHeight(BinaryTreeNode<T> node) {
		int height = 0;
		if (node != null) {
			int lheight = getHeight(node.getLeft());
			int rheight = getHeight(node.getRight());
			height = 	lheight >= rheight ? lheight + 1 : rheight +1;
		}
		return height;
	}
	public int height() {
		return getHeight(root);
	}
	private int countSize(BinaryTreeNode<T> node) {
		int size = 0;
		if (node != null) {
			size = 1;
			int lsize = countSize(node.getLeft());
			int rsize = countSize(node.getRight());
			size = size +lsize + rsize;
		}
		return size;
	}
	public int size() {
		return countSize(root);
	}
	public void remove(T e){
		remove(e, root);
	}
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		int compare = e.compareTo(node.getData());
		if (compare > 0) {
			remove(e, node.getRight());
		} else if (compare < 0) {
			remove(e, node.getLeft());
		} else if (node.getLeft() != null && node.getRight() != null) {
			BinaryTreeNode<T> temp = findMinNode(node.getRight());
			node.setData(temp.getData());
			node.setRight(remove(temp.getData(), node.getRight()));
		} else {
			
			BinaryTreeNode<T> left = node.getLeft();
			BinaryTreeNode<T> right = node.getRight();
			if (left!=null) {
				node.setData(left.getData());
				node.setLeft(left.getLeft());
				System.out.println(left);
			} else if (right != null) {
				node.setData(right.getData());
				node.setLeft(right.getLeft());
				node.setRight(right.getRight());
			} else {
				node.setData(null);
			}
		}
		return node;
	}
	private boolean isLeaf(T e) {
		return false;
	}
	private BinaryTreeNode<T> findParent(T e) {
		return null;
	}
	private BinaryTreeNode<T> findCurrentNode(T e) {
		return null;
	}
}
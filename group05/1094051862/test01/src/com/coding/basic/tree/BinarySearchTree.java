package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.Queue;

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
			
			if (node.getLeft()!=null) {
				node.setData(node.getLeft().getData());
				node.setLeft(node.getLeft().getLeft());
			} else if (node.getRight() != null) {
				node.setData(node.getRight().getData());
				node.setLeft(node.getRight().getLeft());
				node.setRight(node.getRight().getRight());
			} else {
				node.setData(null);
			}
		}
		return node;
	}
	public List<T> levelVisit(){
		Queue resQue = new Queue();
		List<T> result = new ArrayList<T>();
		resQue.enQueue(root);
		while(!resQue.isEmpty()) {
			BinaryTreeNode<T> node = (BinaryTreeNode<T>) resQue.deQueue();
			result.add(node.getData());
			if(node.getLeft()!=null) {
				resQue.enQueue(node.getLeft());
			}
			if(node.getRight()!=null) {
				resQue.enQueue(node.getRight());
			}
		}
		return result;
	}
	public boolean isValid(){
		List<T> inOrderVisit = BinaryTreeUtil.inOrderVisit(root);
		for(int i = 0; i < inOrderVisit.size()-1;i++) {
			T pre = inOrderVisit.get(i);
			T next = inOrderVisit.get(i+1);
			if (pre.compareTo(next)>0) {
				return false;
			}
		}
		return true;
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		T result = getLowestCommonAncesor(n1, n2, root);
		return result;
        
	}
	private T getLowestCommonAncesor(T n1, T n2, BinaryTreeNode<T> node) {
		if (node==null) {
			return null;
		}
		if (node.getData()==n1 || node.getData()==n2) {
			return node.getData();
		}
		T left = getLowestCommonAncesor(n1, n2, node.getLeft());
		T right = getLowestCommonAncesor(n1, n2, node.getRight());
		
		if (left != null && right != null) {
			return node.getData();
		}
		return left == null ? right : left;
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		List<T> result = new ArrayList<T>();
		saveNodesBetween(n1, n2, result, root);
		return result;
	}
	private void saveNodesBetween(T n1, T n2, List<T> result,
			BinaryTreeNode<T> node) {
		if (node==null) {
			return;
		}
		if (!(n1.compareTo(node.getData())>0) && !(n2.compareTo(node.getData())<0)) {
			result.add(node.getData());
		}
		if (node.getLeft() != null) {
			saveNodesBetween(n1, n2, result, node.getLeft());
		}
		if (node.getRight() != null) {
			saveNodesBetween(n1, n2, result, node.getRight());
		}
	}
}
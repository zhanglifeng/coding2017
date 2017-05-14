package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		preOrderInside(result, root);
		return result;
	}

	private static <T> void preOrderInside(List<T> result, BinaryTreeNode<T> node) {

		if (null == node.getData()) {
			return;
		} 
		result.add(node.getData());
		if (node.getLeft() != null) {
			preOrderInside(result, node.getLeft());
		}
		if (node.getRight() != null) {
			preOrderInside(result, node.getRight());
		}
		return;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderInside(result, root);
		if (root.getRight() != null) {
			inOrderInsideRight(result, root.getRight());
		}
		return result;
	}

	private static <T> void inOrderInsideRight(List<T> result, BinaryTreeNode<T> right) {
		result.remove(result.size()-1);
		inOrderInside(result, right);
		if (right.getRight() != null) {
			inOrderInsideRight(result, right.getRight());
		}
	}

	private static <T> void inOrderInside(List<T> result, BinaryTreeNode<T> node) {
		if (null == node.getData()) {
			return;
		}
		if (null != node.getLeft()) {
			inOrderInside(result, node.getLeft());
			result.add(node.getData());
			if (null != node.getRight()) {
				result.add(node.getRight().getData());
			}
			return;
		}
		result.add(node.getData());
		if (null != node.getRight()) {
			result.add( node.getRight().getData());
		}
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderInside(result, root);
		return result;
	}
	private static <T> void postOrderInsideRight(List<T> result, BinaryTreeNode<T> right) {
		result.remove(result.size()-1);
		postOrderInside(result, right);
		if (right.getRight() != null) {
			postOrderInsideRight(result, right.getRight());
		}
	}
	private static <T> void postOrderInside(List<T> result, BinaryTreeNode<T> node) {
		if (null == node.getData()) {
			return;
		}
		if (null != node.getLeft()) {
			postOrderInside(result, node.getLeft());
			if (null != node.getRight()) {
				result.add( node.getRight().getData());
			}
			result.add(node.getData());
			return;
		}
		if (null != node.getRight()) {
			result.add(node.getRight().getData());
		}
		result.add(node.getData());

	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		
		if (root != null) {
			stack.push(root);
			while(!stack.isEmpty()) {
				root = stack.pop();
				result.add(root.getData());
				if (root.hasRight()) {
					stack.push(root.getRight());
				}
				if (root.hasLeft()) {
					stack.push(root.getLeft());
				}
			}
		}
		return result;
	}

	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> t = root;
        while (t != null || !stack.empty()) {    
            while (t != null) {    
            	stack.push(t);    
                t = t.getLeft();    
            }    
            if (!stack.isEmpty()) {    
                t = stack.pop();    
                result.add(t.getData());
                t = t.getRight();    
            }    
        }    
		
		return result;
	}
	
}
package es.jaime.entregas.ejercicio5.ejercicio2;

import es.jaime.datastructures.binarytree.BinaryTree;
import es.jaime.datastructures.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public final class TreeTraversal {
    public static<T extends Comparable<T>> Object[] traversePreOrder(BinaryTree<T> tree) {
        return traversePreOrderRecursive(tree.getRoot(), new ArrayList<>());
    }

    private static<T extends Comparable<T>> Object[] traversePreOrderRecursive(TreeNode<T> root, List<Object> itemsInPreOrder) {
        itemsInPreOrder.add(itemsInPreOrder.size(), root.getData());

        if(root.getLeft() != null){
            traversePreOrderRecursive(root.getLeft(), itemsInPreOrder);
        }

        if(root.getRight() != null){
            traversePreOrderRecursive(root.getRight(), itemsInPreOrder);
        }

        return itemsInPreOrder.toArray();
    }

    public static<T extends Comparable<T>> Object[] traversePostOrder(BinaryTree<T> tree) {
        return traversePostOrderRecursive(tree.getRoot(), new ArrayList<>());
    }

    private static<T extends Comparable<T>> Object[] traversePostOrderRecursive(TreeNode<T> root, List<Object> itemsInPostOrder) {
        if(root.getLeft() != null){
            traversePostOrderRecursive(root.getLeft(), itemsInPostOrder);
        }

        if(root.getRight() != null){
            traversePostOrderRecursive(root.getRight(), itemsInPostOrder);
        }

        itemsInPostOrder.add(itemsInPostOrder.size(), root.getData());

        return itemsInPostOrder.toArray();
    }

}

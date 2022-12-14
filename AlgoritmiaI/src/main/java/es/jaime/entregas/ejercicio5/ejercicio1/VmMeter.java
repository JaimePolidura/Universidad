package es.jaime.entregas.ejercicio5.ejercicio1;


import es.jaime.datastructures.binarytree.BinaryTree;
import es.jaime.datastructures.binarytree.TreeNode;
import es.jaime.entregas.ejercicio5.VmInfo;

public final class VmMeter {
    public static VmInfo getHighestCpuTimeVm(BinaryTree<VmInfo> tree){
        TreeNode<VmInfo> root = tree.getRoot();

        return getHighestTreeNode(root).getData();
    }

    private static TreeNode<VmInfo> getHighestTreeNode(TreeNode<VmInfo> root){
        return root.getRight() != null ?
                getHighestTreeNode(root.getRight()) :
                root;
    }
}

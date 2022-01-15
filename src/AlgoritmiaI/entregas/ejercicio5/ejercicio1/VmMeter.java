package AlgoritmiaI.entregas.ejercicio5.ejercicio1;


import AlgoritmiaI.datastructures.binarytree.BinaryTree;
import AlgoritmiaI.datastructures.binarytree.TreeNode;
import AlgoritmiaI.entregas.ejercicio5.VmInfo;

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

package AlgoritmiaI.entregas.ejercicio5.ejercicio2;

import AlgoritmiaI.datastructures.binarytree.BinaryTree;
import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public final class TreeTraversalTest {
    @Test
    public void testTraversePreOrder() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        assertArrayEquals(
                new Integer[]{15, 9, 6, 14, 13, 20, 17, 64, 26, 72},
                TreeTraversal.traversePreOrder(tree));
    }

    @Test
    public void testTraversePostOrder() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(15);
        tree.insert(9);
        tree.insert(6);
        tree.insert(14);
        tree.insert(13);
        tree.insert(20);
        tree.insert(17);
        tree.insert(64);
        tree.insert(26);
        tree.insert(72);
        assertArrayEquals(
                new Integer[]{6, 13, 14, 9, 17, 26, 72, 64, 20, 15},
                TreeTraversal.traversePostOrder(tree));
    }

}

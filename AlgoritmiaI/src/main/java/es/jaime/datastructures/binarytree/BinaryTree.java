package es.jaime.datastructures.binarytree;


import java.util.Arrays;

public class BinaryTree<T extends Comparable<T>> implements IDataStructure<T> {

    private TreeNode<T> root;

    @Override
    public int size() {
        if (root == null) return 0;

        return countNodes(root);
    }

    private int countNodes(TreeNode<T> node) {
        int count = 1;
        if (node.getLeft() != null) {
            count += countNodes(node.getLeft());
        }

        if (node.getRight() != null) {
            count += countNodes(node.getRight());
        }

        return count;

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Object[] listData() {
        if (root == null) return new Object[0];

        List<T> output = new List<>();

        fillListInOrder(root, output);

        return output.listData();
    }

    private void fillListInOrder(TreeNode<T> node, List<T> list) {

        // Fill with left subtree
        if (node.getLeft() != null)
            fillListInOrder(node.getLeft(), list);

        // Fill with this node
        list.insert(node.getData(), -1);

        // Fill with the right subtree
        if (node.getRight() != null) {
            fillListInOrder(node.getRight(), list);
        }
    }

    public TreeNode<T> search(T data) {
        if (isEmpty()) return null;

        return searchInTree(root, data);
    }

    private TreeNode<T> searchInTree(TreeNode<T> node, T data) {
        if (node == null) return null;

        final int compareResult = data.compareTo(node.getData());

        if (compareResult == 0) {
            return node;
        }

        final boolean dataIsGreater = compareResult > 0;

        if (dataIsGreater) {
            return searchInTree(node.getRight(), data);
        } else {
            return searchInTree(node.getLeft(), data);
        }
    }

    public void insert(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    private void insertNode(TreeNode<T> node, TreeNode<T> newNode) {
        final int compareResult = newNode.getData().compareTo(node.getData());

        if (compareResult == 0) return;

        final boolean newNodeIsGreater = compareResult > 0;

        if (newNodeIsGreater) {
            if (node.getRight() == null) {
                node.setRight(newNode);
            } else {
                insertNode(node.getRight(), newNode);
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
            } else {
                insertNode(node.getLeft(), newNode);
            }
        }
    }

    public void remove(T data) {
        TreeNode<T> nodeToRemove = search(data);
        if (nodeToRemove == null) return;

        TreeNode<T> parent = findParent(nodeToRemove);
        final boolean hasLeft = nodeToRemove.getLeft() != null;
        final boolean hasRight = nodeToRemove.getRight() != null;

        if (hasRight && hasLeft) {
            removeNodeWithTwoChildren(nodeToRemove, parent);
        } else {
            removeNodeWithOneOrZeroChildren(nodeToRemove, parent);
        }
    }

    private void removeNodeWithOneOrZeroChildren(TreeNode<T> nodeToRemove, TreeNode<T> parent) {

        TreeNode<T> nodeForReplace = null;

        if (nodeToRemove.getLeft() != null) {
            nodeForReplace = nodeToRemove.getLeft();
        } else if (nodeToRemove.getRight() != null) {
            nodeForReplace = nodeToRemove.getRight();
        } else {
            nodeForReplace = null;
        }

        if (parent != null) {
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(nodeForReplace);
            } else {
                parent.setRight(nodeForReplace);
            }
        } else {
            root = nodeForReplace;
        }

    }

    private void removeNodeWithTwoChildren(TreeNode<T> nodeToRemove, TreeNode<T> parent) {
        TreeNode<T> nodeForReplace = findMostLeftNode(nodeToRemove.getRight());

        removeNodeWithOneOrZeroChildren(nodeForReplace, findParent(nodeForReplace));

        nodeForReplace.setLeft(nodeToRemove.getLeft());
        nodeForReplace.setRight(nodeToRemove.getRight());

        if (parent != null) {
            if (parent.getRight() == nodeToRemove) {
                parent.setRight(nodeForReplace);
            } else {
                parent.setLeft(nodeForReplace);
            }
        } else {
            root = nodeForReplace;
        }

    }

    private TreeNode<T> findMostLeftNode(TreeNode<T> node) {
        if (node.getLeft() == null)
            return node;

        return findMostLeftNode(node.getLeft());
    }


    private TreeNode<T> findParent(TreeNode<T> node) {
        if (node == root) return null;

        return findParent(root, node);
    }

    private TreeNode<T> findParent(TreeNode<T> parent, TreeNode<T> node) {
        if (parent.getLeft() == node || parent.getRight() == node) {
            return parent;
        }

        final boolean nodeIsGreater = node.getData().compareTo(parent.getData()) > 0;
        if (nodeIsGreater) {
            return findParent(parent.getRight(), node);
        } else {
            return findParent(parent.getLeft(), node);
        }
    }


    public TreeNode<T> getRoot() {
        return root;
    }

    public Object[] listPath(T value) {
        Object[] output = new Object[size()];

        final int finalSize = fillWithPath(getRoot(), value, output, 0);

        return Arrays.copyOfRange(output, 0, finalSize);
    }

    private int fillWithPath(TreeNode<T> currentNode, T value, Object[] output, int firstIndex) {
        if (currentNode == null) return 0;

        output[firstIndex] = currentNode.getData();

        final int comparison = currentNode.getData().compareTo(value);

        if (comparison == 0) return 1;

        final boolean currentNodeIsGreater = currentNode.getData().compareTo(value) > 0;
        final int nextIndex = firstIndex + 1;

        if (currentNodeIsGreater) {
            final int addedToLeft = fillWithPath(currentNode.getLeft(), value, output, nextIndex);
            if (addedToLeft == 0) return 0;
            return addedToLeft + 1;
        } else {
            final int addedToRight = fillWithPath(currentNode.getRight(), value, output, nextIndex);
            if (addedToRight == 0) return 0;
            return addedToRight + 1;
        }

    }
}

package AlgoritmiaI.datastructures.binarytree;

public class NodeUtils {
    public static <T> int count(Node<T> first) {
        if(first == null) return 0;

        int counter = 1;

        Node<T> current = first;
        while(current.getNext() != null){
            counter++;
            current = current.getNext();
        }

        return counter;
    }

    public static <T> Node<T> getSecondToLast(Node<T> first) {
        if(first == null || first.getNext() == null){
            throw new IndexOutOfBoundsException();
        }

        Node<T> secondToLast = first;
        while (secondToLast.getNext().getNext() != null){
            secondToLast = secondToLast.getNext();
        }

        if(secondToLast == null){
            throw new IndexOutOfBoundsException();
        }

        return secondToLast;
    }

    public static <T> Node<T> getLast(Node<T> first) {
        if(first == null || first.getNext() == null){
            return first;
        }

        return getSecondToLast(first).getNext();
    }

    public static <T> Node<T> getNodeByIndex(Node<T> first, int index) {
        if(index < 0 || first == null)
            throw new IndexOutOfBoundsException();

        Node<T> current = first;

        for(int i = 0; i < index && current != null; ++i){
            current = current.getNext();
        }

        if(current == null){
            throw new IndexOutOfBoundsException();
        }

        return current;
    }

    public static <T> Object[] listData(Node<T> first) {
        if(first == null) return new Object[0];

        Object[] output = new Object[count(first)];

        int insertIndex = 0;
        Node<T> current = first;
        while (current != null){
            output[insertIndex] = current.getData();
            insertIndex++;
            current = current.getNext();
        }

        return output;
    }
}

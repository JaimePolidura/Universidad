package es.jaime.datastructures;


public interface MyDataStructure<E> {
    void clear();
    int size();
    E[] listData();
    boolean isEmpty();

    class Node<E> {
        public Node<E> next;
        public Node<E> back;
        public E element;

        public Node(E element, Node<E> next, Node<E> back) {
            this.element = element;
            this.next = next;
            this.back = back;
        }
    }
}

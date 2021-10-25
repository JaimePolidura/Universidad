package AlgoritmiaI.datastructures.stack;

import AlgoritmiaI.datastructures.MyDataStructure;

import java.util.EmptyStackException;

public final class MyStack<E> implements MyDataStructure<E> {
    private Node<E> top;
    private Node<E> bottom;
    private int size;

    public MyStack<E> push (E element) {
        if(isEmpty()) {
            pushWhenEmpty(element);
        }else if (size == 1){
            pushWhenOneElement(element);
        }else{
            pushWhenNotEmpty(element);
        }

        return this;
    }

    private void pushWhenEmpty(E element) {
        Node<E> newNode = new Node<>(element, null, null);
        this.top = newNode;
        this.bottom = newNode;

        size++;
    }

    private void pushWhenOneElement(E element){
        Node<E> newNode = new Node<>(element, null, this.bottom);
        this.bottom.next = newNode;
        this.top = newNode;

        size++;
    }

    private void pushWhenNotEmpty(E element) {
        Node<E> newNode = new Node<>(element, null, this.top);
        this.top.next = newNode;
        this.top = newNode;

        size++;
    }

    public E pop () {
        checkIfNotEmptyOrThrow();

        if(size == 1){
            return popWhenOnlyOneElement();
        }else{
            return popWhenMoreOneElement();
        }
    }

    private E popWhenOnlyOneElement () {
        E elementFirst = this.top.element;
        this.top = null;
        this.bottom = null;

        size--;

        return elementFirst;
    }

    private E popWhenMoreOneElement () {
        Node<E> prevNodeToFirst = getNodeAt(size - 2);
        E elementFirstElement = top.element;

        this.top = prevNodeToFirst;
        prevNodeToFirst.next = null;

        size--;

        return elementFirstElement;
    }

    @Override
    public void clear () {
        this.top = null;
        this.bottom = null;

        size = 0;
    }

    public E top() {
        checkIfNotEmptyOrThrow();

        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public E[] listData() {
        Object[] array = new Object[size];

        Node<E> currentNode = this.bottom;
        int currentIndex = size - 1;

        while (currentNode != null) {
            array[currentIndex] = currentNode.element;
            currentNode = currentNode.next;

            currentIndex--;
        }

        return (E[]) array;
    }

    private Node<E> getNodeAt (int index) {
        Node<E> currentNode = bottom;
        int currentIndex = 0;

        while (currentNode != null) {
            if(currentIndex == index){
                return currentNode;
            }

            currentIndex++;
            currentNode = currentNode.next;
        }

        return null;
    }

    private void checkIfNotEmptyOrThrow () {
        if(isEmpty()){
            throw new EmptyStackException();
        }
    }

    private static class Node<E> {
        Node<E> next;
        Node<E> back;
        E element;

        public Node(E element, Node<E> next, Node<E> back) {
            this.element = element;
            this.next = next;
            this.back = back;
        }
    }
}

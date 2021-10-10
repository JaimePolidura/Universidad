package AlgoritmiaI.datastructures.linkedlist;


import AlgoritmiaI.datastructures.DataStructure;

import java.util.Iterator;

public final class LinkedList<E> implements DataStructure<E>, Iterable<E> {
    // first -> ... -> last
    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList<E> insert(E element, int index) {
        checkInsideBoundsOrThrow(index);

        if(isEmpty()) {
            insertWhenEmpty(element);
        }else if (index == 0) {
            insertFirst(element);
        }else if(index == size) {
            insertLast(element);
        }else if(size - 1 == index){
            insertSecondLast(element, index);
        }else{
            insertBetweenNodes(element, index);
        }

        return this;
    }

    public LinkedList<E> insert (E element) {
        if(isEmpty()){
            insertWhenEmpty(element);
        }else{
            insertLast(element);
        }

        return this;
    }

    private void insertWhenEmpty(E element) {
        Node<E> newNode = new Node<>(element, null, null);
        this.first = newNode;
        this.last = newNode;

        size++;
    }

    private void insertFirst(E element) {
        Node<E> newNode = new Node<>(element, this.first, null);

        this.first.back = newNode;
        this.first = newNode;
        size++;
    }

    private void insertSecondLast (E element, int index) {
        Node<E> newNode = new Node<>(element, last, last.back);

        Node<E> currentNode = last.back;
        Node<E> prevToCurrentNode = last.back.back;

        prevToCurrentNode.next = newNode;

        size++;
    }

    private void insertLast(E element) {
        Node<E> newNode = new Node<>(element, null, last);
        this.last.next = newNode;
        this.last = newNode;

        size++;
    }

    private void insertBetweenNodes(E element, int index) {
        Node<E> nextNodeInIndex = getNodeAt(index + 1);
        Node<E> prevOldNodeInIndex = getNodeAt(index - 1);

        Node<E> newNode = new Node<>(element, nextNodeInIndex, prevOldNodeInIndex);
        prevOldNodeInIndex.next = newNode;
        nextNodeInIndex.back = newNode;
        
        size++;
    }

    public LinkedList<E> remove(int index) {
        checkInsideBoundsOrThrow(index);

        if(isEmpty()){
            return this;
        }else if (index == 0 && size > 1) {
            removeFirst();
        }else if(index == 0 && size == 1){
            clear();
        }else if (index + 1 == size) {
            removeLast();
        }else{
            removeBetweenNodes(index);
        }

        return this;
    }

    private void removeFirst() {
        this.first = first.next;

        size--;
    }

    private void removeLast() {
        Node<E> nodePrevToLast = getNodeAt(size - 1);
        this.last = nodePrevToLast;
        this.last.next = null;

        size--;
    }

    private void removeBetweenNodes (int index) {
        checkInsideBoundsOrThrow(index);
        checkInsideBoundsOrThrow(index + 1);
        checkInsideBoundsOrThrow(index - 1);

        unlinkNode(index);
        size--;
    }

    @Override
    public void clear () {
        this.last = null;
        this.first = null;
        size = 0;
    }

    private void unlinkNode (int index) {
        Node<E> oldNodeInIndex = getNodeAt(index);
        Node<E> prevOldNodeInIndex = getNodeAt(index - 1);
        Node<E> nextNodeInIndex = getNodeAt(index + 1);

        prevOldNodeInIndex.next = nextNodeInIndex;
        oldNodeInIndex = null;
    }

    public E getFirst() {
        return first == null ? null : first.element;
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
        Node<E> currentNode = first;

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.element;

            currentNode = currentNode.next;
        }

        return (E[]) array;
    }

    public E get (int index) {
        checkIfNotEmptyOrThrow();
        checkInsideBoundsOrThrow(index);

        return getNodeAt(index).element;
    }

    private Node<E> getNodeAt (int index) {
        Node<E> currentNode = first;
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

    private void checkInsideBoundsOrThrow (int index) {
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkIfNotEmptyOrThrow () {
        if(isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Empty linked list");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<LinkedList> implements Iterator<E>{
        private int startIndex;
        private Node<E> currentNode;

        public MyIterator() {
            startIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return (startIndex == -1 && !isEmpty()) || (currentNode != null && currentNode.next != null);
        }

        @Override
        public E next() {
            if(this.startIndex == -1){
                this.currentNode = first;
                startIndex++;

                return currentNode.element;
            }

            this.currentNode = currentNode.next;

            return currentNode.element;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> back;

        public Node(E element, Node<E> next, Node<E> back) {
            this.element = element;
            this.next = next;
            this.back = back;
        }
    }
}

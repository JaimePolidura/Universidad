package es.jaime.datastructures.queue;

import es.jaime.datastructures.MyDataStructure;

import java.util.EmptyStackException;

public final class MyQueue<E> implements MyDataStructure<E> {
    //enqueue: bottom -> ... -> top
    private Node<E> top;
    private Node<E> bottom;
    private int size;

    public MyQueue<E> enqueue(E element) {
        if(isEmpty()){
            enqueueWhenEmpty(element);
        }else if(size == 1){
            enqueueWhenOnlyOneElement(element);
        }else{
            enqueueWhenVariousElements(element);
        }

        return this;
    }

    private void enqueueWhenEmpty(E element) {
        Node<E> newNode = new Node<>(element, null, null);
        this.top = newNode;
        this.bottom = newNode;

        size++;
    }

    private void enqueueWhenOnlyOneElement (E element){
        Node<E> newNode = new Node<>(element, this.bottom, null);
        this.bottom.back = newNode;
        this.top = bottom;
        this.bottom = newNode;

        size++;
    }

    private void enqueueWhenVariousElements (E element) {
        Node<E> newNode = new Node<>(element, this.bottom,null);
        this.bottom.back = newNode;
        this.bottom = newNode;

        size++;
    }

    public E dequeue () {
        checkIfNotEmptyOrThrow();

        if(size == 1) {
            return dequeueWhenOneElement();
        }else if (size == 2){
            return dequeueWhenTwoElements();
        }else{
            return dequeueWhenMoreThanOneElements();
        }
    }

    private E dequeueWhenOneElement() {
        E elementToDequeue = this.bottom.element;

        this.bottom = null;
        this.top = null;

        size--;

        return elementToDequeue;
    }

    private E dequeueWhenTwoElements () {
        E elementToDequeue = this.bottom.element;

        this.bottom = top;
        this.top.back = null;
        size--;

        return elementToDequeue;
    }

    private E dequeueWhenMoreThanOneElements () {
        E elementToDequeue = this.bottom.element;

        this.bottom = bottom.next;
        this.bottom.back = null;

        size--;

        return elementToDequeue;
    }

    @Override
    public void clear () {
        this.top = null;
        this.bottom = null;

        size = 0;
    }

    public E pick() {
        checkIfNotEmptyOrThrow();

        return bottom.element;
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

        Node<E> currentNode = this.top;
        int currentIndex = size - 1;

        while (currentNode != null) {
            array[currentIndex] = currentNode.element;
            currentNode = currentNode.back;

            currentIndex--;
        }

        return (E[]) array;
    }

    private void checkIfNotEmptyOrThrow () {
        if(isEmpty()){
            throw new EmptyStackException();
        }
    }
}

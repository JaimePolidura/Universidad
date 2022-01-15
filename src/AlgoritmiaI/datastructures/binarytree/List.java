package AlgoritmiaI.datastructures.binarytree;


public class List<T> implements IDataStructure<T>{
    private Node<T> first;

    public Node<T> getFirst() {
        return first;
    }

    public int size() {
        return NodeUtils.count(first);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Object[] listData() {
        return NodeUtils.listData(first);
    }

    public List insert(T data, int index) {
        Node<T> newNode = new Node(data);
        newNode.setData(data);

        if(index == 0){
            insertAtFirst(newNode);
        }else if(index == -1){
            insertAtEnd(newNode);
        }else if(index > 0){
            insertAtPosition(newNode, index);
        }else{
            throw new IndexOutOfBoundsException();
        }

        return this;
    }

    private void insertAtPosition(Node<T> newNode, int index) {
        if(first == null){
            throw new IndexOutOfBoundsException();
        }

        Node<T> prevNode = getNodeByIndex(index-1);
        if(prevNode == null){
            throw new IndexOutOfBoundsException();
        }

        Node<T> prevNextNode = prevNode.getNext();

        newNode.setNext(prevNextNode);

        prevNode.setNext(newNode);
    }

    private Node<T> getNodeByIndex(int index) {
        return NodeUtils.getNodeByIndex(first, index);
    }

    void insertAtEnd(Node node){
        if (first == null) {
            first = node;
        } else if (first.getNext() == null) {
            first.setNext(node);
        } else {
            Node last = getSecondToLast().getNext();
            last.setNext(node);
        }
    }

    Node<T> getSecondToLast(){
        return NodeUtils.getSecondToLast(first);
    }

    void insertAtFirst(Node<T> node){
        node.setNext(first);
        first = node;
    }

    public void remove(int index) {
        if(index == 0){
            removeFirst();
        }else if(index == -1){
            removeLast();
        }else if(index > 0) {
            removeAtPosition(index);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    private void removeAtPosition(int index) {
        Node<T> prevNode = getNodeByIndex(index-1);
        Node<T> targetNode = prevNode.getNext();

        if(targetNode == null){
            throw new IndexOutOfBoundsException();
        }

        prevNode.setNext(targetNode.getNext());
    }

    private void removeLast() {
        if(first.getNext() ==  null){
            first = null;
        }else{
            getSecondToLast().setNext(null);
        }
    }

    private void removeFirst() {
        first = first.getNext();
    }
}

package es.jaime.entregas.ejercicio3;

import es.jaime.datastructures.stack.MyStack;

public final class SyntaxError {
    private final MyStack<String> contextStack;
    private final int index;

    public SyntaxError(MyStack<String> contextStack, int index){
        this.contextStack = contextStack;
        this.index = index;
    }

    public MyStack<String> getContextStack() {
        return contextStack;
    }

    public int getIndex() {
        return index;
    }
}

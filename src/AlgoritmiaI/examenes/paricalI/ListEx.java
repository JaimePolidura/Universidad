package AlgoritmiaI.examenes.paricalI;

import edai.collections.List;
import edai.collections.Node;
import edai.collections.utils.NodeUtils;

public final class ListEx<T> extends List<T> {
    public void move(int sourceIndex, int targetIndex) {
        checkInsideBounds(sourceIndex, targetIndex);
        if(sourceIndex == targetIndex) return;

        Node<T> nodeFromSource = NodeUtils.getNodeByIndex(this.getFirst(), sourceIndex);
        Node<T> nodeFromTarget = NodeUtils.getNodeByIndex(this.getFirst(), targetIndex);

        this.remove(sourceIndex);
        if(targetIndex > sourceIndex){
            this.remove(targetIndex - 1);
        }else{
            this.remove(targetIndex);
        }

        this.insert(nodeFromTarget.getData(), sourceIndex);
        this.insert(nodeFromTarget.getData(), targetIndex);
    }

    private void checkInsideBounds(int sourceIndex, int targetIndex) {
        int size = NodeUtils.count(this.getFirst());

        if(sourceIndex < 0 || sourceIndex >= size || targetIndex < 0 || targetIndex >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}

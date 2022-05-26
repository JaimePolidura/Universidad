package AlgoritmiaII.entregas.ejercicio6.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class HashTable<T> {
    private final List<LinkedList<Entry<T>>> elements;
    private int size;
    private final int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.elements = new ArrayList<>(capacity);
        this.size = 0;

        this.fillBuckets();
    }

    private void fillBuckets(){
        for (int i = 0; i < capacity; i++)
            this.elements.add(new LinkedList<>());
    }

    public T get(String key) {
        int hash = key.hashCode();
        LinkedList<Entry<T>> bucket = this.findBucketForHash(hash);

        return bucket != null ? findDataInBucket(bucket, hash) : null;
    }

    private T findDataInBucket(LinkedList<Entry<T>> bucket, int hash) {
        return bucket.stream()
                .filter(entry -> entry.hashcode == hash)
                .findFirst()
                .map(Entry::getValue)
                .orElse(null);
    }

    public boolean set(String key, T value) {
        int hashCode = key.hashCode();
        LinkedList<Entry<T>> bucketForKey = this.findBucketForHash(hashCode);

        if (this.get(key) != null){
            return false;
        }

        bucketForKey.add(new Entry<>(key, value, key.hashCode()));
        size++;

        return true;
    }

    public boolean remove(T key) {
        var bucket = this.findBucketForHash(key.hashCode());
        var someItemRemoved = bucket.removeIf(itemInBucket -> itemInBucket.hashcode == key.hashCode());

        if (someItemRemoved)
            size--;

        return someItemRemoved;
    }

    public int size() {
        return this.size;
    }

    private LinkedList<Entry<T>> findBucketForHash(int hash){
        return this.elements.get(findIndexBucketForHash(hash));
    }

    private int findIndexBucketForHash(int hash){
        return hash % this.elements.size();
    }

    private LinkedList<Entry<T>> newBucket(String key, T value){
        return new LinkedList<>(){{
           add(new Entry<>(key, value, key.hashCode()));
        }};
    }

    private static class Entry<T>{
        public String key;
        public T value;
        public int hashcode;

        public Entry(String key, T value, int hashcode) {
            this.key = key;
            this.value = value;
            this.hashcode = hashcode;
        }

        public T getValue() {
            return value;
        }
    }
}

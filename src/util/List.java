package util;
import java.util.Iterator;

public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;

    public List() {
        this.objects = (E[]) new Object[4];
        this.size = 0;
    } //default constructor with an initial capacity of 4.

    private int find(E e) {
        for(int i = 0; i<size; i++){
            if(objects[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        E[] newObjects = (E[]) new Object[this.objects.length+4];
        for(int i = 0; i<this.size; i++){
            newObjects[i] = this.objects[i];
        }
        this.objects = newObjects;
    }

    public boolean contains(E e) {
        return find(e) != -1;
    }

    public void add(E e) {
        if(this.size == this.objects.length){
            this.grow();
        }
        this.objects[this.size++] = e;
        this.size++;
    }

    public void remove(E e) {
        int idx = this.find(e);
        if(idx == -1){
            return;
        }
        for(int i = idx; i < this.size-1; i++){
            this.objects[i] = this.objects[i+1];
        }
        this.objects[this.size-1] = null;
        this.size--;
    }

    public boolean isEmpty() {
        return this.size==0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public E get(int index) {
        if(index < 0 || index >= this.size){
            return null;
        }
        return this.objects[index];
    } //return the object at the index

    public void set(int index, E e) {
        if(index <0 || index >= this.objects.length){
            return;
        }
        this.objects[index] = e;
    } //put object e at the index

    public int indexOf(E e) {
        return this.find(e);
    } //return the index of the object or return -1

    private class ListIterator implements Iterator<E> {
        private int curr = 0;

        public boolean hasNext() {
            return this.curr < size;
        }//return false if it’s empty or end of list

        public E next() {
            if(!hasNext()){
                return null;
            }
            return objects[this.curr++];
        } //return the next object in the list
    }
}
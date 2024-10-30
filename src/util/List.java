package util;


import java.util.Iterator;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
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

    /**
     * @param e object to be found
     * @return true if object is found in list, false otherwise.
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * @param e object to be added
     * method for adding object of type E to List of type E
     */
    public void add(E e) {
        if(this.size == this.objects.length || this.isEmpty()){
            this.grow();
        }
        this.objects[this.size] = e;
        this.size++;
    }

    /**
     * @param e object to be removed
     * method for removing object of type E from List of type E
     */
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

    /**
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size==0;
    }

    /**
     * @return size of list (amount of objects of type E in list)
     */
    public int size() {
        return this.size;
    }

    /**
     * @return Iterator for easy iteration of List
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * @param index index to get object from
     * @return object at inputted index or null if not in List
     */
    public E get(int index) {
        if(index < 0 || index >= this.size){
            return null;
        }
        return this.objects[index];
    } //return the object at the index

    /**
     * @param index index needed to be accessed
     * @param e object that will be set in that index
     */
    public void set(int index, E e) {
        if(index <0 || index >= this.size){
            return;
        }
        this.objects[index] = e;
    } //put object e at the index

    /**
     * @param e object to find index of
     * @return index of object e, -1 if not in List
     */
    public int indexOf(E e) {
        return this.find(e);
    } //return the index of the object or return -1


    private class ListIterator implements Iterator<E> {
        private int curr = 0;

        /**
         * @return true if List has an object in the next index, false otherwise
         */
        @Override
        public boolean hasNext() {
            if(isEmpty()){
                return false;
            }
            return this.curr < size;
        }//return false if it’s empty or end of list

        /**
         * @return object at the next index, null if there is no object
         */
        @Override
        public E next() {
            if(isEmpty() || !hasNext()){
                return null;
            }
            return objects[this.curr++];
        } //return the next object in the list
    }
}
package util;
import java.util.Iterator;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Circular<E> extends List<E>{

    private int startIdx;
    /**
     * Constructor inherits all instance vars of List
     */
    public Circular(){
        super();
        this.startIdx = 0;
    }

    /**
     * @param e object to be added
     * method for adding object of type E to Circular linked list of type E
     */
    @Override
    public void add(E e){
        super.add(e);
        ensureCircular();
    }

    /**
     * @param e object to be removed
     * method for removing object of type E from Circular linked list of type E
     */
    @Override
    public void remove(E e){
        super.remove(e);
        ensureCircular();
    }

    /**
     * @param idx index to get object from
     * @return object at circular index of list
     */
    @Override
    public E get(int idx){
        if(isEmpty()){
            return null;
        }
        int cIdx = idx % size();
        return super.get(cIdx);
    }

    public void setStartIdx(E e){
        int idx = indexOf(e);
        if(idx!=-1){
            this.startIdx = idx;
        }
    }

    /**
     * @return Iterator for easy iteration over Circular class
     */
    @Override
    public Iterator<E> iterator(){
        return new CircularListIterator();
    }

    /**
     * does nothing, meant to ensure list is being traversed in a circular fashion, but wasn't needed
     */
    private void ensureCircular(){

    }

    private class CircularListIterator implements Iterator<E>{
        private int curr;
        private final int start;
        private boolean cycled;

        public CircularListIterator(){
            this.curr = startIdx%size();
            this.start = curr;
            this.cycled = false;
        }

        /**
         * @return true if there is an object in the next index
         */
        @Override
        public boolean hasNext(){
            return size()>0 && (!this.cycled || this.curr!=this.start);
        }

        /**
         * @return the object at the next circular index of the list, will return the head of the list if curr index is the last element
         */
        @Override
        public E next(){
            if(!hasNext()){
                return null;
            }
            E result = get(this.curr);
            this.curr = (this.curr+1)%size();
            if(this.curr==this.start){
                this.cycled = true;
            }
            return result;
        }
    }

}

package util;
import java.util.Iterator;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Circular<E> extends List<E>{

    /**
     * Constructor inherits all instance vars of List
     */
    public Circular(){
        super();
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
        private int curr = 0;

        /**
         * @return true if there is an object in the next index (always returns true in this case unless empty)
         */
        @Override
        public boolean hasNext(){
            return !isEmpty() || this.curr < size() || (this.curr%size()!=size()-1);
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
            return result;
        }
    }

}

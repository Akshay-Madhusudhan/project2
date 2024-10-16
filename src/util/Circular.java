package util;
import java.util.Iterator;

public class Circular<E> extends List<E>{

    public Circular(){
        super();
    }

    @Override
    public void add(E e){
        super.add(e);
        ensureCircular();
    }

    @Override
    public void remove(E e){
        super.remove(e);
        ensureCircular();
    }

    @Override
    public E get(int idx){
        if(isEmpty()){
            return null;
        }
        int cIdx = idx % size();
        return super.get(cIdx);
    }

    @Override
    public Iterator<E> iterator(){
        return new CircularListIterator();
    }

    private void ensureCircular(){

    }

    private class CircularListIterator implements Iterator<E>{
        private int curr = 0;

        @Override
        public boolean hasNext(){
            return !isEmpty() || this.curr < size();
        }

        @Override
        public E next(){
            if(!hasNext()){
                return null;
            }
            E result = get((curr + 1) % size());
            return result;
        }
    }

}

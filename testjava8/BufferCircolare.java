package testjava8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Una classe che implementa un buffer circolare ( a.k.a buffer a scorrimento)
 * attraverso un ArrayList, non è particolarmente utile ma serve per illustrare
 * i Generics e il loro rapporto con i nuovi Stream.
 *
 * La classe è creata tramite composizione di ArrayList, quindi non ne eredita
 * direttamente i metodi. Sebbene non includa un singolo metodo riguardante
 * gli Stream (filter, foreach etc etc) essi risulteranno disponibili inquanto
 * inclusi dalla stessa interfaccia Collection. In questo modo è possibile
 * importare in modo trasparente codice vecchio e addirittura binari vecchi (
 * essendo l'interfaccia facente parte della libreria di base).
 *
 * Per fare questo hanno però creato un pattern pericolosamente vicino alla
 * tanto ostacolata ereditarietà multipla e che se usato in modo improprio può
 * avere comportamenti strani.
 *
 * @author darshan
 * @param <T>
 */
public class BufferCircolare<T> implements Collection<T> {

    private final ArrayList<T> buffer;
    private final int max_size;
    private int offset;
    public int size;

    public BufferCircolare(int len) {
        this.max_size = len;
        buffer = new ArrayList<>(len);
        offset = 0;
        size = 0;
    }

    public int push(T obj) {
        if (size == max_size) { //elimino l'ultimo
            this.pop_tail();
            push(obj);
        } else {
            int i = (offset + size) % max_size;
            if (buffer.size() <= i) {
                buffer.add(obj);
            } else {
                buffer.set(i, obj);
            }
            size++;
            return size;
        }
        return size;
    }

    public synchronized T pop_tail() {
        T temp = this.read_tail();
        if (size > 0) {
            offset++;
            offset = offset % max_size;
            size--;
        }
        return temp;
    }

    public T read_tail() {
        if (size == 0) {
            return null;
        }
        return buffer.get(offset);
    }

    @Override
    public synchronized void clear() {
        buffer.clear();
        offset = 0;
        size = 0;
    }

    public synchronized String dump() {
        StringBuilder temp = new StringBuilder();
        if (size == 0) {
            return " ";
        }
        for (int n = offset; n < (offset + size); n++) {
            temp.append(buffer.get(n % size).toString()).append("\n");
        }
        return temp.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return buffer.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return buffer.iterator();
    }

    @Override
    public Object[] toArray() {
        return buffer.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return buffer.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return this.push(e) > 0;
    }

    @Override
    public boolean remove(Object o) {
        if (buffer.remove(o)) {
            size--;
            return true;
        };
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return buffer.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            c.forEach(i -> this.push(i));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = buffer.removeAll(c);
        size = buffer.size();
        return flag;
    }

    ;


/**
 * Retains only the elements in this list that are contained in the specified collection. 
 * In other words, removes from this list all of its elements that are not contained in the specified collection.

* @param c
 * @return 
 */    
    @Override
    public boolean retainAll(Collection<?> c) {
        return buffer.retainAll(c);
    }
}

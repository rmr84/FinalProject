import java.util.Iterator;


public interface Dictionary<K,V> {

    public Iterator<K> keys();

    public Iterator<V> elements();
    
    public V get(K key);

    public V remove(K key);

    public V put(K key, V value);

    public boolean isEmpty();
   
    public int size();
}

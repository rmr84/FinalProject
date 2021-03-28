import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashGiftCard<K, V> implements Dictionary<K, V> {

    private Object[] entries; 
    private int size;
    private static final int DEFAULT_CAPACITY = 150;
    private int capacity;
    public HashGiftCard() {
        this(DEFAULT_CAPACITY);
    }
    
    
    
    public HashGiftCard(int initialCapacity) {
        entries = new Object[initialCapacity];
        size = 0;
    }
    
    @Override
    public Iterator<K> keys() {
       return new Iterator<K>() {
    		private int counter = 0;
    		private int currentIndex = 0;
    		private Node currentNode = null;
    		
        	
        	@Override
        	public boolean hasNext() {
        		return counter < size;
        	}
        	
        	@SuppressWarnings("unchecked")
			@Override
        	public K next() {
        		if (!hasNext()) {
        			throw new NoSuchElementException();
        		}
        		while (currentNode == null) {
        			currentNode = (Node)entries[currentIndex++];
        		}
        		
        		K key = currentNode.getKey();
        		currentNode = currentNode.getNext();
        		counter++;
        		return key;
        	}
        };
    }
    
    
   @Override
    public Iterator<V> elements() {
        return new Iterator<V>() {
        	private int counter = 0;
    		private int currentIndex = 0;
    		private Node currentNode = null;
    		
        	
        	@Override
        	public boolean hasNext() {
        		return counter < size;
        	}
        	
        	@SuppressWarnings("unchecked")
			@Override
        	public V next() {
        		if (!hasNext()) {
        			throw new NoSuchElementException();
        		}
        		while (currentNode == null) {
        			currentNode = (Node)entries[currentIndex++];
        		}
        		
        		V value = currentNode.getValue();
        		currentNode = currentNode.getNext();
        		counter++;
        		return value;
        	}
        };
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        int index = getHashIndex(key);
        Node n = (Node)entries[index];
        while (n != null && !n.getKey().equals(key)) {
            n = n.getNext();
        }
        if (n == null) {
            return null;
        }
        return n.getValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        int index = getHashIndex(key);
        
        Node current = (Node)entries[index];
        if (current == null) return null;
        if (current.getKey().equals(key)) {
        	
        	entries[index] = current.getNext();
        	size--;
        	return current.getValue();
        }
        Node prev = current;
        current = current.getNext();
        while (current != null) {
        	if (current.getKey().equals(key)) {
        		prev.setNext(current.getNext());
        		size--;
        		return current.getValue();
        	} else {
        		 prev = current;
        	     current = current.getNext();
        	}
       
        }
        return null;
 
    }

    
    @Override
    public V put(K key, V value) {
    	if (key == null || value == null) {
    		throw new NullPointerException("Null Pointer exception.");
    	}
       Node n = getNodeForKey(key);
       if (n == null) {
    	   add(key, value);
    	   return null;
       } else {
    	   V oldValue = n.getValue();
    	   n.setValue(value);
    	   return oldValue;
       }
    }
    @SuppressWarnings("unchecked")
    public void add(K key, V value) {
    	int index = getHashIndex(key);
    	Node n = new Node(key, value);
    	n.setNext((Node)entries[index]);
    	entries[index] = n;
    	size++;
    }
	@SuppressWarnings("unchecked")
    private Node getNodeForKey(K key) {
    	Node n = (Node)(entries[getHashIndex(key)]);
    	while (n != null && !n.getKey().equals(key)) {
    		n = n.getNext();
    	}
    	return n;
    }

    private int getHashIndex(K key) {
        int capacity = entries.length;
        int index = key.hashCode() % capacity;
        if (index < 0) {
            index += capacity;
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

	private int hashIndex(K key) {
		int index = key.hashCode() % capacity;
		return (index < 0) ? (index + capacity) : index;
	}
	
	public void rehash(int newCapacity) {
		newCapacity = nextPrime(newCapacity);
		Object[] oldEntries = entries;
		entries = new Object[newCapacity];
		capacity = newCapacity;
		int counter = 0;
		for (int i = 0; i < oldEntries.length; i++) {
			Node old = (Node)oldEntries[i];
			while (old != null) {
				Node n = new Node(old.getKey(), old.getValue());
				int index = hashIndex(n.getKey());
				Node head = (Node)entries[index];
				entries[index] = n;
				n.setNext(head);
				old = old.getNext();
				counter++;
			}
		}
		assert counter == size;
	}

	private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i < (int) Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
	
    private int nextPrime(int n) {
        int p = n + 1;
        while (!isPrime(p)) {
            p++;
        }
        return p;
    }

    private class Node {

        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
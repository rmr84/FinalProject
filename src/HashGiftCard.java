import java.util.Iterator;
import java.util.NoSuchElementException;


@SuppressWarnings("hiding")
	public class HashGiftCard<User, ArrayList<GiftCard>> implements Dictionary<User,  ArrayList<GiftCard>> {
		private int size; 
		private Object[] entries;
		
		public HashGiftCard(int initialCapacity) {
			entries = new Object[initialCapacity];
			size = 0;
		}


		public Iterator<User> keys() {
		// TODO Auto-generated method stub
			return null;
		}

	
		public Iterator<ArrayList> elements() {
		// TODO Auto-generated method stub
			return null;
		}

	
		public V get(User key) {
			return null;
		}
	

	
		public V remove(User Userey) {
		// TODO Auto-generated method stub
			return null;
		}

	
		public V put(User Userey, V value) {
		
		return null;
		}

	
		public boolean isEmpty() {
		
		return size() == 0;
		}

		public int size() {
		return size;
		}
	
		private int getHashIndex(User key) {
        return key;
		}
}
	
	
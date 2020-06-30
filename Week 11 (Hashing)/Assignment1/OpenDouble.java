package Assignment1;
// Concrete class implementing MyMap using open addressing using double hashing

import java.util.*;

public class OpenDouble<K, V> implements MyMap<K, V> {

	private static int DEFAULT_INITIAL_CAPACITY = 4;
	private static int MAXIMUM_CAPACITY = 1 << 30;
	private int capacity;
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;
	private float loadFactorThreshold;
	private int size = 0;

	LinkedList<MyMap.Entry<K, V>>[] table;

	public OpenDouble() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}

	public OpenDouble(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}

	public OpenDouble(int initialCapacity, float loadFactorThreshold) {
		if (initialCapacity > MAXIMUM_CAPACITY)
			this.capacity = MAXIMUM_CAPACITY;
		else
			this.capacity = trimToPowerOf2(initialCapacity);

		this.loadFactorThreshold = loadFactorThreshold;
		table = new LinkedList[capacity];
	}

	@Override
	public void clear() {
		size = 0;
		removeEntries();
	}

	@Override
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry : bucket)
					if (entry.getValue().equals(value))
						return true;
			}
		}
		return false;
	}

	@Override
	public Set entrySet() {
		java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<>();

		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry : bucket)
					set.add(entry);
			}
		}

		return set;
	}

	@Override
	public Object get(Object key) {
		int bucketIndex = hash(key.hashCode());
		if (table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry : bucket)
				if (entry.getKey().equals(key))
					return entry.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set keySet() {
		java.util.Set<K> set = new java.util.HashSet<K>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry : bucket)
					set.add(entry.getKey());
			}
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
//		if (get(key) != null) { // The key is already in the map
//			int bucketIndex = hash(key.hashCode());
//			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
//			for (Entry<K, V> entry : bucket)
//				if (entry.getKey().equals(key)) {
//					V oldValue = entry.getValue();
//					// Replace old value with new value
//					entry.value = value;
//					// Return the old value for the key
//					return oldValue;
//				}
//		}

		// Check load factor
		if (size >= capacity * loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY)
				throw new RuntimeException("Exceeding maximum capacity");

			rehash();
		}

		int bucketIndex = hash(key.hashCode());

		// Create a linked list for the bucket if not already created
		if (table[bucketIndex] == null) {
			table[bucketIndex] = new LinkedList<Entry<K, V>>();
		}

		// Add a new entry (key, value) to hashTable[index]
		table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));

		size++; // Increase size

		return value;
	}

	@Override
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());

		// Remove the first entry that matches the key from a bucket
		if (table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry : bucket)
				if (entry.getKey().equals(key)) {
					bucket.remove(entry);
					size--; // Decrease size
					break; // Remove just one entry that matches the key
				}
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Set values() {
		java.util.Set<V> set = new java.util.HashSet<>();

		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry : bucket)
					set.add(entry.getValue());
			}
		}

		return set;
	}
	
	private void removeEntries() {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				table[i].clear();
			}
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		for (int i = 0; i < capacity; i++) {
			if (table[i] != null && table[i].size() > 0)
				for (Entry<K, V> entry : table[i])
					builder.append(entry);
		}

		builder.append("]");
		return builder.toString();
	}
	
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1; // Same as capacity *= 2. <= is more efficient
		}

		return capacity;
	}
	
	private void rehash() {
		java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
		capacity <<= 1; // Same as capacity *= 2. <= is more efficient
		table = new LinkedList[capacity]; // Create a new hash table
		size = 0; // Reset size to 0

		for (Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue()); // Store to new table
		}
	}
	
	private int hash(int hashCode) {
		int code = hashCode % capacity;
		int multFactor = 1;
		
		if (hashCode > capacity || (table[code] != null && table[code] != table[hashCode]))
			code = supplementaryHash(code);
		return code;
	}
	
	private int supplementaryHash(int hashCode) {
		int multFactor = 1;
		
		while (table[hashCode] != null) {
			hashCode += 1 + multFactor * (7 - hashCode % 7);
			multFactor++;
			if (hashCode > capacity - 1)
				hashCode -= capacity;
		}
		return hashCode;
	}
}
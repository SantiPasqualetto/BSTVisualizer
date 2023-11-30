package ADTMapBST;

/**
 * This class implements Entry interface and represents an entry for a map.
 * It contains a key and a value.
 */
public class EntryMap<K,V> implements Entry<K,V> {
	private K key;
	private V value;
	
	public EntryMap(K k, V v) {
		key = k;
		value = v;
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
	
	public String toString() {
		return "(" + key + "," + value + ")";
	}
}

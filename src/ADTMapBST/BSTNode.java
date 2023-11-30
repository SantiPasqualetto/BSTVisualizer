package ADTMapBST;

/**
 * This class represents a node of a binary search tree.
 * It contains a reference to the key, the value, the father, the left child and the right child.
 */
public class BSTNode<K,V> {
	private K key;
	private V value;
	private BSTNode<K,V> father, left, right;
	
	//Dummy node
	public BSTNode(BSTNode<K,V> father) {
		key = null;
		value = null;
		this.father = father;
		left = null;
		right = null;
	}
	
	//Node
	public BSTNode(K key, V value) {
		this(null);
		this.key = key;
		this.value = value;
	}
	
	//Root node
	public BSTNode() {
		this(null);
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
	
	public BSTNode<K,V> getFather() {
		return father;
	}
	
	public void setFather(BSTNode<K,V> father) {
		this.father = father;
	}
	
	public BSTNode<K,V> getLeft() {
		return left;
	}
	
	public void setLeft(BSTNode<K,V> left) {
		this.left = left;
	}
	
	public BSTNode<K,V> getRight() {
		return right;
	}
	
	public void setRight(BSTNode<K,V> right) {
		this.right = right;
	}
}

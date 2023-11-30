package ADTMapBST;

import java.util.Comparator;
import Excepcions.InvalidKeyException;
import ADTList.*;

/**
 * This class implements Map interface and represents a binary search tree.
 * It contains a reference to the root node of the tree, the comparator for the keys and the size of the tree.
 */
public class BSTMap<K,V> implements Map<K,V> {
	protected BSTNode<K,V> root;
	protected int cantElems;
	protected Comparator<K> comp;
	
	public BSTMap(Comparator<K> c) {
		root = new BSTNode<>();
		cantElems = 0;
		comp = c;
	}
	
	public int size() {
		return cantElems;
	}

	public boolean isEmpty() {
		return cantElems == 0;
	}

	public V get(K key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave nula!");
		}
		return getAux(root,key);
	}
	
	private V getAux(BSTNode<K,V> node, K key) {
		V toRet = null;
		if (node.getKey() != null) {
			int c = comp.compare(key, node.getKey());
			if (c == 0) {
				toRet = node.getValue();
			}
			if (c < 0) {
				toRet = getAux(node.getLeft(), key);
			}
			if (c > 0) {
				toRet = getAux(node.getRight(), key);
			}
		}
		return toRet;
	}

	public V put(K key, V value) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave nula!");
		}
		return putAux(root,key,value);
	}
	
	private V putAux(BSTNode<K,V> node, K key, V value) {
		V toRet = null;
		if (node.getKey() == null) {
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new BSTNode<>(node));
			node.setRight(new BSTNode<>(node));
			cantElems++;
		} else {
			int c = comp.compare(key, node.getKey());
			if (c == 0) {
				toRet = node.getValue();
				node.setValue(value);
			}
			if (c < 0) {
				putAux(node.getLeft(),key,value);
			}
			if (c > 0) {
				putAux(node.getRight(), key, value);
			}
		}
		return toRet;
	}

	public V remove(K key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave nula!");
		}
		return removeAux(root,key);
	}
	
	private V removeAux(BSTNode<K,V> node, K key) {
		V toRet = null;
		if (node.getKey() != null) {
			int c = comp.compare(key, node.getKey());
			if (c == 0) {
				toRet = node.getValue();
				BSTNode<K, V> left = node.getLeft();
				BSTNode<K, V> rigth = node.getRight();
				if (node == root) {
					if (left.getKey() != null && rigth.getKey() != null) { //Case two childs
						BSTNode<K,V> sucesor = sucesorIn(node.getRight());
						root.setKey(sucesor.getKey());
						root.setValue(sucesor.getValue());
					} else if (left.getKey() != null) { 				   //Case one child (left)
						root = left;
						root.setFather(null);
					} else { 											   //Case one child (rigth)
						root = rigth;
						root.setFather(null);
					} 
				} else {
					BSTNode<K, V> father = node.getFather();
					if (left.getKey() != null && rigth.getKey() != null) { //Case two childs
						BSTNode<K,V> sucesor = sucesorIn(node.getRight());
						node.setKey(sucesor.getKey());
						node.setValue(sucesor.getValue());
					} else if (left.getKey() != null) { 				   //Case one child (left)
						if (father.getLeft() == node) {
							father.setLeft(left);
						} else {
							father.setRight(left);
						}
						left.setFather(father);
					} else { 										       //Case one child (rigth)
						if (father.getLeft() == node) {
							father.setLeft(rigth);
						} else {
							father.setRight(rigth);
						}
						rigth.setFather(father);
					} 
				}
				if (!(left.getKey() != null && rigth.getKey() != null)) {
					setNull(node);
				}								   
				cantElems--;
			}
			if (c < 0) {
				toRet = removeAux(node.getLeft(),key);
			}
			if (c > 0) {
				toRet = removeAux(node.getRight(), key);
			}
		}
		return toRet;
	}
	
	private void setNull(BSTNode<K,V> n) {
		n.setKey(null);
		n.setValue(null);
		n.setFather(null);
		n.setLeft(null);
		n.setRight(null);
	}
	
	private BSTNode<K,V> sucesorIn(BSTNode<K,V> node) {
		BSTNode<K,V> toRet = null;
		if (node.getLeft().getKey() != null) {
			toRet = sucesorIn(node.getLeft());
		} else {
			toRet = node;
			if (node.getRight().getKey() != null) {
				if (node.getFather().getLeft() == node) {
					node.getFather().setLeft(node.getRight());
				} else {
					node.getFather().setRight(node.getRight());
				}
				node.getRight().setFather(node.getFather());
			} else {
				if (node.getFather().getLeft() == node) {
					node.getFather().setLeft(new BSTNode<>(node.getFather()));
				} else {
					node.getFather().setRight(new BSTNode<>(node.getFather()));
				}
			}
		}
		return toRet;
	}

	public Iterable<K> keys() {
		PositionList<K> toRet = new DoubleLinkedList<>();
		if (!isEmpty()) {
			keysAux(root, toRet);
		}
		return toRet;
	}
	
	private void keysAux(BSTNode<K,V> n, PositionList<K> l) {
		l.addLast(n.getKey());
		if (n.getLeft().getKey() != null) {
			keysAux(n.getLeft(), l);
		}
		if (n.getRight().getKey() != null) {
			keysAux(n.getRight(), l);
		}
	}

	public Iterable<V> values() {
		PositionList<V> toRet = new DoubleLinkedList<>();
		if (!isEmpty()) {
			valuesAux(root, toRet);
		}
		return toRet;
	}
	
	private void valuesAux(BSTNode<K,V> n, PositionList<V> l) {
		l.addLast(n.getValue());
		if (n.getLeft().getKey() != null) {
			valuesAux(n.getLeft(), l);
		}
		if (n.getRight().getKey() != null) {
			valuesAux(n.getRight(), l);
		}
	}

	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toRet = new DoubleLinkedList<>();
		if (!isEmpty()) {
			entriesAux(root, toRet);
		}
		return toRet;
	}
	
	public void entriesAux(BSTNode<K,V> n, PositionList<Entry<K,V>> l) {
		l.addLast(new EntryMap<>(n.getKey(),n.getValue()));
		if (n.getLeft().getKey() != null) {
			entriesAux(n.getLeft(), l);
		}
		if (n.getRight().getKey() != null) {
			entriesAux(n.getRight(), l);
		}
	}
}

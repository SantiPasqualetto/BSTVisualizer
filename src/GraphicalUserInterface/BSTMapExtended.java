package GraphicalUserInterface;

import java.util.Comparator;
import ADTMapBST.*;

/**
 * This class extends the BSTMap class, adding some methods that are useful for the GUI.
 */
public class BSTMapExtended<K,V> extends BSTMap<K,V>{
	public BSTMapExtended(Comparator<K> c) {
		super(c);
	}

	/**
	 * Returns the root of the tree.
	 */
	public BSTNode<K,V> root() {
		return root;
	}

	/**
	 * Returns the depth of the node.
	 */
	public int depth(BSTNode<K,V> n) {
		int toRet = 0;
		if (n != root) {
			toRet = 1 + depth(n.getFather());
		}
		return toRet;
	}

	/**
	 * Returns true if the node is on the left subtree, false otherwise.
	 */
	public boolean leftOrRight(BSTNode<K,V> n, BSTNode<K,V> rootLeft) {
		BSTNode<K,V> aux = n;
		boolean toRet = false;
		while (aux.getFather() != null && !toRet) {
			if (aux.getFather() == rootLeft) {
				toRet = true;
			}
			aux = aux.getFather();
		}
		if (n == rootLeft) {
			toRet = true;
		}
		return toRet;
	}
}

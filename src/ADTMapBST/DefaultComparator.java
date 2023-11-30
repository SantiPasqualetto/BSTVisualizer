package ADTMapBST;

import java.util.Comparator;

/**
 * This class implements Comparator interface and represents a default comparator.
 * It compares two elements of the same type.
 */
public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException {
		return ((Comparable<E>)a).compareTo(b);
	}
}

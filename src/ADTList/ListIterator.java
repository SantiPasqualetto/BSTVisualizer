package ADTList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import Excepcions.*;

/**
 * This class implements Iterator interface and represents an iterator for a list.
 * It contains a reference to the current position of the iterator and the list.
 * It iterates over the list from the first to the last position.
 */
public class ListIterator<E> implements Iterator<E>{
	private Position<E> cursor;
	private PositionList<E> list;
	
	public ListIterator(PositionList<E> l) {
		list = l;
		if (!l.isEmpty()) {
			try {
				cursor = l.first();
			} catch (EmptyListException e) {e.printStackTrace();}
		} else {
			cursor = null;
		}
	}
	
	public boolean hasNext() {
		return cursor != null;
	}

	public E next() throws NoSuchElementException { 	
		if (cursor == null) {
			throw new NoSuchElementException("Error: No hay siguiente");
		}
		E toReturn = cursor.element();  
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();} 
		return toReturn;
	}
}

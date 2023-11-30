package ADTList;

import java.util.Iterator;
import Excepcions.*;

/**
 * This class implements PositionList interface and represents a double linked list.
 * It contains a reference to the first and last nodes of the list and the size of the list.
 */
public class DoubleLinkedList<E> implements PositionList<E> {
	protected int size;
	protected DNode<E> head, tail;
	
	public DoubleLinkedList() {
		head = new DNode<E>(null,null,null);
		tail = new DNode<E>(null,null,null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Lista vacia!");
		}
		return head.getNext();
	}

	public Position<E> last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Lista vacia!");
		}
		return tail.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> nodo = checkPosition(p);
		if (nodo.getNext() == tail) {
			throw new BoundaryViolationException("La ultima posicion no tiene siguiente");
		}
		return nodo.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> nodo = checkPosition(p);
		if (nodo.getPrev() == head) {
			throw new BoundaryViolationException("La primera posicion no tiene anterior");
		}
		return nodo.getPrev();
	}

	public void addFirst(E element) {
		DNode<E> aux = new DNode<>(element,head.getNext(),head);
		head.getNext().setPrev(aux);
		head.setNext(aux);
		size++;
	}

	public void addLast(E element) {
		DNode<E> aux = new DNode<>(element,tail,tail.getPrev());
		tail.getPrev().setNext(aux);
		tail.setPrev(aux);
		size++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> nodo = checkPosition(p);
		DNode<E> aux = new DNode<>(element,nodo.getNext(),nodo);
		nodo.getNext().setPrev(aux);
		nodo.setNext(aux);
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> nodo = checkPosition(p);
		DNode<E> aux = new DNode<>(element,nodo,nodo.getPrev());
		nodo.getPrev().setNext(aux);
		nodo.setPrev(aux);
		size++;
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		DNode<E> nodo = checkPosition(p);
		E toRet = nodo.element();
		nodo.getPrev().setNext(nodo.getNext());
		nodo.getNext().setPrev(nodo.getPrev());
		nodo = null;
		size--;
		return toRet;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> nodo = checkPosition(p);
		E toRet = nodo.element();
		nodo.setElement(element);
		return toRet;
	}

	public Iterator<E> iterator() {
		return new ListIterator<E>(this);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toRet = new DoubleLinkedList<>();
		if (!isEmpty()) {
			try {
				Position<E> pos = first();
				while (pos != last()) {
					toRet.addLast(pos);
					pos = next(pos);
				}
				toRet.addLast(pos);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		}
		return toRet;
	}
	
	/*
	 * This method checks if the position is valid and returns the node of the position.
	 */
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		DNode<E> toRet = null;
		if (p == null || isEmpty()) {
			throw new InvalidPositionException("Posicion invalida!");
		}
		try {
			toRet = (DNode<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Error de casteo!");
		}
		return toRet;
	}
}

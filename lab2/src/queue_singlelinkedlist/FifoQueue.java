package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E e) {
		QueueNode<E> temp = new QueueNode<E>(e);

		if (size == 0) {
			last = temp;
			last.next = last;

		} else {
			temp.next = last.next;
			last.next = temp;
			last = temp;
		}

		size++;
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;

		} else {

			return last.next.element;
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		} else {
			QueueNode<E> first = last.next;
			last.next = first.next;
			size--;

			return first.element;
		}
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int count = 0;

		/* Konstruktor */
		private QueueIterator() {
			if (last == null) {
				pos = null;
			} else {
				pos = last.next;
			}
			count = 0;
		}

		public boolean hasNext() {
			if (pos == null) {
				return false;
			} else if (count >= size) {
				return false;
			} else {
				return true;
			}
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			QueueNode<E> temp = pos;
			pos = temp.next;
			count++;
			return temp.element;
		}
	}

	public void append(FifoQueue<E> q) {
		if (this == q) {
			throw new IllegalArgumentException();
		}
		if (q.isEmpty()) {

		} else if (isEmpty()) {
			last = q.last;
			size += q.size;
		} else if (!isEmpty()) {
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
			last = q.last;
			size += q.size;
		}
		q.size = 0;
		q.last = null;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}

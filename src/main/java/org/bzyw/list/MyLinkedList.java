package org.bzyw.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
	/**
	 * 数据量
	 */
	private int size;

	private int modCount;

	/**
	 * 头节点
	 */
	private Node<E> begin;

	/**
	 * 尾节点
	 */
	private Node<E> end;

	public MyLinkedList() {
		doClear();
	}

	/**
	 * 节点
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		private E data;
		private Node<E> prev;
		private Node<E> next;

		Node(E data, Node<E> prev, Node<E> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		return getNode((E) o) != null;
	}

	private class Ite implements Iterator<E> {
		private Node<E> current = begin;
		private int count = modCount;

		@Override
		public boolean hasNext() {
			return current.next != end;
		}

		@Override
		public E next() {
			if (count != modCount)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();
			current = current.next;
			return current.data;
		}

		@Override
		public void remove() {
			if (count != modCount)
				throw new ConcurrentModificationException();
			current = current.prev;
			MyLinkedList.this.remove(current);
			count++;
		}
	}

	public Iterator<E> iterator() {
		return new Ite();
	}

	public boolean add(E e) {
		addBefore(end, e);
		return true;
	}

	private void addBefore(Node<E> node, E data) {
		Node<E> newNode = new Node<>(data, node.prev, node);
		node.prev.next = newNode;
		node.prev = newNode;
		size++;
		modCount++;
	}

	private void remove(Node<E> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
		modCount++;
	}

	private Node<E> getNode(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index is negative.");
		Node<E> node = begin;
		for (int i = 0; i < index; i++)
			node = node.next;
		return node;
	}

	private Node<E> getNode(E data) {
		Node<E> node = begin;
		if (data == null) {
			for (int i = 0; i < size; i++) {
				node = node.next;
				if (node.data == null)
					return node;
			}
		} else {
			for (int i = 0; i < size; i++) {
				node = node.next;
				if (data.equals(node.data))
					return node;
			}
		}
		return null;
	}

	public boolean remove(Object o) {
		@SuppressWarnings("unchecked")
		Node<E> node = getNode((E) o);
		if (node != null) {
			remove(node);
			return true;
		}
		return false;
	}

	public void clear() {
		doClear();
	}

	private void doClear() {
		begin = new Node<>(null, null, null);
		end = new Node<>(null, begin, null);
		begin.next = end;
		size = 0;
		modCount = 0;
	}

	public E get(int index) {
		return getNode(index).data;
	}

	public E set(int index, E element) {
		Node<E> node = getNode(index);
		E oldData = node.data;
		node.data = element;
		return oldData;
	}

	public void add(int index, E element) {
		Node<E> node = getNode(index);
		addBefore(node, element);
	}

	public E remove(int index) {
		Node<E> node = getNode(index);
		remove(node);
		return node.data;
	}

}

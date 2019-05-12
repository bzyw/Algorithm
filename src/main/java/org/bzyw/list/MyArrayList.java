package org.bzyw.list;

import java.util.*;

public class MyArrayList<E> {
	private static final Object[] EMPTY = {};

	private static final int DEFAULT_CAPACITY = 10;

	private Object[] data = null;

	private int size;

	public MyArrayList() {
		data = EMPTY;
	}

	public MyArrayList(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("capacity cannot be negative.capacity:" + capacity);
		data = new Object[capacity];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		if (o == null) {
			for (Object element : data) {
				if (element == null)
					return true;
			}
		} else {
			for (Object element : data) {
				if (o.equals(element))
					return true;
			}
		}
		return false;
	}

	public Iterator<E> iterator() {
		return new It();
	}

	private class It implements Iterator<E> {
		private int index = 0;
		private int lastPos = -1;

		public boolean hasNext() {
			if (index < size)
				return true;
			else
				return false;
		}

		@SuppressWarnings("unchecked")
		public E next() {
			if (index < size) {
				int i = index;
				index++;
				return (E) data[lastPos = i];
			} else {
				throw new IndexOutOfBoundsException("");
			}
		}

		public void remove() {
			if (lastPos < 0)
				throw new IllegalStateException();
			System.arraycopy(data, index + 1, data, index, size - index);
			index--;
			size--;
			lastPos = -1;
		}
	}

	public boolean add(E e) {
		if (size == data.length)
			ensureCapacity(size + size >> 1);
		data[size++] = e;
		return true;
	}

	private void ensureCapacity(int newCapacity) {
		if (newCapacity < data.length)
			return;
		else {
			Object[] temp = data;
			data = new Object[newCapacity];
			System.arraycopy(temp, 0, data, 0, temp.length);
		}
	}

	public boolean remove(Object o) {
		boolean result = false;
		if (o == null) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] == null) {
					if (i < data.length - 1)
						System.arraycopy(data, i + 1, data, i, size - i);
					size--;
					result = true;
				}
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				if (o.equals(data[i])) {
					if (i < data.length - 1)
						System.arraycopy(data, i + 1, data, i, size - i);
					size--;
					result = true;
				}
			}
		}
		return false;
	}

	public void clear() {
		ensureCapacity(DEFAULT_CAPACITY);
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		return (E) data[index];
	}

	public E set(int index, E element) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		data[index] = element;
		return element;
	}

	public void add(int index, E element) {
		if (size == data.length) {
			ensureCapacity(size + size >> 1);
		}
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;
		size++;
	}

	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		@SuppressWarnings("unchecked")
		E result = (E) data[index];
		if (index == data.length - 1) {
			size--;
			return result;
		}
		System.arraycopy(data, index + 1, data, index, size - index - 1);
		return result;
	}
}
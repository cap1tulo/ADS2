import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity();
        elements[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[0];
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[size - 1];
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable) elements[j]).compareTo(elements[j + 1]) > 0) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public T[] toArray() {
        return (T[]) java.util.Arrays.copyOf(elements, size);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return (T) elements[index++];
            }

        };
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[size * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        System.out.println("Size: " + myList.size());
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());

        myList.removeFirst();
        System.out.println("After removing first element, first element: " + myList.getFirst());

        myList.addLast(4);
        myList.addFirst(0);

        System.out.println("Index of 3: " + myList.indexOf(3));
        System.out.println("Last index of 4: " + myList.lastIndexOf(4));

        myList.sort();
        System.out.print("Sorted list: ");
        for (Integer item : myList) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println("Is 2 in the list? " + myList.contains(2));

        System.out.print("List as array: ");
        Object[] objectArray = myList.toArray();
        Integer[] array = new Integer[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            array[i] = (Integer) objectArray[i];
        }
        for (Integer item : array) {
            System.out.print(item + " ");
        }
        System.out.println();

        myList.clear();
        System.out.println("List cleared, size: " + myList.size());
    }
}

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a list with the given item as the first object. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size() * 2];

        for (int i = 0; i < other.size(); i++) {
            items[i] = (T) other.get(i);
        }

        size = other.size();
        nextLast = size;
        nextFirst = items.length - 1;
    }

    /** Resizing the list. */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];

        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, 0, size);
        } else {
            int firstSize = items.length - nextFirst - 1;
            int secondSize = size - firstSize;
            if (firstSize != 0) {
                System.arraycopy(items, nextFirst + 1, a, 0, firstSize);
            }
            if (secondSize != 0) {
                System.arraycopy(items, 0, a, firstSize, secondSize);
            }
        }

        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Finds the actual index of the given index in the array. */
    private int indexCounter(int index) {
        int actualIndex = nextFirst + index + 1;

        if (actualIndex >= items.length) {
            actualIndex -= items.length;
        }

        return actualIndex;
    }

    /** Counts the usage of the list. */
    private double usageCounter() {
        return (double) size / items.length;
    }

    /** Adds an item to the front of the list. */
    @Override
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        size += 1;


        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /** Adds an item to the end of the list. */
    @Override
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(items.length * 2);
        }

        items[nextLast] = item;
        size += 1;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    /** Returns the size of the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Removes and returns the first item of the list. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        T val = items[nextFirst];

        if (items.length > 16 && usageCounter() < 0.25) {
            resize(items.length / 2);
        }

        return val;
    }

    /** Removes and returns the last item of the list. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast += items.length;
        }
        T val = items[nextLast];

        if (items.length > 16 && usageCounter() < 0.25) {
            resize(items.length / 2);
        }

        return val;
    }


    /** Get the item at the given index. If no such item
     * exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[indexCounter(index)];
    }
}

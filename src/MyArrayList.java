import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

public class MyArrayList<ELEMENT> extends AbstractList<ELEMENT>
        implements List<ELEMENT>, RandomAccess, Cloneable, Serializable {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    Object[] elementData;

    private int size;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public MyArrayList(Collection<? extends ELEMENT> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    static <ELEMENT> ELEMENT elementAt(Object[] es, int index) {
        return (ELEMENT) es[index];
    }

    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENT_DATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
                && !(elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA
                && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0)
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    int lastIndexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Object clone() {
        try {
            MyArrayList<?> v = (MyArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @NotNull
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @NotNull
    public <ARRAY> ARRAY[] toArray(ARRAY[] a) {
        if (a.length < size)
            return (ARRAY[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    ELEMENT elementData(int index) {
        return elementAt(elementData, index);
    }

    @Override
    public ELEMENT get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public ELEMENT set(int index, ELEMENT element) {
        Objects.checkIndex(index, size);
        ELEMENT oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    private void add(ELEMENT element, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = element;
        size = s + 1;
    }

    @Override
    public boolean add(ELEMENT element) {
        modCount++;
        add(element, elementData, size);
        return true;
    }

    public void add(int index, ELEMENT element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }

    public ELEMENT remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        ELEMENT oldValue = (ELEMENT) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List)) {
            return false;
        }

        final int expectedModCount = modCount;
        boolean equal = (o.getClass() == MyArrayList.class)
                ? equalsArrayList((MyArrayList<?>) o)
                : equalsRange((List<?>) o, 0, size);

        checkForComodification(expectedModCount);
        return equal;
    }

    boolean equalsRange(List<?> other, int from, int to) {
        final Object[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        var oit = other.iterator();
        for (; from < to; from++) {
            if (!oit.hasNext() || !Objects.equals(es[from], oit.next())) {
                return false;
            }
        }
        return !oit.hasNext();
    }

    private boolean equalsArrayList(MyArrayList<?> other) {
        final int otherModCount = other.modCount;
        final int s = size;
        boolean equal;
        if (equal = (s == other.size)) {
            final Object[] otherEs = other.elementData;
            final Object[] es = elementData;
            if (s > es.length || s > otherEs.length) {
                throw new ConcurrentModificationException();
            }
            for (int i = 0; i < s; i++) {
                if (!Objects.equals(es[i], otherEs[i])) {
                    equal = false;
                    break;
                }
            }
        }
        other.checkForComodification(otherModCount);
        return equal;
    }

    private void checkForComodification(final int expectedModCount) {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public Iterator<ELEMENT> iterator() {
        return super.iterator();
    }

    @Override
    public ListIterator<ELEMENT> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<ELEMENT> listIterator(int index) {
        return super.listIterator(index);
    }

    private class ListItr implements ListIterator<ELEMENT> {

        private int nextIdx;
        private int lastRetIdx = -1;
        private int expectedModCount = modCount;

        public ListItr(int index) {
            nextIdx = index;
        }

        @Override
        public boolean hasNext() {
            return nextIdx != size;
        }

        @Override
        public ELEMENT next() {
            checkForComodification();
            int i = nextIdx;
            nextIdx++;
            return elementData(lastRetIdx = i);
        }

        @Override
        public boolean hasPrevious() {
            return nextIdx != 0;
        }

        @Override
        public ELEMENT previous() {
            checkForComodification();
            int i = nextIdx - 1;
            nextIdx = i;
            return elementData(lastRetIdx = i);
        }

        @Override
        public int nextIndex() {
            return nextIdx;
        }

        @Override
        public int previousIndex() {
            return nextIdx - 1;
        }

        @Override
        public void remove() {
            if (lastRetIdx < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();
            MyArrayList.this.remove(lastRetIdx);
            nextIdx = lastRetIdx;
            lastRetIdx = -1;
            expectedModCount = modCount;
        }

        @Override
        public void set(ELEMENT element) {
            if (lastRetIdx < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();
            MyArrayList.this.set(lastRetIdx, element);
        }

        @Override
        public void add(ELEMENT element) {
            checkForComodification();
            MyArrayList.this.add(nextIdx, element);
            nextIdx++;
            lastRetIdx = -1;
            expectedModCount = modCount;
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

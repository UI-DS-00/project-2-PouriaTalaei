public class LinkedList<T> {
    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> head = null; // head node of the list (or null if empty)
    private Node<T> tail = null; // last node of the list (or null if empty)
    private int size = 0; // number of nodes in the list

    public LinkedList() {
    } // constructs an initially empty list

    // access methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T first() { // returns (but does not remove) the first element
        if (isEmpty()) return null;
        return head.getElement();
    }

    public T last() { // returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // update methods
    public void addFirst(T t) { // adds element e to the front of the list
        head = new Node<>(t, head); // create and link a new node
        if (size == 0)
            tail = head; // special case: new node becomes tail also
        size++;
    }

    public void addLast(T t) { // adds element e to the end of the list
        Node<T> newest = new Node<>(t, null); // node will eventually be the tail
        if (isEmpty())
            head = newest; // special case: previously empty list
        else tail.setNext(newest); // new node after existing tail
        tail = newest; // new node becomes the tail
        size++;
    }

    public T removeFirst() { // removes and returns the first element
        if (isEmpty()) return null; // nothing to remove
        T answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null; // special case as list is now empty
        return answer;
    }
}

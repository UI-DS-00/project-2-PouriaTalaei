import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class LinkedList<T> {
    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
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

    private String nameOfPlayList;
    private Node<T> head = null; // head node of the list (or null if empty)
    private Node<T> tail = null; // last node of the list (or null if empty)
    private int size = 0; // number of nodes in the list

    public LinkedList(String nameOfPlayList) {
        setNameOfPlayList(nameOfPlayList);
    } // constructs an initially empty list

    public String getNameOfPlayList() {
        return nameOfPlayList;
    }

    public void setNameOfPlayList(String nameOfPlayList) {
        this.nameOfPlayList = nameOfPlayList;
    }

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

    public void removeFirst() { // removes and returns the first element
        if (isEmpty()) return; // nothing to remove
        T answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null; // special case as list is now empty
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }


    public void removeSong(LinkedList<Music> playList, String nameOfSong) {
        if (playList.getSize() == 0) return;
        Node<Music> current;
        current = playList.getHead();
        Node<Music> previous = new Node<Music>();
        while ((current != null) && (!Objects.equals(current.element.getTrackName(), nameOfSong))) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (current == head)
                head = (Node<T>) current.next;
            else
                previous.next = current.next;
            size = size - 1;
        }
    }


    PlayListMethods playListMethods = new PlayListMethods();

    public void mergePlayLists(LinkedList<Music> playList1, LinkedList<Music> playList2) {
        LinkedList<Music> mergePlayList = new LinkedList<>("mergePlayList1");
        PlayListMethods.playlists.add(mergePlayList);
        Node<Music> currentPlayList1;
        currentPlayList1 = playList1.getHead();
        Node<Music> previous1 = new Node<>();
        Node<Music> currentPlayList2;
        currentPlayList2 = playList2.getHead();
        Node<Music> previous2 = new Node<>();

        //add songs of playList one to mergePlayList
        while (currentPlayList1 != null) {
            mergePlayList.addLast(currentPlayList1.element);
            currentPlayList1 = currentPlayList1.next;
        }
        currentPlayList1 = playList1.getHead();
        boolean repetitiousSong = false;
        while (currentPlayList2 != null) {
            while ((currentPlayList1 != null)) {
                if (Objects.equals(currentPlayList1.element.getTrackName(), currentPlayList2.element.getTrackName()))
                    repetitiousSong = true;
                currentPlayList1 = currentPlayList1.next;
            }
            currentPlayList1 = playList1.getHead();
            if (!repetitiousSong)
                mergePlayList.addLast(currentPlayList2.element);
            else repetitiousSong = false;
            currentPlayList2 = currentPlayList2.next;
        }
    }

    public void shuffleMerge(String nameOfShufflePlayList) {
        LinkedList<Music> shufflePlayList = new LinkedList<>(nameOfShufflePlayList);
        ArrayList<Music> allSongs = new ArrayList<>();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        addAllSongToArrayList(allSongs);
        for (int i = 0; i < allSongs.size(); i++) { //create Array of numbers to choose them random
            randomNumbers.add(i, i);
        }
        addSongsToShuffleMergeRandom(randomNumbers,random,shufflePlayList,allSongs);
        PlayListMethods.playlists.add(shufflePlayList);
    }

    public void addSongsToShuffleMergeRandom(ArrayList<Integer> randomNumbers, Random random, LinkedList<Music> shufflePlayList, ArrayList<Music> allSongs) {
        for (int i = 0; i < randomNumbers.size(); ) {
            int randomNumber = random.nextInt(randomNumbers.size());
            shufflePlayList.addLast(allSongs.get(randomNumber));
            randomNumbers.remove(randomNumber);
        }

    }

    public void addAllSongToArrayList(ArrayList<Music> allSongs) {
        Node<Music> current;
        current = PlayListMethods.playlists.get(0).getHead();
        //first add one playlist to decrease HazIneH
        while (current != null) {
            allSongs.add(current.element);
            current = current.next;
        }
        boolean repetitiousSong = false;
        for (int i = 1; i < PlayListMethods.playlists.size(); i++) {
            current = PlayListMethods.playlists.get(i).getHead();
            while (current != null) {
                for (int j = 0; j < allSongs.size(); j++) {
                    if (Objects.equals(allSongs.get(j).getTrackName(), current.element.getTrackName()))
                        repetitiousSong = true;
                }
                if (!repetitiousSong)
                    allSongs.add(current.element);
                else repetitiousSong = false;
                current = current.next;
            }
        }

    }
}
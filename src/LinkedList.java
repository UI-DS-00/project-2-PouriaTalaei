import java.util.*;

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
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public LinkedList(String nameOfPlayList) {
        setNameOfPlayList(nameOfPlayList);
    }

    public String getNameOfPlayList() {
        return nameOfPlayList;
    }

    public void setNameOfPlayList(String nameOfPlayList) {
        this.nameOfPlayList = nameOfPlayList;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public T last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(T t) {
        head = new Node<>(t, head); // create and link a new node
        if (size == 0)
            tail = head;
        size++;
    }

    public void addLast(T t) {
        Node<T> newest = new Node<>(t, null);
        if (isEmpty())
            head = newest;
        else tail.setNext(newest);
        tail = newest;
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) return;
        T answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;
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
        Node<Music> previous = new Node<>();
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

    public void mergePlayLists(LinkedList<Music> playList1, LinkedList<Music> playList2) {
        LinkedList<Music> mergePlayList = new LinkedList<>("mergePlayList1");
        PlayListMethods.playlists.add(mergePlayList);
        Node<Music> currentPlayList1;
        currentPlayList1 = playList1.getHead();
        Node<Music> currentPlayList2;
        currentPlayList2 = playList2.getHead();

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

    public void shuffleMerge() {
        LinkedList<Music> shufflePlayList = new LinkedList<>("ShufflePlayList");
        ArrayList<Music> allSongs = new ArrayList<>();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        addAllSongToArrayList(allSongs);
        for (int i = 0; i < allSongs.size(); i++) { //create Array of numbers to choose them random
            randomNumbers.add(i, i);
        }
        addSongsToShuffleMergeRandom(randomNumbers, random, shufflePlayList, allSongs);
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

    public void playMusic(LinkedList<Music> playList) {
        Node<Music> current;
        current = playList.getHead();
        while (current != null) {
            printInfoOfSong(current.element);
            System.out.println();
            current = current.next;
        }
    }

    public void printInfoOfSong(Music music){
        System.out.println("ArtistName : " + music.getArtistName());
        System.out.println("Track Name : " + music.getTrackName());
        System.out.println("Release Date : " + music.getReleaseDate());
        System.out.println("Genre : " + music.getGenre());
        System.out.println("Len : " + music.getLen());
        System.out.println("Topic : " + music.getTopic());
    }

    public void filter(int numberOfMenu, String theFilterWord, LinkedList<Music> playList) {
        LinkedList<Music> filterPlayList = new LinkedList<>("FilterPlayList");
        if (playList.getSize() == 0) return;
        Node<Music> current;
        current = playList.getHead();

        switch (numberOfMenu) {
            case 1://ArtistName
                while ((current != null) && (Objects.equals(current.element.getArtistName(), theFilterWord))) {
                    filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;

            case 2://track Name
                while (current != null) {
                    if (Objects.equals(current.element.getTrackName(), theFilterWord))
                        filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;

            case 3://releaseDate
                while (current != null) {
                    if (Objects.equals(current.element.getReleaseDate(), theFilterWord))
                        filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;

            case 4://genre
                while (current != null) {
                    if (Objects.equals(current.element.getGenre(), theFilterWord))
                        filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;

            case 5://len
                while (current != null) {
                    if (Objects.equals(current.element.getLen(), theFilterWord))
                        filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;

            case 6://topic
                while (current != null) {
                    if (Objects.equals(current.element.getTopic(), theFilterWord))
                        filterPlayList.addLast(current.element);
                    current = current.next;
                }
                break;
        }
        showPlayList(filterPlayList);
    }

    public void showPlayList(LinkedList<Music> playList) {
        if (playList.getSize() == 0)
            System.out.println("Find No Song!");
        else
            printPlayList(playList);
    }

    public void printPlayList(LinkedList<Music> playList) {
        Node<Music> current;
        current = playList.getHead();
        while (current != null) {
            System.out.println("ArtistName : " + current.element.getArtistName());
            System.out.println("Track Name : " + current.element.getTrackName());
            System.out.println("Release Date : " + current.element.getReleaseDate());
            System.out.println("Genre : " + current.element.getGenre());
            System.out.println("Len : " + current.element.getLen());
            System.out.println("Topic : " + current.element.getTopic());
           // System.out.println("liked : " + current.element.favorite);
            System.out.println();
            current = current.next;
        }
    }

    public void addPlayListToArrayList(ArrayList<Music> sortPlayList, LinkedList<Music> playList) {
        Node<Music> current;
        current = playList.getHead();
        while (current != null) {
            sortPlayList.add(current.element);
            current = current.next;
        }
        for (int i = 0; i < sortPlayList.size(); i++) {

        //    if (playListMethods.isSwap(sortPlayList.get(i).getTrackName(), sortPlayList.get(i + 1).getTrackName()))
        }
    }


//    public void sortSongs(LinkedList<Music> playList) {
//        Node<Music> node1;
//        Node<Music> node2;
//        Node<Music> node3;
//        Node<Music> previous;
//        node1 = playList.getHead();
//        node2 = playList.getHead().getNext();
//
//
////        boolean isSort = false;
////        while (!isSort) {
////            while ((node1 != null) || (node2 != null)) {
////                if (playListMethods.isSwap(node1.element.getTrackName(), node2.element.getTrackName())) {
////                    previous = playList.getHead();
////                    while (previous != null) {
////                        if (previous.next != node1) {
////                            previous = previous.next;
////                        } else break;
////                    }
////                    node3 = node1;
////                    previous.next = node2;
////                    node2.next = node3;
////                }
////            }
////        }
//    }
}
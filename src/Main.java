import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ScanFile scanFile = new ScanFile();
        scanFile.ScanCSVFile();


       // ArrayList<LinkedList<Music>> playlists = new ArrayList<>();
        LinkedList<Music> playList = new LinkedList<Music>("plaListOnE");
        LinkedList<Music> playList2 = new LinkedList<>("playLiSTtwO");


        playList.addFirst(scanFile.fileMusic.get(5));
        playList.addFirst(scanFile.fileMusic.get(9));
        playList.addFirst(scanFile.fileMusic.get(3));
     //   playList.addFirst(scanFile.fileMusic.get(7));
      //  playList.removeFirst();
        //playList2.addFirst(scanFile.fileMusic.get(10));
        playList2.addFirst(scanFile.fileMusic.get(9));
        playList2.addFirst(scanFile.fileMusic.get(4));
      //  playList.removeSong(playList,"cry");
        PlayListMethods.playlists.add(playList);
        PlayListMethods.playlists.add(playList2);
        playList.mergePlayLists(playList,playList2);
        playList.filter(6,"romantic",playList);





    }
}

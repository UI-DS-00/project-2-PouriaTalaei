import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ScanFile scanFile = new ScanFile();
        scanFile.ScanCSVFile();

        ArrayList<LinkedList<Music>> playlists = new ArrayList<>();
        PlayListMethods playListMethods = new PlayListMethods();

//        playList.addFirst(scanFile.fileMusic.get(5));
//        playList.addFirst(scanFile.fileMusic.get(3));
//        playList.removeFirst();
//        playlists.add(playList);
    }
}

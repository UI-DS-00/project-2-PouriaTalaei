import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ScanFile scanFile = new ScanFile();
        scanFile.ScanCSVFile();


        ArrayList<LinkedList<Music>> playlists = new ArrayList<>();
        LinkedList<Music> playList = new LinkedList<Music>("plaListOnE");


        playList.addFirst(scanFile.fileMusic.get(5));
        playList.addFirst(scanFile.fileMusic.get(9));
        playList.addFirst(scanFile.fileMusic.get(3));
        playList.addFirst(scanFile.fileMusic.get(7));
        playList.removeFirst();
        playList.removeSong(playList,"cry");
        playlists.add(playList);
    }
}

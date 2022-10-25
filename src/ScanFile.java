import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanFile {
    ArrayList<Music> fileMusic = new ArrayList<>();

    public void ScanCSVFile() {
        try (
                Scanner scanner = new Scanner(Paths.get("musics.csv").toFile())) {

            // CSV file delimiter
            String delimiter = "\r\n";

            // set comma as delimiter
            scanner.useDelimiter(delimiter);

            // read all fields
            while (scanner.hasNext()) {
                String infoOfSong = scanner.next();
                String[] infoOfSongArray = infoOfSong.split(",");
                Music music = new Music(infoOfSongArray[0], infoOfSongArray[1], infoOfSongArray[2], infoOfSongArray[3], infoOfSongArray[4], infoOfSongArray[5]);
                fileMusic.add(music);
            }

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
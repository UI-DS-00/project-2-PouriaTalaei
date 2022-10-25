import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ScanFile scanFile = new ScanFile();
        scanFile.ScanCSVFile();
        Panel panel = new Panel();
        panel.printPanel(scanFile);
    }
}

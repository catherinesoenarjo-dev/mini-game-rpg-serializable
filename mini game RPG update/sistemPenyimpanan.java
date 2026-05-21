import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class sistemPenyimpanan {
    private static final String NAMA_FILE = "savegame_rpg.dat";
    public static void simpanGame (ArrayList<musuh> dataMonster){
        try {
            FileOutputStream fileOut = new FileOutputStream(NAMA_FILE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(dataMonster);
            objectOut.close();
            fileOut.close();
            System.out.println("[SISTEM] Progress permainan berhasil disimpan!");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menyimpan game: " + e.getMessage());
        }
    }
    public static ArrayList<musuh> loadGame(){
        ArrayList<musuh> dataTermuat = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(NAMA_FILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            dataTermuat = (ArrayList<musuh>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("[SISTEM] Progress permainan berhasil dimuat!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[SISTEM] Tidak ada save data. Memulai game baru.");
        }
        return dataTermuat;
    }
}

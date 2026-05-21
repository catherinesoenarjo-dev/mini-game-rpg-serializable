
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class arenaPertarunganDinamis {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<musuh> gelombangMonster = new ArrayList<>();
        gelombangMonster.add(new slime());
        gelombangMonster.add(new naga());
        gelombangMonster.add(new slime());
        gelombangMonster.add(new zombie());

        System.out.println("=======================================");
        System.out.println("ARENA RPG: GELOMBANG MONSTER");
        System.out.println("=======================================\n");
        System.out.println("AWAS! Sekelompok monster menghadap Anda!");

        boolean isBermain = true;

        while (isBermain && !gelombangMonster.isEmpty()) {
            System.out.println("\n--- STATUS MONSTER ---");
            for (int i = 0; i < gelombangMonster.size(); i++) {
                musuh m = gelombangMonster.get(i);
                System.out.println((i + 1) + ". " + m.namaMusuh + "(HP: " + m.healthPoint + ")");
            }
            System.out.println("------------------------------");
            System.out.println("8. [SAVE GAME] Simpan progress permainan");
            System.out.println("9. [LOAD GAME] Muat progress sebelumnya");
            System.out.println("0. Kabur dari pertarungan");
            System.out.println("\nPilih target monster (1-" + gelombangMonster.size() + ") atau aksi lainnya: ");

            try {
                int pilihanTarget = input.nextInt();

                if (pilihanTarget == 0) {
                    System.out.println("Anda lari terbirit-birit dari arena...");
                    isBermain = false;
                    continue;
                } else if (pilihanTarget == 8) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame_rpg.dat"))) {
                        oos.writeObject(gelombangMonster);
                        System.out.println(">>> BERHASIL: Game berhasil disimpan! <<<");
                    } catch (IOException e) {
                        System.out.println(">>> GAGAL: Terjadi kesalahan saat menyimpan game: " + e.getMessage());
                    }
                    continue;
                } else if (pilihanTarget == 9) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame_rpg.dat"))) {
                        gelombangMonster = (ArrayList<musuh>) ois.readObject();
                        System.out.println(">>> BERHASIL: Game berhasil dimuat! <<<");
                    } catch (FileNotFoundException e) {
                        System.out.println(">>> GAGAL: File save game tidak ada. Silahkan save game terlebih dahulu. <<<");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(">>> GAGAL: Terjadi kesalahan saat membaca file save: " + e.getMessage());
                    }
                    continue;
                }
                if (pilihanTarget < 1 || pilihanTarget > gelombangMonster.size()) {
                    System.out.println("Pilihan tidak valid! Anda membuang giliran.");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;
                musuh target = gelombangMonster.get(indeksMonster);

                System.out.println("Masukkan kekuatan serangan Anda (10-100): ");
                int power = input.nextInt();
                if (power < 10 || power > 100) {
                    throw new seranganTidakValidException("Tindakan Ilegal:Kekuatan serangan harus antara 10 dan 100!");
                }
                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                target.terimaDamage(power);

                if (target.healthPoint <= 0) {
                    System.out.println(target.namaMusuh + " hancur menjadi debu!.");
                    if (target instanceof bisaLoot) {
                        bisaLoot monsterLoot = (bisaLoot) target;
                        monsterLoot.jatuhkanItem();
                    }
                    gelombangMonster.remove(indeksMonster);
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan input, silahkan coba lagi.");
                input.nextLine();
                continue;
            }
            if (gelombangMonster.isEmpty()) {
                System.out.println("\nSELAMAT! Semua monster telah dibersihkan dari arena!");
                break;
            }

            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
            for (int i = 0; i < gelombangMonster.size(); i++) {
                if (gelombangMonster.get(i).healthPoint > 0) {
                    musuh monsterAktif = gelombangMonster.get(i);
                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof bisaTerbang) {
                        System.out.println("[PERINGATAN! SERANGAN UDARA TERDETEKSI]");
                        bisaTerbang monsterTerbang = (bisaTerbang) monsterAktif;
                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();
                    } else {
                        monsterAktif.serangPemain();
                    }
                }
            }

            System.out.println("-----------------------------------------------------------------------------");
            boolean semuaMati = true;
            for (int i = 0; i < gelombangMonster.size(); i++) {
                if (gelombangMonster.get(i).healthPoint > 0) {
                    semuaMati = false;
                    break;
                }
            }
            if (semuaMati) {
                System.out.println("\nSELAMAT! Anda telah menyapu bersih gelombang monster ini.");
                isBermain = false;
            }
        }

        input.close();
        System.out.println("Permainan Berakhir.");
    }
}

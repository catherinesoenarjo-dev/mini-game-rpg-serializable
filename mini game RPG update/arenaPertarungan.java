
import java.util.Scanner;

public class arenaPertarungan {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        musuh[] gelombangMonster = new musuh[3];
        gelombangMonster[0] = new slime();
        gelombangMonster[1] = new naga();
        gelombangMonster[2] = new zombie();

        System.out.println("=======================================");
        System.out.println("ARENA RPG: GELOMBANG MONSTER");
        System.out.println("=======================================\n");
        System.out.println("AWAS! Sekelompok monster menghadap Anda!");

        boolean isBermain = true;

        while (isBermain) {
            System.out.println("\n--- STATUS MONSTER ---");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + "(HP: " + gelombangMonster[i].healthPoint + ")");
                } else {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + " [TEWAS]");
                }
            }
            System.out.println("4. Kabur dari pertarungan");
            System.out.println("\nPilih target monster yang ingin diserang (1/2/3) atau 4 untuk kabur: ");

            try {
                int pilihanTarget = input.nextInt();

                if (pilihanTarget == 4) {
                    System.out.println("Anda lari terbirit-birit dari arena...");
                    isBermain = false;
                    continue;
                }

                if (pilihanTarget < 1 || pilihanTarget > 3) {
                    System.out.println("Pilihan tidak valid! Anda membuang giliran.");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;
                if (gelombangMonster[indeksMonster].healthPoint <= 0) {
                    throw new targetMatiException("Tindakan Ilegal: Anda tidak bisa menyerang monster yang sudah mati!");
                }

                System.out.println("Masukkan kekuatan serangan Anda (10-100): ");
                int power = input.nextInt();
                if (power < 10 || power > 100) {
                    throw new seranganTidakValidException("Tindakan Ilegal:Kekuatan serangan harus antara 10 dan 100!");
                }
                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                gelombangMonster[indeksMonster].terimaDamage(power);

                System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
                for (int i = 0; i < gelombangMonster.length; i++) {
                    if (gelombangMonster[i].healthPoint > 0) {
                        musuh monsterAktif = gelombangMonster[i];
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
                        if (gelombangMonster[i].healthPoint <= 0) {
                            System.out.println(gelombangMonster[indeksMonster].namaMusuh + " berhasil dikalahkan!.");
                        
                            if (gelombangMonster[i] instanceof bisaLoot) {
                                bisaLoot monsterLoot = (bisaLoot) gelombangMonster[indeksMonster];
                                monsterLoot.jatuhkanItem();
                            }
                        }
                    }
                }
                
                catch (java.util.InputMismatchException e) {
                    System.out.println("ERROR INPUT: Anda harus memasukkan ANGKA!");
                    input.nextLine(); 
                }
                catch (targetMatiException e) {
                    System.out.println("KESALAHAN GAME: " + e.getMessage());
                }
                catch (seranganTidakValidException e) {
                    System.out.println("KESALAHAN GAME: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Terjadi kesalahan sistem: " + e.getMessage());
                }
                System.out.println("-----------------------------------------------------------------------------");
                boolean semuaMati = true;
                for (int i = 0; i < gelombangMonster.length; i++) {
                    if (gelombangMonster[i].healthPoint > 0) {
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


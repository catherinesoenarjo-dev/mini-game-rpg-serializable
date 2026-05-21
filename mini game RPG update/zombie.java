public class zombie extends musuh implements bisaLoot {
    public zombie(){
        super("Zombie Brazil", 150);
    }
    @Override
    public void serangPemain(){
        System.out.println(this.namaMusuh + " melompat dan terkena gigitan maut! Player -20 HP");
    }
    @Override
    public void suaraKhas(){
        System.out.println(this.namaMusuh + ": Graphh..Graph..Graphh..");
    }
    @Override
    public void jatuhkanItem(){
        System.out.println(this.namaMusuh + " menjatuhkan zombie tooth yang bisa ditemukan!");
    }
} 

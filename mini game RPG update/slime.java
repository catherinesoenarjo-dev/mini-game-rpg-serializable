public class slime extends musuh implements bisaLoot {
    public slime(){
        super("Slime Asam", 50);
    }
    @Override
    public void serangPemain(){
        System.out.println(this.namaMusuh + " melompat dan menyiram cairan asam! Player -15 HP");
    }
    @Override
    public void suaraKhas(){
        System.out.println(this.namaMusuh + ": bluub..bllub..bluub..");
    }
    @Override
    public void jatuhkanItem(){
        System.out.println(this.namaMusuh + " menjatuhkan slime potion yang bisa ditemukan!");
    }
} 

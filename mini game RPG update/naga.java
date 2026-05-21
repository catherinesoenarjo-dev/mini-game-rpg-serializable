public class naga extends musuh implements bisaTerbang, bisaLoot {
    public naga(){
        super("Naga Bonar", 500);
    }
    @Override
    public void serangPemain(){
        System.out.println(this.namaMusuh + " menyemburkan nafas api di udara! Player -50 HP");
    }
    @Override
    public void suaraKhas(){
        System.out.println(this.namaMusuh + ": ROARRR!!");
    }
    @Override
    public void lepasLandas(){
        System.out.println(this.namaMusuh + " terbang tinggi! Sulit diserang.");
    }
    @Override
    public void seranganUdara(){
        System.out.println(this.namaMusuh + " menyemburkan badai api! Player -80 HP");
    }
    @Override
    public void jatuhkanItem(){
        System.out.println(this.namaMusuh + " menjatuhkan naga scale yang bisa ditemukan!");
    }
}

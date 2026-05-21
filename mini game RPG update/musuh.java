import java.io.Serializable;
public abstract class musuh implements Serializable {

    protected String namaMusuh;
    protected int healthPoint;

    public musuh(String nama, int hp) {
        this.namaMusuh = nama;
        this.healthPoint = hp;
    }

    public void terimaDamage(int damage) {
        this.healthPoint -= damage;
        System.out.println(this.namaMusuh + " terkena serangan biasa. Sisa HP: " + this.healthPoint);
    }

    public abstract void serangPemain();
    public abstract void suaraKhas();
}

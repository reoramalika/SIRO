package com.ramalika.siro;

/**
 * Created by Reo Ramalika_2 on 29/04/2017.
 */

public class KecerdasanEntity {
    private int ID;
    private String namaKecerdasan;
    private float persentase;
    private int jumlah;

    public KecerdasanEntity(){
        this.jumlah=0;
        this.persentase=0.0f;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNamaKecerdasan() {
        return namaKecerdasan;
    }

    public void setNamaKecerdasan(String namaKecerdasan) {
        this.namaKecerdasan = namaKecerdasan;
    }

    public float getPersentase() {
        return persentase;
    }

    public void setPersentase(float persentase) {
        this.persentase = persentase;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}

package com.ramalika.siro;

/**
 * Created by Reo Ramalika_2 on 28/04/2017.
 */

public class CiriEntity {
    private int id;
    private String namaCiri;
    private String kategori;
    private boolean isChecked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaCiri() {
        return namaCiri;
    }

    public void setNamaCiri(String namaCiri) {
        this.namaCiri = namaCiri;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}

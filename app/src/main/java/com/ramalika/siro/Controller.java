package com.ramalika.siro;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reo Ramalika_2 on 28/04/2017.
 */

public class Controller {
    private DAO dao;
    Context context;

    public Controller(Context context){
        this.context=context;
    }

    private void openDB(){
        dao=new DAO(context);
        try{
            dao.createDataBase();
            dao.openDataBase();
        }
        catch (IOException ex){
            throw new Error("Unable to create database");
        }
        catch (SQLException ex){
            throw ex;
        }
    }

    public ArrayList<KecerdasanEntity> getListResultKecerdasan(List<CiriEntity> listCiri){
        //fungsi belum efisien
        //max id kecerdasan dan jumlah id tidak dapat dipastikan selalu sama
        ArrayList<KecerdasanEntity> rs=new ArrayList<>();

        ArrayList<KecerdasanEntity> listKecerdasan=dao.getAllKecerdasan();
        KecerdasanEntity arrayKecerdasan[]=new KecerdasanEntity[listKecerdasan.size()];
        for(int i=0;i<listKecerdasan.size();i++){
            arrayKecerdasan[i]=listKecerdasan.get(i);
        }

        for(int i=0;i<listCiri.size();i++){
            String tempKategori;
            tempKategori=listCiri.get(i).getKategori();
            int index;
            for(int j=0;j<(tempKategori.length()/2)+1;j++){
                index=Integer.parseInt(tempKategori.substring(j*2,j*2+1));
                arrayKecerdasan[index].setJumlah(arrayKecerdasan[index].getJumlah()+1);
            }
        }

        for(int i=0;i<listKecerdasan.size();i++){
            if(listKecerdasan.get(i).getJumlah()>0){
                arrayKecerdasan[i].setPersentase((float) arrayKecerdasan[i].getJumlah()/listKecerdasan.size());
                rs.add(arrayKecerdasan[i]);
            }
        }
        return rs;
    }

    public ArrayList<String> getListResultCaraBelajar(KecerdasanEntity kecerdasan){
        ArrayList<String> rs;
        rs=dao.getCaraBelajar(kecerdasan.getID());

        return rs;
    }

    public ArrayList<String> getListResultKarir(KecerdasanEntity kecerdasan){
        ArrayList<String> rs;
        rs=dao.getKarir(kecerdasan.getID());

        return rs;
    }

    public void init(){
        openDB();

        //initKecerdasan
        KecerdasanEntity kecerdasan=new KecerdasanEntity();

        kecerdasan.setID(0);
        kecerdasan.setNamaKecerdasan("Kecerdasan Verbal (Verbal Linguistik)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(1);
        kecerdasan.setNamaKecerdasan("Kecerdasan Matematis-Logis (Loghical Mathematical)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(2);
        kecerdasan.setNamaKecerdasan("Kecerdasan Visual-Spasial (Visual Spatial)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(3);
        kecerdasan.setNamaKecerdasan("Kecerdasan Musikal (Musical)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(4);
        kecerdasan.setNamaKecerdasan("Kecerdasan Kinestetis (Bodily Kinesthetic)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(5);
        kecerdasan.setNamaKecerdasan("Kecerdasan Naturalis (Bodily Naturalistic)");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(6);
        kecerdasan.setNamaKecerdasan("Kecerdasan Sosial");
        dao.insertKecerdasan(kecerdasan);

        kecerdasan.setID(7);
        kecerdasan.setNamaKecerdasan("Kecerdasan Intrapersonal");
        dao.insertKecerdasan(kecerdasan);


        //initCiri
        CiriEntity ciri=new CiriEntity();
        int idCiri=0;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mampu mengekspresikan pikirannya dengan baik");
        ciri.setKategori("0");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mampu menuliskan pengalaman dengan kreatif");
        ciri.setKategori("0");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Gemar bercerita imajinasi dengan indah");
        ciri.setKategori("0");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Daya ingat kuat & mudah menghafal hal-hal baru");
        ciri.setKategori("0");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Suka bertanya sebab-akibat tentang berbagai hal");
        ciri.setKategori("1");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mampu menjelaskan masalah dengan logis");
        ciri.setKategori("1");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Suka bereksperimen untuk membuat kesimpulan atau menjawab dugaan");
        ciri.setKategori("1");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Menyukai perminan teka-teki/puzle");
        ciri.setKategori("1");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Suka Bermain dengan bentuk-bentuk");
        ciri.setKategori("2");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Tak banyak bicara, namun gemar coret-coret");
        ciri.setKategori("2");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Pandai menyelesaikan masalah");
        ciri.setKategori("2");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mampu memprediksi apa yang akan terjadi dengan memperhatikan pola kejadian");
        ciri.setKategori("2");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Sensitif terhadap nada dan irama serta kekuatan emosi musik");
        ciri.setKategori("3");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Faham susunan nada yang rumit");
        ciri.setKategori("3");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Sangat menikmati kegiatan fisik seperti berlari, melompat, atau memanjat");
        ciri.setKategori("4");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Memiliki kontrol tubuh yang baik serta reflek yang sempurna");
        ciri.setKategori("4");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mudah mengingat apa yang dilakukan oleh ornag lain");
        ciri.setKategori("4");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Sangat menikmati saat berada di alam terbuka");
        ciri.setKategori("5");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mudah mengingat ciri hewan dan tumbuhan");
        ciri.setKategori("5");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Sangat tertarik dengan hal yang berkaitan dengan alam");
        ciri.setKategori("5");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mudah mengingat informasi yang berkaitan dengan alam");
        ciri.setKategori("5");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Pandai bernegosiasi");
        ciri.setKategori("6");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mahir bersosialisasi");
        ciri.setKategori("6");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Menikmati ketika berada di tengah orang banyak");
        ciri.setKategori("6");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Pandai membaca situasi dengan baik");
        ciri.setKategori("6");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Memiliki rasa percaya diri yang tinggi");
        ciri.setKategori("7");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Sangat mandiri");
        ciri.setKategori("7");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Mampu untuk fokus");
        ciri.setKategori("7");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Ingin berbeda dari kebanyakan orang");
        ciri.setKategori("7");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        ciri.setId(idCiri);
        ciri.setNamaCiri("Lebih suka bekerja sendiri");
        ciri.setKategori("7");
        ciri.setChecked(false);
        dao.insertCiri(ciri);
        idCiri++;

        //initCaraBelajar
        CaraBelajarEntity belajar=new CaraBelajarEntity();

        belajar.setID(0);
        belajar.setNamaCaraBelajar("Membaca materi pelajaran");
        belajar.setKategori("0");
        dao.insertCaraBelajar(belajar);

        belajar.setID(1);
        belajar.setNamaCaraBelajar("Membuat rangkuman");
        belajar.setKategori("0");
        dao.insertCaraBelajar(belajar);

        belajar.setID(2);
        belajar.setNamaCaraBelajar("Melakukan permainan kata");
        belajar.setKategori("0");
        dao.insertCaraBelajar(belajar);

        belajar.setID(3);
        belajar.setNamaCaraBelajar("Melakukan kegiatan eksperimen dengan mengamati seuatu kejadian dan membuat kesimpulan atau memprediksi kejadian selanjutnya");
        belajar.setKategori("1");
        dao.insertCaraBelajar(belajar);

        belajar.setID(4);
        belajar.setNamaCaraBelajar("Melatih berhitung");
        belajar.setKategori("1");
        dao.insertCaraBelajar(belajar);

        belajar.setID(5);
        belajar.setNamaCaraBelajar("Memahami cara kerja perangkat elektronik");
        belajar.setKategori("1");
        dao.insertCaraBelajar(belajar);

        belajar.setID(6);
        belajar.setNamaCaraBelajar("Membuat mind mapping");
        belajar.setKategori("2");
        dao.insertCaraBelajar(belajar);

        belajar.setID(7);
        belajar.setNamaCaraBelajar("Menonton film, slide, atau foto");
        belajar.setKategori("2");
        dao.insertCaraBelajar(belajar);

        belajar.setID(8);
        belajar.setNamaCaraBelajar("Menggambar atau mencoret coret untuk mengingat sesuatu");
        belajar.setKategori("2");
        dao.insertCaraBelajar(belajar);

        belajar.setID(9);
        belajar.setNamaCaraBelajar("Membaca puisi dengan menggunakan intonasi");
        belajar.setKategori("3");
        dao.insertCaraBelajar(belajar);

        belajar.setID(10);
        belajar.setNamaCaraBelajar("Menyanyikan lagu");
        belajar.setKategori("3");
        dao.insertCaraBelajar(belajar);

        belajar.setID(11);
        belajar.setNamaCaraBelajar("Berikan iringan musik klasik saat belajar");
        belajar.setKategori("3");
        dao.insertCaraBelajar(belajar);

        belajar.setID(12);
        belajar.setNamaCaraBelajar("Belajar dengan melibatkan fisik");
        belajar.setKategori("4");
        dao.insertCaraBelajar(belajar);

        belajar.setID(13);
        belajar.setNamaCaraBelajar("Mendramatisasikan proses belajar");
        belajar.setKategori("4");
        dao.insertCaraBelajar(belajar);

        belajar.setID(14);
        belajar.setNamaCaraBelajar("Belajar di luar ruangan dan mempraktekan langung hal yang dipelajari");
        belajar.setKategori("4");
        dao.insertCaraBelajar(belajar);

        belajar.setID(15);
        belajar.setNamaCaraBelajar("Menijinkan untuk memeihara hewan peliharaan");
        belajar.setKategori("5");
        dao.insertCaraBelajar(belajar);

        belajar.setID(16);
        belajar.setNamaCaraBelajar("Belajar atau bermain di udara terbuka");
        belajar.setKategori("5");
        dao.insertCaraBelajar(belajar);

        belajar.setID(17);
        belajar.setNamaCaraBelajar("Belajar kelompok");
        belajar.setKategori("6");
        dao.insertCaraBelajar(belajar);

        belajar.setID(18);
        belajar.setNamaCaraBelajar("Memberikan ruang untuk bersosialisasi dengan teman-temannya");
        belajar.setKategori("6");
        dao.insertCaraBelajar(belajar);

        belajar.setID(19);
        belajar.setNamaCaraBelajar("Membangun komunikasi dengan membuatkan daftar pertanyaan untuk mengajarkan sebab-akibat suatu kejadian");
        belajar.setKategori("6");
        dao.insertCaraBelajar(belajar);

        belajar.setID(20);
        belajar.setNamaCaraBelajar("Berikan ruangan sendiri untuk belajar");
        belajar.setKategori("7");
        dao.insertCaraBelajar(belajar);

        belajar.setID(21);
        belajar.setNamaCaraBelajar("Mengajarkan untuk membuat target");
        belajar.setKategori("7");
        dao.insertCaraBelajar(belajar);

        belajar.setID(22);
        belajar.setNamaCaraBelajar("Mengajarkan untuk membuat jurnal hidup pribadi");
        belajar.setKategori("7");
        dao.insertCaraBelajar(belajar);

        //initKarir
        KarirEntity karir=new KarirEntity();

        karir.setID(0);
        karir.setNamaKarir("Penulis");
        karir.setKategori("0,7");
        dao.insertKarir(karir);

        karir.setID(1);
        karir.setNamaKarir("Editor");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(2);
        karir.setNamaKarir("Jurnalis");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(3);
        karir.setNamaKarir("Guru");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(4);
        karir.setNamaKarir("Penerjemah");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(5);
        karir.setNamaKarir("Pengacara");
        karir.setKategori("0,1");
        dao.insertKarir(karir);

        karir.setID(6);
        karir.setNamaKarir("Reporter");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(7);
        karir.setNamaKarir("Public Relations");
        karir.setKategori("0,6");
        dao.insertKarir(karir);

        karir.setID(8);
        karir.setNamaKarir("Manajer");
        karir.setKategori("0");
        dao.insertKarir(karir);

        karir.setID(9);
        karir.setNamaKarir("Analis");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(10);
        karir.setNamaKarir("Akuntan");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(11);
        karir.setNamaKarir("Dokter");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(12);
        karir.setNamaKarir("Ilmuwan");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(13);
        karir.setNamaKarir("Programmer");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(14);
        karir.setNamaKarir("Researcher");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(15);
        karir.setNamaKarir("Banker");
        karir.setKategori("1");
        dao.insertKarir(karir);

        karir.setID(16);
        karir.setNamaKarir("Arsitek");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(17);
        karir.setNamaKarir("Pilot");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(18);
        karir.setNamaKarir("Desainer");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(19);
        karir.setNamaKarir("Seniman");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(20);
        karir.setNamaKarir("Fotografer");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(21);
        karir.setNamaKarir("Animator");
        karir.setKategori("2");
        dao.insertKarir(karir);

        karir.setID(22);
        karir.setNamaKarir("Komposer");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(23);
        karir.setNamaKarir("Penyanyi");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(24);
        karir.setNamaKarir("Pemain sandiwara");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(25);
        karir.setNamaKarir("Penata rekaman");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(26);
        karir.setNamaKarir("Penyelaras instrumen musik");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(27);
        karir.setNamaKarir("Pembuat alat musik");
        karir.setKategori("3");
        dao.insertKarir(karir);

        karir.setID(28);
        karir.setNamaKarir("Penari");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(29);
        karir.setNamaKarir("Atlet");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(30);
        karir.setNamaKarir("Aktor");
        karir.setKategori("4,6");
        dao.insertKarir(karir);

        karir.setID(31);
        karir.setNamaKarir("Ahli mimik");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(32);
        karir.setNamaKarir("Ahli bedah");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(33);
        karir.setNamaKarir("Pembalap");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(34);
        karir.setNamaKarir("Pekerja lapangan");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(35);
        karir.setNamaKarir("Perakit mesin");
        karir.setKategori("4");
        dao.insertKarir(karir);

        karir.setID(36);
        karir.setNamaKarir("Plantologis");
        karir.setKategori("5");
        dao.insertKarir(karir);

        karir.setID(37);
        karir.setNamaKarir("Petani");
        karir.setKategori("5");
        dao.insertKarir(karir);

        karir.setID(38);
        karir.setNamaKarir("Peneliti hewan dan tumbuhan");
        karir.setKategori("5");
        dao.insertKarir(karir);

        karir.setID(39);
        karir.setNamaKarir("Konselor");
        karir.setKategori("6");
        dao.insertKarir(karir);

        karir.setID(40);
        karir.setNamaKarir("Marketing");
        karir.setKategori("6");
        dao.insertKarir(karir);

        karir.setID(41);
        karir.setNamaKarir("Pekerja sosial");
        karir.setKategori("6");
        dao.insertKarir(karir);

        karir.setID(42);
        karir.setNamaKarir("Terapis");
        karir.setKategori("6");
        dao.insertKarir(karir);

        karir.setID(43);
        karir.setNamaKarir("Trainer");
        karir.setKategori("7");
        dao.insertKarir(karir);

        karir.setID(44);
        karir.setNamaKarir("Wiraswasta");
        karir.setKategori("7");
        dao.insertKarir(karir);

        karir.setID(45);
        karir.setNamaKarir("Psikolog");
        karir.setKategori("7");
        dao.insertKarir(karir);

        karir.setID(46);
        karir.setNamaKarir("Pemimpin agama");
        karir.setKategori("7");
        dao.insertKarir(karir);

        dao.close();
    }
}
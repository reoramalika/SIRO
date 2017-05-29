package com.ramalika.siro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Reo Ramalika_2 on 28/04/2017.
 */

public class DAO extends SQLiteOpenHelper{
    public static final String dbName="dbSIRO";
    public static final int DB_VERSION=1;
    private static String DB_PATH = "/data/data/com.ramalika.siro/databases/";
    private final Context myContext;
    SQLiteDatabase sqliteDB;

    //CiriEntity
    public final String tblCiri="tblCiri";
    public final String colIDCiri="IDCiri";
    public final String colNamaCiri="NamaCiri";
    public final String colKategoriCiri="KategoriCiri";

    //KecerdasanEntity
    public final String tblKecerdasan="tblKecerdasan";
    public final String colIDKecerdasan="IDKecerdasan";
    public final String colNamaKecerdasan="NamaKecerdasan";

    //CaraBelajarEntity
    public final String tblCaraBelajar ="tblCaraBelajar";
    public final String colIDBelajar ="IDCaraBelajar";
    public final String colNamaBelajar ="NamaCaraBelajar";
    public final String colKategoriBelajar ="KategoriCaraBelajar";

    //KarirEntity
    public final String tblKarir ="tblKarir";
    public final String colIDKarir ="IDKarir";
    public final String colNamaKarir ="NamaKarir";
    public final String colKategoriKarir ="KategoriKarir";

    public DAO(Context context) {
        super(context, dbName,null, 1);
        this.myContext = context;
        sqliteDB=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + tblKecerdasan + "(" + colIDKecerdasan + " INTEGER PRIMARY KEY," + colNamaKecerdasan + " TEXT);");
            db.execSQL("CREATE TABLE " + tblCiri + "(" + colIDCiri + " INTEGER PRIMARY KEY," + colNamaCiri + " TEXT," + colKategoriCiri + " TEXT);");
            db.execSQL("CREATE TABLE " + tblCaraBelajar + "(" + colIDBelajar + " INTEGER PRIMARY KEY," + colNamaBelajar + " TEXT," + colKategoriBelajar + " TEXT);");
            db.execSQL("CREATE TABLE " + tblKarir + "(" + colIDKarir + " INTEGER PRIMARY KEY," + colNamaKarir + " TEXT," + colKategoriKarir + " TEXT);");
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    }

    public void insertKecerdasan(KecerdasanEntity value){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put(colIDKecerdasan,value.getID());
            contentValues.put(colNamaKecerdasan,value.getNamaKecerdasan());
            Log.d("SUCCED INSERT", "Insert kecerdasan done!");
            this.getReadableDatabase().insert(tblKecerdasan, null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.getReadableDatabase().close();
            close();
        }
    }

    public void insertCiri(CiriEntity value){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put(colIDCiri, value.getId());
            contentValues.put(colNamaCiri, value.getNamaCiri());
            contentValues.put(colKategoriCiri,value.getKategori());
            Log.d("SUCCED INSERT", "Insert ciri done!");
            this.getReadableDatabase().insert(tblCiri, null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.getReadableDatabase().close();
            close();
        }
    }

    public void insertCaraBelajar(CaraBelajarEntity value){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put(colIDBelajar,value.getID());
            contentValues.put(colNamaBelajar,value.getNamaCaraBelajar());
            contentValues.put(colKategoriBelajar, value.getKategori());
            Log.d("SUCCED INSERT", "Insert cara belajar done!");
            this.getReadableDatabase().insert(tblCaraBelajar, null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.getReadableDatabase().close();
            close();
        }
    }

    public void insertKarir(KarirEntity value){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put(colIDKarir,value.getID());
            contentValues.put(colNamaKarir,value.getNamaKarir());
            contentValues.put(colKategoriKarir, value.getKategori());
            Log.d("SUCCED INSERT", "Insert karir done!");
            this.getReadableDatabase().insert(tblKarir, null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.getReadableDatabase().close();
            close();
        }
    }

    public String getNamaKecerdasan(int ID){
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblKecerdasan+" where "+colIDKecerdasan+"="+ID,null);
        while (cursor.moveToNext()){
            if (cursor.getInt(cursor.getColumnIndex(colIDKecerdasan))==ID)
                return cursor.getString(cursor.getColumnIndex(colNamaKecerdasan));
        }

        return "Tidak ditemukan";
    }

    public int getIDKecerdasan(String nama){
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblKecerdasan+" where "+colNamaKecerdasan+"='"+nama+"'",null);
        while (cursor.moveToNext()){
            //if (cursor.getString(cursor.getColumnIndex(colNamaKecerdasan))==nama)
                return cursor.getInt(cursor.getColumnIndex(colIDKecerdasan));
        }

        return -1;
    }

    public ArrayList<KecerdasanEntity> getAllKecerdasan(){
        ArrayList<KecerdasanEntity> result=new ArrayList<KecerdasanEntity>();
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblKecerdasan,null);
        while(cursor.moveToNext()){
            KecerdasanEntity temp=new KecerdasanEntity();
            temp.setID(cursor.getInt(cursor.getColumnIndex(colIDKecerdasan)));
            temp.setNamaKecerdasan(cursor.getString(cursor.getColumnIndex(colNamaKecerdasan)));
            result.add(temp);
        }
        return result;
    }

    public ArrayList<CiriEntity> getAllCiri(){
        ArrayList<CiriEntity> result=new ArrayList<CiriEntity>();
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblCiri,null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            CiriEntity temp=new CiriEntity();
            temp.setId(cursor.getInt(cursor.getColumnIndex(colIDCiri)));
            temp.setNamaCiri(cursor.getString(cursor.getColumnIndex(colNamaCiri)));
            temp.setKategori(cursor.getString(cursor.getColumnIndex(colKategoriCiri)));
            temp.setChecked(false);
            result.add(temp);
        }
        cursor.close();
        return result;
    }

    public ArrayList<String> getCaraBelajar(int kategori){
        ArrayList<String> result=new ArrayList<String>();
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblCaraBelajar+" where "+colKategoriBelajar+" like '%"+kategori+"%'",null);
        while (cursor.moveToNext()){
            result.add(cursor.getString(cursor.getColumnIndex(colNamaBelajar)));
        }
        return result;
    }

    public ArrayList<String> getKarir(int kategori){
        ArrayList<String> result=new ArrayList<String>();
        Cursor cursor=getReadableDatabase()
                .rawQuery("select * from "+tblKarir+" where "+colKategoriKarir+" like '%"+kategori+"$'",null);
        while (cursor.moveToNext()){
            result.add(cursor.getString(cursor.getColumnIndex(colNamaKarir)));
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();
                Log.d("Success create","Success creating database");

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + dbName;

            File file = new File(myPath);
            if (file.exists() && !file.isDirectory())
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);


        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
        /*File dbFile = myContext.getDatabasePath(DB_NAME);
        return dbFile.exists();*/
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(dbName);


        // Path to the just created empty db
        String outFileName = DB_PATH + dbName;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + dbName;
        sqliteDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Log.d("Success Open", "Success opening database");
    }

    @Override
    public synchronized void close() {
        if(sqliteDB != null)
            sqliteDB.close();

        super.close();
    }
}
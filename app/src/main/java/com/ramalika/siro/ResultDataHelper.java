package com.ramalika.siro;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Reo Ramalika_2 on 29/05/2017.
 */

public class ResultDataHelper implements Serializable,Parcelable {
    private ArrayList<KecerdasanEntity> rs;
    int id;

    ResultDataHelper(ArrayList<KecerdasanEntity> value){
        this.rs=value;
    }

    protected ResultDataHelper(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<ResultDataHelper> CREATOR = new Creator<ResultDataHelper>() {
        @Override
        public ResultDataHelper createFromParcel(Parcel in) {
            return new ResultDataHelper(in);
        }

        @Override
        public ResultDataHelper[] newArray(int size) {
            return new ResultDataHelper[size];
        }
    };

    public ArrayList<KecerdasanEntity> getRs() {
        return rs;
    }

    public void setRs(ArrayList<KecerdasanEntity> rs) {
        this.rs = rs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeInt(id);
    }
}

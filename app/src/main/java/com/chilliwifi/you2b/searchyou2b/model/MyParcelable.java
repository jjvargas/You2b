package com.chilliwifi.you2b.searchyou2b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MyParcelable implements Parcelable {

    private ArrayList<Items> list;

    public MyParcelable(ArrayList<Items> list){
        this.list = list;
    }

    protected MyParcelable(Parcel in) {
    }

    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
    }

    public ArrayList<Items> writeFromParcel(){
        return list;
    }
}

package com.example.mediaplayer.Helpers;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Song implements Parcelable {
    private String name;
    private String path;
    private int length;

    public Song(String name, String path, int length) {
        this.name = name;
        this.path = path;
        this.length = length;
    }

    protected Song(Parcel in) {
        name = in.readString();
        path = in.readString();
        length = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getShortName(){
        if(name.length() < 30)
            return name;
        else
            return name.substring(0, 30);
    }

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
        dest.writeInt(length);
    }
}

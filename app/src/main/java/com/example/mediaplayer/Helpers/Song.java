package com.example.mediaplayer.Helpers;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Song implements Parcelable {
    private String name;
    private Uri uri;
    private int length;

    public Song(String name, Uri uri, int length) {
        this.name = name;
        this.uri = uri;
        this.length = length;
    }


    protected Song(Parcel in) {
        name = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
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
                ", uri='" + uri + '\'' +
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
        Uri.writeToParcel(dest, uri);
        dest.writeInt(length);
    }
}

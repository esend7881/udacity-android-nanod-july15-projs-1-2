package com.ericsender.android_nanodegree.popmovie.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by g56147 on 9/15/2015.
 */
public class TrailerListObj implements Parcelable, Serializable {
    public final String youtube_key;
    public final String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrailerListObj that = (TrailerListObj) o;

        if (youtube_key != null ? !youtube_key.equals(that.youtube_key) : that.youtube_key != null)
            return false;
        return !(title != null ? !title.equals(that.title) : that.title != null);

    }

    @Override
    public int hashCode() {
        int result = youtube_key != null ? youtube_key.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    protected TrailerListObj(Parcel in) {

        youtube_key = in.readString();
        title = in.readString();
    }

    public TrailerListObj(String youtube_key, String title) {
        this.youtube_key = youtube_key;
        this.title = title;
    }

    public static final Creator<TrailerListObj> CREATOR = new Creator<TrailerListObj>() {
        @Override
        public TrailerListObj createFromParcel(Parcel in) {
            return new TrailerListObj(in);
        }

        @Override
        public TrailerListObj[] newArray(int size) {
            return new TrailerListObj[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(youtube_key);
        dest.writeString(title);
    }

    @Override
    public String toString() {
        return "TrailerListObj{" +
                "title='" + title + '\'' +
                ", youtube_key='" + youtube_key + '\'' +
                '}';
    }
}
package com.example.devilapplication.recylerview.form;

import android.os.Parcel;
import android.os.Parcelable;

public class FormData implements Parcelable {
    private String name;
    private String email;

    public FormData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected FormData(Parcel in) {
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<FormData> CREATOR = new Creator<FormData>() {
        @Override
        public FormData createFromParcel(Parcel in) {
            return new FormData(in);
        }

        @Override
        public FormData[] newArray(int size) {
            return new FormData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
    }
}

package com.example.notev22.note;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String name;
    private int idNote;

    public Note(String name, int idMessage) {
        this.name = name;
        this.idNote = idMessage;
    }

    protected Note(Parcel in) {
        name = in.readString();
        idNote = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdMessage() {
        return idNote;
    }

    public void setIdMessage(int idMessage) {
        this.idNote = idMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(idNote);
    }
}

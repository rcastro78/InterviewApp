package com.applaudostudios.nfl12bars.models;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by RafaelCastro on 28/5/15.
 */
public class BarVenue implements Parcelable {

    private int id;
    private String name;
    private String address;

    private String imageUrl;
    private String schedules;


    public BarVenue(int id, String name, String address, String imageUrl,String schedules) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.schedules = schedules;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(imageUrl);
        dest.writeString(schedules);

    }


    public static final Parcelable.Creator<BarVenue> CREATOR
            = new Parcelable.Creator<BarVenue>() {
        public BarVenue createFromParcel(Parcel in) {
            return new BarVenue(in);
        }
        public BarVenue[] newArray(int size) {
            return new BarVenue[size];
        }
    };
    private BarVenue(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        imageUrl = in.readString();
        schedules = in.readString();

    }

}

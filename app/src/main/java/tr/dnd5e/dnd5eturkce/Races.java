package tr.dnd5e.dnd5eturkce;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Races implements Comparable<Races>, Parcelable{

    private String name;
    private String description;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Races createFromParcel(Parcel in) {
            return new Races(in);
        }

        public Races[] newArray(int size) {
            return new Races[size];
        }
    };


    public Races(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Races(Parcel in) {
        this.setName(in.readString());
        this.setDescription(in.readString());
    }


    @Override
    public int compareTo(@NonNull Races o) {
        return 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getDescription());
    }
}

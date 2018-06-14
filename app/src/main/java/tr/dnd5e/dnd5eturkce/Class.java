package tr.dnd5e.dnd5eturkce;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Class implements Comparable<Class>, Parcelable{

    private String name;
    private String description;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Class createFromParcel(Parcel in) {
            return new Class(in);
        }

        public Class[] newArray(int size) {
            return new Class[size];
        }
    };


    public Class(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Class(Parcel in) {
        this.setName(in.readString());
        this.setDescription(in.readString());
    }


    @Override
    public int compareTo(@NonNull Class o) {
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

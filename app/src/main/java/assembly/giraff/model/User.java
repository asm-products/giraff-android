package assembly.giraff.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String userName;
    private int userFavedGifs;
    private Boolean isUpgraded;

    public User(String name, int muserFavedGifs) {
        userName = name;
        userFavedGifs = muserFavedGifs;
        this.isUpgraded = false;
    }

    public User(Parcel parcel) {
        userName = parcel.readString();
        userFavedGifs = Integer.valueOf(parcel.readString());
    }

    public String getUserName() {
        return userName;
    }

    public int getUserFavedGifs() {
        return userFavedGifs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(String.valueOf(userFavedGifs));
        dest.writeString(String.valueOf(isUpgraded));
    }

    public static final Creator<User> CREATOR
            = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Boolean IsUpgraded() {
        return isUpgraded;
    }

    public void setIsUpgraded(Boolean isUpgraded) {
        this.isUpgraded = isUpgraded;
    }
}

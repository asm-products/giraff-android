package assembly.giraff.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String userName;
    private String userLevel;

    public User(String name, String level) {
        userName = name;
        userLevel = level;
    }

    public User(Parcel parcel) {
        userName = parcel.readString();
        userLevel = parcel.readString();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userLevel);
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
}

package mubbi.saveme.contact_list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdidone on 04/06/2015.
 */
public class Contact implements Parcelable{

    private String name;
    private String phone;
    private String imagePath;

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){

        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[0];
        }
    };

    private Contact(Parcel in){
        this.name = in.readString();
        this.phone = in.readString();
        this.imagePath = in.readString();
    }

    public Contact(String name, String phone, String image) {
        this.name = name;
        this.phone = phone;
        this.imagePath = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.getPhone());
        dest.writeString(this.imagePath);
    }
}

package mubbi.saveme.contact_list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdidone on 04/06/2015.
 */
public class Contact implements Parcelable{

    private String id;
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
        this.id = in.readString();
        this.name = in.readString();
        this.phone = in.readString();
        this.imagePath = in.readString();
    }

    public Contact(String id, String name, String phone, String image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.imagePath = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
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
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.getPhone());
        dest.writeString(this.imagePath);
    }
}

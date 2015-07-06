package mubbi.saveme.configuration;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import mubbi.saveme.contact_list.Contact;

/**
 * Created by pdidone on 16/06/2015.
 */
public class Advice implements Parcelable{

    private int id;
    private String title;
    private int delay;
    private ArrayList<Contact> contactList;

    public static final Creator<Advice> CREATOR = new Creator<Advice>(){

        @Override
        public Advice createFromParcel(Parcel parcel) {
            return new Advice(parcel);
        }

        @Override
        public Advice[] newArray(int size) {
            return new Advice[0];
        }
    };

    public Advice(int id, String title, int delay, ArrayList<Contact> contactList) {
        this.id = id;
        this.title = title;
        this.delay = delay;
        this.contactList = contactList;
    }

    private Advice(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.delay = in.readInt();
        contactList = new ArrayList<>();
        in.readTypedList(contactList, Contact.CREATOR);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDelay() {
        return delay;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.delay);
        dest.writeTypedList(this.contactList);
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", delay=" + delay +
                ", contactList=" + contactList +
                '}';
    }
}

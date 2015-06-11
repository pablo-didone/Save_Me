package mubbi.saveme.contact_list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdidone on 09/06/2015.
 */
public class ContactRow implements Parcelable{

    private Contact contact;
    private boolean checked;

    public static final Parcelable.Creator<ContactRow> CREATOR = new Creator<ContactRow>() {

        @Override
        public ContactRow[] newArray(int size) {
            return new ContactRow[size];
        }

        @Override
        public ContactRow createFromParcel(Parcel arg0) {
            return new ContactRow(arg0);
        }
    };

    private ContactRow(Parcel in){
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.checked = in.readByte() == 1 ? true : false;
    }

    public ContactRow(Contact contact){
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(contact, flags);
        dest.writeByte((byte) (checked == true ? 1 : 0));
    }

}

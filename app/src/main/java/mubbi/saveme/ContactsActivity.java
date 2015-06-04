package mubbi.saveme;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by pdidone on 04/06/2015.
 */
public class ContactsActivity extends ListActivity {

    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_contacts);

        //Get contacts
        contacts = new ArrayList<>();
        getContactsFromPhone();

        //Set adapter
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        setListAdapter(adapter);
    }

    //Load contacts data and save in ArrayList
    private void getContactsFromPhone(){
        Contact contact = new Contact("Pepe Argento", "+54 123456789");

        for (int i = 0; i < 20; i++){
            contacts.add(contact);
        }
    }
}

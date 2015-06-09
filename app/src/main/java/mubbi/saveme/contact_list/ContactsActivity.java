package mubbi.saveme.contact_list;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mubbi.saveme.R;

/**
 * Created by pdidone on 04/06/2015.
 */
public class ContactsActivity extends ListActivity {

    private ArrayList<Contact> contacts;
    private ArrayList<ContactRow> contactRows;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            contactRows = savedInstanceState.getParcelableArrayList("ROWLIST");
        }else{
            //Get contacts
            contacts = new ArrayList<>();
            getContactsFromPhone();

            //Convert to
            contactRows = new ArrayList<>();
            convertToRowList(contacts);
        }

        //Set layout
        setContentView(R.layout.activity_contacts);

        //Set adapter
        adapter = new ContactsAdapter(this, contactRows);
        setListAdapter(adapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("ROWLIST", this.contactRows);
    }

    //Load contacts data and save in ArrayList
    private void getContactsFromPhone(){
        Contact contact;

        for (int i = 0; i < 20; i++){
            contact = new Contact("Pepe Argento " + i, "+54 123456789", null);
            contacts.add(contact);
        }
    }

    //Set contact list to contact row (with check)
    private void convertToRowList(ArrayList<Contact> dataIn){
        ContactRow row;
        for (int i = 0; i < dataIn.size(); i++){
            row = new ContactRow(dataIn.get(i));
            this.contactRows.add(row);
        }
    }
}

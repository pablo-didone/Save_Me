package mubbi.saveme;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContactsActivity.this, contacts.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Load contacts data and save in ArrayList
    private void getContactsFromPhone(){
        Contact contact;

        for (int i = 0; i < 20; i++){
            contact = new Contact("Pepe Argento " + i, "+54 123456789");
            contacts.add(contact);
        }
    }
}

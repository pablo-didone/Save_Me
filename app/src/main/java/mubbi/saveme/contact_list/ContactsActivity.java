package mubbi.saveme.contact_list;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

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

        //Button return contacts
        Button btnReturnContacts = (Button)findViewById(R.id.btnReturnContactList);
        btnReturnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacts.clear();
                for (int i = 0; i < contactRows.size(); i++){
                    if (contactRows.get(i).isChecked()){
                        contacts.add(contactRows.get(i).getContact());
                    }
                }
                Intent i = new Intent();
                i.putParcelableArrayListExtra("CONTACT_LIST",contacts);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("ROWLIST", this.contactRows);
    }

    //Load contacts data and save in ArrayList
    private void getContactsFromPhone(){

        String[] projection = {
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.Data.HAS_PHONE_NUMBER
        };

        String clause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +
                "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        String order_criteria = ContactsContract.Data.DISPLAY_NAME_PRIMARY + " ASC";

        Cursor contactsCursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,  //Contacts URI
                projection,
                clause,
                null,
                order_criteria
        );

        if(contactsCursor.moveToFirst()){
            String contactName;
            String contactPhone;
            String hasPhoneNumber;

            int columnName = contactsCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME_PRIMARY);
            int columnPhone = contactsCursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER);
            int columnHasNumber = contactsCursor.getColumnIndex(ContactsContract.Data.HAS_PHONE_NUMBER);

            Contact contact;

            do{
                contactName = contactsCursor.getString(columnName);
                contactPhone = contactsCursor.getString(columnPhone);
                hasPhoneNumber = contactsCursor.getString(columnHasNumber);

                if (!hasPhoneNumber.equals("0")){
                    contact = new Contact(contactName,contactPhone,null);
                    contacts.add(contact);
                }
            } while (contactsCursor.moveToNext());
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

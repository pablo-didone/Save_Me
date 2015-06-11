package mubbi.saveme.configuration;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mubbi.saveme.R;
import mubbi.saveme.contact_list.Contact;
import mubbi.saveme.contact_list.ContactsActivity;

/**
 * Created by pdidone on 10/06/2015.
 */
public class ConfigurationActivity extends Activity {

    ArrayList<Contact> contacts;



    private final int PICK_CONTACTS_REQUEST = 1;

    private Spinner spnDelay;
    private Spinner spnFrecuency;
    private Spinner spnTotalTime;
    private Spinner spnAdvice;
    private ImageButton btnAddContact;
    private ArrayAdapter<String> spinnerAdapter;
    private ListView lstContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //ListView
        lstContacts = (ListView)findViewById(R.id.lstSelectedContacts);

        //Spinner Delay
        spnDelay = (Spinner)findViewById(R.id.spnDelay);
        final String[] delayData = new String[]{"1","5","10","30","60"};

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, delayData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDelay.setAdapter(spinnerAdapter);


        //Spinner frecuency
        spnFrecuency = (Spinner)findViewById(R.id.spnFrecuency);
        final String[] frecuencyData = new String[]{"1","3","5","10"};

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, frecuencyData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnFrecuency.setAdapter(spinnerAdapter);

        //Spinner Total Time
        spnTotalTime = (Spinner)findViewById(R.id.spnTotalTime);

        final String[] totalTimeData = new String[]{"5","10","30"};

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, totalTimeData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTotalTime.setAdapter(spinnerAdapter);

        //Spinner Advice
        spnAdvice = (Spinner)findViewById(R.id.spnAdvice);

        final String[] AdviceData = new String[]{
                getResources().getString(R.string.vibrate),
                getResources().getString(R.string.sound),
                getResources().getString(R.string.do_nothing)
        };

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AdviceData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnAdvice.setAdapter(spinnerAdapter);

        //Button add contact
        btnAddContact = (ImageButton)findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, ContactsActivity.class);
                startActivityForResult(intent, PICK_CONTACTS_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_CONTACTS_REQUEST && resultCode == RESULT_OK){
            contacts = data.getParcelableArrayListExtra("CONTACT_LIST");
            loadSelectedContacts();
        }
    }

    private void loadSelectedContacts(){
        SelectedContactsAdapter adapter = new SelectedContactsAdapter(this, contacts);
        lstContacts.setAdapter(adapter);
    }
}

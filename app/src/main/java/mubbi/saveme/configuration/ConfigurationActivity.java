package mubbi.saveme.configuration;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    private ArrayList<Contact> contacts;

    private final int PICK_CONTACTS_REQUEST = 1;

    private EditText txtTitle;
    private Spinner spnDelay;
    private ImageButton btnAddContact;
    private ArrayAdapter<String> spinnerAdapter;
    private ListView lstContacts;
    private Button btnSaveAdvice;
    SelectedContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //TextBox advice title
        txtTitle = (EditText)findViewById(R.id.txtEventTitle);

        //ListView
        lstContacts = (ListView)findViewById(R.id.lstSelectedContacts);
        registerForContextMenu(lstContacts);

        //Spinner Delay
        spnDelay = (Spinner)findViewById(R.id.spnDelay);
        final String[] delayData = new String[]{"1","5","10","30","60"};

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, delayData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDelay.setAdapter(spinnerAdapter);


        //Button add contact
        btnAddContact = (ImageButton)findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConfigurationActivity.this, ContactsActivity.class);

                //Pass selected contacts yet
                if (contacts != null && contacts.size() > 0){
                    ArrayList<String> selected = new ArrayList<>();
                    for(int i = 0; i < contacts.size(); i++){
                        selected.add(contacts.get(i).getId());
                    }
                    intent.putStringArrayListExtra("SELECTED", selected);
                }

                startActivityForResult(intent, PICK_CONTACTS_REQUEST);
            }
        });

        //Button save advice
        btnSaveAdvice = (Button)findViewById(R.id.btnDoneConfig);
        btnSaveAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdvice();
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_contacts, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.ctxDeleteContact:
                contacts.remove(contacts.get(info.position));
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void loadSelectedContacts(){
        adapter = new SelectedContactsAdapter(this, contacts);
        lstContacts.setAdapter(adapter);
    }

    private void saveAdvice(){
        String title;
        int delay;

        //Validate TextView Title
        if (!txtTitle.getText().toString().equals("")){
            title = txtTitle.getText().toString();
        }else{
            showMessage(getResources().getString(R.string.title_required));
            return;
        }

        //Validate contacts list
        if (contacts == null){
            showMessage(getResources().getString(R.string.contacts_required));
            return;
        }else if (contacts.size() < 1){
            showMessage(getResources().getString(R.string.contacts_required));
            return;
        }

        delay = Integer.parseInt(spnDelay.getSelectedItem().toString());

        //Format contacts to save
        String contactsString = "";
        for (int i = 0; i < contacts.size(); i++){
            contactsString += contacts.get(i).getName() + "/" + contacts.get(i).getPhone() + ";";
        }

        //Save in data base
        AdvicesSqLiteHelper sqLiteHelper = new AdvicesSqLiteHelper(this, "DBAdvices", null, 1);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        if(db != null){
            String sql = "INSERT INTO advices (title, delay, contacts) VALUES ('" + title + "',"
                    + delay + ",'" + contactsString + "')";

            db.execSQL(sql);
            db.close();
            showMessage(getResources().getString(R.string.saved_successfully));
        }else{
            showMessage(getResources().getString(R.string.save_failed));
        }
        ConfigurationActivity.this.finish();
    }

    private void showMessage(String errorText){
        Toast toast = Toast.makeText(this, errorText, Toast.LENGTH_LONG);
        toast.show();
    }
}

package mubbi.saveme.main;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mubbi.saveme.R;
import mubbi.saveme.configuration.Advice;
import mubbi.saveme.configuration.AdvicesSqLiteHelper;
import mubbi.saveme.configuration.ConfigurationActivity;
import mubbi.saveme.contact_list.Contact;


public class MainActivity extends Activity {

    ListView lstAdvices;
    ArrayList<Advice> adviceList;
    AdvicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set action to button
        Button btnNewAdvice = (Button)findViewById(R.id.btnNewAdvice);
        btnNewAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });

        //Load saved advices and show into list view        
        lstAdvices = (ListView)findViewById(R.id.lstAdvices);
        registerForContextMenu(lstAdvices);
        showAdvicesList();
        
        //Set listview click listener
        lstAdvices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAdvicesList();
    }

    private void showAdvicesList(){
        adviceList = loadSavedAdvices();
        adapter = new AdvicesAdapter(this, adviceList);
        lstAdvices.setAdapter(adapter);
    }

    private ArrayList<Advice> loadSavedAdvices(){

        ArrayList<Advice> advicesList = new ArrayList<>();

        AdvicesSqLiteHelper sqLiteHelper = new AdvicesSqLiteHelper(this, "DBAdvices", null, 1);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM advices", null);

        Advice advice;

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                int delay = cursor.getInt(2);
                ArrayList<Contact> contacts = getContactsFromString(cursor.getString(3));

                advice = new Advice(id,title,delay,contacts);
                advicesList.add(advice);
            }while(cursor.moveToNext());
        }

        return advicesList;
    }

    private ArrayList<Contact> getContactsFromString(String cad){
        ArrayList<Contact> contacts = new ArrayList<>();

        String[] contactsArray = cad.split(";");
        for(int i = 0; i < contactsArray.length; i++){
            String[] stringContact = contactsArray[i].split("/");
            String name = stringContact[0];
            String phone = stringContact[1];

            Contact contact = new Contact(null, name, phone, null);
            contacts.add(contact);
        }

        return contacts;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_advices, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        
        switch (item.getItemId()){
            case R.id.ctxDeleteAdvice:
                deleteAdvice(adviceList.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    
    private void deleteAdvice(Advice advice){
        AdvicesSqLiteHelper sqLiteHelper = new AdvicesSqLiteHelper(this,"DBAdvices",null,1);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        
        String sql = "DELETE FROM advices WHERE id = " + advice.getId();        
        db.execSQL(sql);
        
        adviceList.remove(advice);  
        adapter.notifyDataSetChanged();
    }
    
    
}

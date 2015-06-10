package mubbi.saveme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mubbi.saveme.configuration.ConfigurationActivity;
import mubbi.saveme.contact_list.ContactsActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowContacts = (Button)findViewById(R.id.btnShowContacts);
        btnShowContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }



}

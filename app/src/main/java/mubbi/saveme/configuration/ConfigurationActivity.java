package mubbi.saveme.configuration;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import mubbi.saveme.R;
import mubbi.saveme.contact_list.ContactsActivity;

/**
 * Created by pdidone on 10/06/2015.
 */
public class ConfigurationActivity extends Activity {

    private Spinner spnDelay;
    private Spinner spnFrecuency;
    private Spinner spnTotalTime;
    private Spinner spnAdvice;
    private ImageButton btnAddContact;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

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
                startActivity(intent);
            }
        });
    }
}

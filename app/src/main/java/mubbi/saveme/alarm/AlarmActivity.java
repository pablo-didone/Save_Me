package mubbi.saveme.alarm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import mubbi.saveme.R;
import mubbi.saveme.configuration.Advice;


/**
 * Created by pdidone on 30/06/2015.
 */
public class AlarmActivity extends Activity {

    ListView lstContactsAlarm;
    AlarmContactsAdapter adapter;
    Advice advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Get information from intent
        advice = getIntent().getExtras().getParcelable("ADVICE");

        //Set list of contacts
        lstContactsAlarm = (ListView)findViewById(R.id.lstAlarmContacts);
        adapter = new AlarmContactsAdapter(this, advice.getContactList());
        lstContactsAlarm.setAdapter(adapter);


        TextView lblAlarmDelay = (TextView)findViewById(R.id.lblAlarmDelay);
        lblAlarmDelay.setText(lblAlarmDelay.getText().toString() + ": "
                + Integer.toString(advice.getDelay()) + " minutos");
    }



}

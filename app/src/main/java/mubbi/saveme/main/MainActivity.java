package mubbi.saveme.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import mubbi.saveme.R;
import mubbi.saveme.configuration.Advice;
import mubbi.saveme.configuration.ConfigurationActivity;


public class MainActivity extends Activity {

    private final int SELECT_ADVICE_REQUEST = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowContacts = (Button)findViewById(R.id.btnShowContacts);
        btnShowContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivityForResult(intent, SELECT_ADVICE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_ADVICE_REQUEST && resultCode == RESULT_OK){
            Advice advice = data.getExtras().getParcelable("ADVICE");
            Log.e("ADVICE",advice.toString());
        }
    }
}

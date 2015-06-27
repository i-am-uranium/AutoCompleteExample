package appers.com.autocompleteexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    private Toolbar toolbar;
    EditText contact_person, contact_number;
    Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setSupportActionBar(toolbar);


    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        contact_person = (EditText) findViewById(R.id.contact_person);
        contact_number = (EditText) findViewById(R.id.contact_number);
        done = (Button) findViewById(R.id.done);

    }


    public void done(View view) {
        if (!contact_number.getText().toString().equals("") && !contact_person.getText().toString().equals("")) {
//            startActivity(new Intent(this, TransportCheck.class));
            Toast.makeText(this, "Thank You for using Ambulance we will reach soon.", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "One or More Fields Are empty", Toast.LENGTH_SHORT).show();
    }
}
